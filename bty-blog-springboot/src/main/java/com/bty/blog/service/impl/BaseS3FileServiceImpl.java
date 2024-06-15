package com.bty.blog.service.impl;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.bty.blog.entity.FileType;
import com.bty.blog.entity.S3Key;
import com.bty.blog.service.FileService;
import com.bty.blog.util.ThumbnailUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

/**
 * @author bty
 * @date 2022/12/7
 * @since 1.8
 **/

@RequiredArgsConstructor
public abstract class BaseS3FileServiceImpl implements FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseS3FileServiceImpl.class);


    public static final String DEFAULT_BUCKET = "bty-blog";

    private final AmazonS3 amazonS3;

    @Override
    public String handleCover(MultipartFile multipartFile) throws IOException {

        String originalFileName = multipartFile.getOriginalFilename();
        assert originalFileName != null;
        FileType type = FileType.of(getFileExtention(originalFileName));

        // 图片
        if (type.equals(FileType.IMAGE)) {
            InputStream originalInputStream = multipartFile.getInputStream();
            InputStream thumbnailInputStream = ThumbnailUtil.createImageThumbnail(originalInputStream, getFileExtention(originalFileName));
            PutObjectResult result = doStore(thumbnailInputStream, originalFileName, S3Key.COVER.getPrefix());
            return getUrlFromPutObjectResult(result);
        }
        return getCoverUrlFromNonImageFileType(type);
    }



    @Override
    public String storeFile(MultipartFile multipartFile, Map<String,Object> meta) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        LOGGER.info("start filebase uploading of {}", originalFilename);
        S3Key s3key;
        try {
            s3key = (S3Key)meta.get("s3key");
        } catch (Exception e) {
            LOGGER.error("S3 implementation requires s3key");
            throw new RuntimeException("S3 implementation requires s3key ");
        }
        PutObjectResult result = doStore(multipartFile.getInputStream(), originalFilename, s3key.getPrefix());
        return getUrlFromPutObjectResult(result);
    }

    private PutObjectResult doStore(InputStream in, String filename, String s3KeyPrefix) {
        PutObjectResult result;
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            String randomFilename = buildFilename(filename);
            String key = s3KeyPrefix + randomFilename;
            result = amazonS3.putObject(DEFAULT_BUCKET, key, in, objectMetadata);
            LOGGER.info("filebase uploaded successfully of {}", key);
        } catch (SdkClientException e) {
            LOGGER.error(e.getMessage());
            throw new SdkClientException(e.getMessage());
        }
        return result;
    }


    public abstract String getUrlFromPutObjectResult(PutObjectResult result);


    public String getCoverUrlFromNonImageFileType(FileType fileType) {
        if (fileType.equals(FileType.IMAGE)) {
            LOGGER.error("image fileType not supported");
            throw new RuntimeException("image fileType not supported");
        }
        return doGetCoverUrlFromNonImageFileType(fileType);
    }

    public abstract String doGetCoverUrlFromNonImageFileType(FileType fileType);
}
