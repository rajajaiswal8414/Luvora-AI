package com.luvora.ai.service;

import com.luvora.ai.dto.project.FileContentResponse;
import com.luvora.ai.dto.project.FileNode;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFile(Long projectId, String path, Long userId);
}
