package org.example.client;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.model.Branch;
import org.example.model.Repository;

import java.util.List;

@RegisterRestClient(baseUri = "https://api.github.com")
public interface GitHubApiClient {
    @GET
    @Path("/users/{username}/repos")
    Uni<List<Repository>> getRepositories(@PathParam("username") String username);

    @GET
    @Path("/repos/{username}/{repo}/branches")
    Uni<List<Branch>> getBranches(@PathParam("username") String username, @PathParam("repo") String repo);
}
