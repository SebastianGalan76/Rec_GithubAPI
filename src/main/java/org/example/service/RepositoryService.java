package org.example.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.GitHubApiClient;
import org.example.model.Repository;

import java.util.List;

@ApplicationScoped
public class RepositoryService {

    @RestClient
    GitHubApiClient gitHubApiClient;

    /**
     * Retrieves a list of repositories for a given GitHub user.
     *
     * @param username The GitHub username for which repositories are fetched
     * @return List of repositories wrapped by Uni<>.
     */
    public Uni<List<Repository>> getRepositories(String username) {
        return gitHubApiClient.getRepositories(username);
    }
}
