package com.bty.blog.controller;

import com.bty.blog.annotation.Admin;
import com.bty.blog.domain.Response;
import com.bty.blog.entity.FileType;
import com.bty.blog.entity.S3Key;
import com.bty.blog.entity.vo.FileRecordVO;
import com.bty.blog.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

/**
 * @author bty
 * @date 2022/12/7
 * @since 1.8
 **/
@Api(value = "文件上传")
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @Admin
    @ApiOperation(value = "上传record")
    @PostMapping("/file/record")
    public Response recordFile(@RequestBody MultipartFile multipartFile) throws IOException {

        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;

        FileType fileType = FileType.of(fileService.getFileExtention(originalFilename));

        String coverUrl  = fileService.handleCover(multipartFile);

        String url = fileService.storeFile(multipartFile,Collections.singletonMap("s3key", S3Key.RECORD));

        return Response.success(new FileRecordVO(url,coverUrl,fileType.getCode()));
    }

    @Admin
    @ApiOperation(value = "上传post中图片")
    @PostMapping("/file/postImage")
    public Response postImage(@RequestBody MultipartFile multipartFile) throws IOException {

        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        FileType type = FileType.of(fileService.getFileExtention(originalFilename));
        if(!type.equals(FileType.IMAGE)){
            throw new RuntimeException("only image allowed!");
        }
        String url = fileService.storeFile(multipartFile,Collections.singletonMap("s3key", S3Key.BLOG));
        return Response.success(Collections.singletonMap("url",url));
    }
}
