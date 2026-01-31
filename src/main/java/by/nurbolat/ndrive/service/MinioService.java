package by.nurbolat.ndrive.service;

import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioService {
    private final MinioClient minioClient = MinioClient
            .builder()
            .endpoint("http://127.0.0.1:9000")
            .credentials("minioadmin","minioadmin")
            .build();


    public void makeBucket() throws Exception {
        minioClient.makeBucket(
                MakeBucketArgs
                        .builder()
                        .bucket("user-files")
                        .build());
    }
}
