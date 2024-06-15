package com.bty.blog.entity;

/**
 * @author bty
 * @date 2023/1/21
 * @since 1.8
 **/
public enum S3Key {
    COVER("cover/"),RECORD("record/"),BLOG("blog/");

    private final String prefix;

    public String getPrefix() {
        return prefix;
    }

    S3Key(String prefix) {
        this.prefix = prefix;
    }
}
