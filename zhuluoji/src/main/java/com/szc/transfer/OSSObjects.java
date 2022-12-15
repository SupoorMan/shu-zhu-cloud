package com.szc.transfer;

import lombok.Data;

@Data
public class OSSObjects {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String url;
}
