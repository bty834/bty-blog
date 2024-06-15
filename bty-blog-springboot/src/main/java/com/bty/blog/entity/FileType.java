package com.bty.blog.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author bty
 * @date 2023/1/21
 * @since 1.8
 **/
public enum FileType {
    IMAGE(0),VIDEO(1),AUDIO(2),
    PDF(3),WORD(4),EXCEL(5),
    PPT(6), MD(7),
    OTHER(100);

    private final Integer code;

    FileType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    private static final Set<String> IMAGE_EXT = new HashSet<String>(){{
        addAll(Arrays.asList("jpg","png","jpeg","gif"));
    }};
    private static final Set<String> AUDIO_EXT = new HashSet<String>(){{
        addAll(Arrays.asList("mp3","wma","aac"));
    }};
    private static final Set<String>  VIDEO_EXT = new HashSet<String>(){{
        addAll(Arrays.asList("mp4","avi","mpg","mpeg"));
    }};
    private static final Set<String> PDF_EXT = new HashSet<String>(){{
        addAll(Arrays.asList("pdf"));
    }};
    private static final Set<String> WORD_EXT = new HashSet<String>(){{
        addAll(Arrays.asList("doc","docx"));
    }};
    private static final Set<String> EXCEL_EXT = new HashSet<String>(){{
        addAll(Arrays.asList("xls","xlsx"));
    }};
    private static final Set<String> PPT_EXT = new HashSet<String>(){{
        addAll(Arrays.asList("ppt","pptx"));
    }};
    private static final Set<String> MD_EXT = new HashSet<String>(){{
        addAll(Arrays.asList("md"));
    }};



    public static FileType of(String ext){

        if(IMAGE_EXT.contains(ext)){
            return FileType.IMAGE;
        }
        if(VIDEO_EXT.contains(ext)){
            return FileType.VIDEO;
        }
        if(AUDIO_EXT.contains(ext)){
            return FileType.AUDIO;
        }
        if(PDF_EXT.contains(ext)){
            return FileType.PDF;
        }
        if(WORD_EXT.contains(ext)){
            return FileType.WORD;
        }
        if(EXCEL_EXT.contains(ext)){
            return FileType.EXCEL;
        }
        if(PPT_EXT.contains(ext)){
            return FileType.PPT;
        }
        if(MD_EXT.contains(ext)){
            return FileType.MD;
        }
        return FileType.OTHER;
    }


}
