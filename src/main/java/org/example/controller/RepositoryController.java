package org.example.controller;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.service.RepositoryService;

@Path("/repos")
@Produces(MediaType.APPLICATION_JSON)
public class RepositoryController {

    @Inject
    RepositoryService repositoryService;

    @GET
    @Path("/{username}")
    public Uni<Response> getRepositories(@PathParam("username") String username) {
        return repositoryService.getRepositories(username);
    }
}