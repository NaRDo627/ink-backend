package net.ink.api.core.component;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {
    String uploadMultiPartFile(MultipartFile mFile, String filePath);
}
