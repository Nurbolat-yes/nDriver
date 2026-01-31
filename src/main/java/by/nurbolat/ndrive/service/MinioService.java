package by.nurbolat.ndrive.service;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class MinioService {
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    public void uploadObject() throws Exception {
        if (!isBucketExist()){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }



    }

    private boolean isBucketExist() throws Exception {
        return minioClient.bucketExists(
          BucketExistsArgs.builder()
                  .bucket(bucketName)
                  .build()
        );
    }
}
