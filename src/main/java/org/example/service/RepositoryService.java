package org.example.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.GitHubApiClient;
import org.example.model.dto.BranchDto;
import org.example.model.dto.ErrorResponse;
import org.example.model.dto.RepositoryDto;

@ApplicationScoped
public class RepositoryService {

    @RestClient
    GitHubApiClient gitHubApiClient;

    @Inject
    BranchService branchService;

    /**
     * Retrieves a list of repositories for a given GitHub user.
     *
     * @param username The GitHub username for which repositories are fetched
     * @return List of repositories wrapped by Uni<>.
     */
    public Uni<Response> getRepositories(String username) {
        return gitHubApiClient.getRepositories(username)
                .onItem().transformToUni(repos -> {
                    if (repos == null) {
                        return Uni.createFrom().item(Response.status(404)
                                .entity(new ErrorResponse(404, "User not found"))
                                .build());
                    }

                    return Uni.combine().all().unis(
                            repos.stream()
                                    .filter(repo -> !repo.fork())
                                    .map(repo -> branchService.getBranchesForRepo(username, repo.name())
                                            .onItem().transform(branches -> new RepositoryDto(
                                                    repo.name(),
                                                    repo.owner().login(),
                                                    branches.stream().map(branch -> new BranchDto(branch.name(), branch.commit().sha())).toList()
                                            )))
                                    .toList()).collectFailures()
                            .with(list -> Response.ok(list).build());
                })
                .onFailure().recoverWithItem(e -> Response.status(404)
                        .entity(new ErrorResponse(404, e.getMessage()))
                        .build());
    }
}
