package org.example.controller;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.model.Repository;
import org.example.service.RepositoryService;

import java.util.List;

@Path("/repos")
@Produces(MediaType.APPLICATION_JSON)
public class RepositoryController {

    @Inject
    RepositoryService repositoryService;

    @GET
    @Path("/{username}")
    public Uni<List<Repository>> getRepositories(@PathParam("username") String username) {
        return repositoryService.getRepositories(username);
    }
}