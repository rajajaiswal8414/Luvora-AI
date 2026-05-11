package com.luvora.ai.controller;

import com.luvora.ai.dto.project.ProjectRequest;
import com.luvora.ai.dto.project.ProjectResponse;
import com.luvora.ai.dto.project.ProjectSummaryResponse;
import com.luvora.ai.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponse>> getMyProjects(){
        return ResponseEntity.ok(projectService.getUserProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id){
        return ResponseEntity.ok(projectService.getUserProjectById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest projectRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(projectRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProjectById(@PathVariable Long id, @Valid @RequestBody ProjectRequest projectRequest){
        return ResponseEntity.ok(projectService.updateProject(id, projectRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id){
        projectService.softDelete(id);
        return ResponseEntity.noContent().build();
    }


}
