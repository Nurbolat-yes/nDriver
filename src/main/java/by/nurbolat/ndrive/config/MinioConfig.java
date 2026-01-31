package by.nurbolat.ndrive.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfig {
    @Value("${minio.url}")
    private String url;
    @Value("${minio.access-key}")
    private String access;
    @Value("${minio.secret-key}")
    private String password;

    @Bean
    public MinioClient createClient(){
        return MinioClient
                .builder()
                .endpoint(url)
                .credentials(access,password)
                .build();
    }

}
