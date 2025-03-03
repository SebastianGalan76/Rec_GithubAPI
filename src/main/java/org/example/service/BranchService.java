package org.example.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.GitHubApiClient;
import org.example.model.Branch;

import java.util.List;

@ApplicationScoped
public class BranchService {
    @RestClient
    GitHubApiClient gitHubApiClient;

    /**
     * Retrieves a list of branches for a given GitHub repository
     *
     * @param username The GitHub username who owns the repository
     * @param repositoryName The name of the repository for which branches are fetched.
     * @return List of branches wrapped by Uni<>
     */
    public Uni<List<Branch>> getBranchesForRepo(String username, String repositoryName) {
        return gitHubApiClient.getBranches(username, repositoryName);
    }
}
