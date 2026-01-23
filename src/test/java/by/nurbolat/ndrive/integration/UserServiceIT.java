package by.nurbolat.ndrive.integration;

import by.nurbolat.ndrive.annotation.IT;
import by.nurbolat.ndrive.database.entity.User;
import by.nurbolat.ndrive.database.repository.UserRepository;
import by.nurbolat.ndrive.service.UserService;
import dto.UserCreateDto;
import dto.UserReadDto;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestConstructor;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@IT
@Testcontainers
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserServiceIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16");

    private final UserService userService;

    @Test
    void createUser(){
        var userCreateDto = new UserCreateDto("aslan","123");
        var userReadDto = new UserReadDto("aslan");

        userService.createUser(userCreateDto);

        var actualUser = userService.loadUserByUsername(userCreateDto.getUsername());

        assertNotNull(actualUser);
        assertEquals(userReadDto.getUsername(),actualUser.getUsername());
    }

    @Test
    void uniqueUsername(){
        var userCreateDto = new UserCreateDto("aslan","123");
        var duplicatedDto = new UserCreateDto("aslan","4567");

        userService.createUser(duplicatedDto);

        Exception exception = assertThrows(RuntimeException.class,()->{
            userService.createUser(duplicatedDto);
        });

        assertTrue(exception.getMessage().contains("duplicate key value violates"));
    }
}
