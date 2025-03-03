package org.example.model.dto;

public record BranchDto(
        String name,
        String lastCommitSha
) { }
