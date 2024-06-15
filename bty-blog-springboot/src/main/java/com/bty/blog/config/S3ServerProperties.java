package com.bty.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author bty
 * @date 2023/1/20
 * @since 1.8
 **/
@ConfigurationProperties(prefix = "s3")
public class S3ServerProperties {
    private String name;
    private String key;
    private String secret;
    private String endpoint;
    private String region;
    private String bucket;

    @Override
    public String toString() {
        return "S3ServerProperties{" +
                "name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", secret='" + secret + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", region='" + region + '\'' +
                ", bucket='" + bucket + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
