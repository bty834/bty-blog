package com.bty.blog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author bty
 * @date 2022/12/7
 * @since 1.8
 **/
public interface FileService {

    default String getFileExtention(String filename){
        int seperatorIdx = filename.lastIndexOf('.');
        return filename.substring(seperatorIdx + 1).toLowerCase();
    }

    default String buildFilename(String originalFilename){
        return UUID.randomUUID() + originalFilename;
    }


    /**
     * get the cover image of file , if the file type is image , then take de-sampled origin image as cover
     * @param multipartFile uploaded file
     * @param type //0 image,1 video,2 audio,3 PDF,4 WORD,5 EXCEL,6 SLIDE,7 MD,8 OTHER
     * @return cover img url
     */
    String handleCover(MultipartFile multipartFile) throws IOException;

    /**
     * @param multipartFile uploaded file
     * @return file url
     */
    String storeFile(MultipartFile multipartFile, Map<String,Object> meta) throws IOException;



}
