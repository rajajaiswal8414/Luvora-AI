package com.luvora.ai.service.impl;

import com.luvora.ai.dto.project.FileContentResponse;
import com.luvora.ai.dto.project.FileNode;
import com.luvora.ai.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public FileContentResponse getFile(Long projectId, String path, Long userId) {
        return null;
    }
}
