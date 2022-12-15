package com.szc.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.szc.transfer.OSSObjects;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;


@Slf4j
public class OssUtil {

    //单项目 临时处理方式
    public static OSSObjects oSSObjects = new OSSObjects();


    public static void main(String[] args) throws IOException {
//        InputStream is = new FileInputStream(new File(OssUtil.class.getResource("/a.text").getFile()));
//        upload(is, "test/a.text");

        //find("zhuluoji/a.txt");
    }

    public static void find(String ossFilePath) throws IOException {
        OSS client = new OSSClientBuilder().build(oSSObjects.getEndpoint(), oSSObjects.getAccessKeyId(), oSSObjects.getAccessKeySecret());
        OSSObject object = client.getObject(oSSObjects.getBucketName(), ossFilePath);

//        InputStream in = object.getObjectContent();
//        byte[] b = new byte[64];
//        int i = 0;
//        while ((i = in.read(b)) != -1) {
//            String str = new String(b);
//            System.out.print(str);
//        }

        System.out.println("--------");
        System.out.println(object.getBucketName());
        System.out.println(object.getResponse().getUri());

        object.getObjectContent().close();
    }

    public static String upload(InputStream is, String ossFilePath) throws IOException {
        OSS client = new OSSClientBuilder().build(oSSObjects.getEndpoint(), oSSObjects.getAccessKeyId(), oSSObjects.getAccessKeySecret());

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setObjectAcl(CannedAccessControlList.PublicRead);

            PutObjectResult putObjectResult = client.putObject(oSSObjects.getBucketName(), ossFilePath, is, metadata);
            if (putObjectResult.getRequestId() != null) {
                return oSSObjects.getUrl() + ossFilePath;
            }
            return null;
        } catch (OSSException oe) {
            log.error("OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
            log.error("Error Message: " + oe.getErrorMessage());
            log.error("Error Code: " + oe.getErrorCode());
            log.error("Request ID: " + oe.getRequestId());
            log.error("Host ID: " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            client.shutdown();
        }
        return null;
    }

}
