package by.nurbolat.ndrive.controller;

import by.nurbolat.ndrive.service.UserService;
import dto.UserDto;
import dto.UserReadDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/sign-up")
public class RegistrationController {
    private final UserService userService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@RequestBody UserDto user){
       return new UserReadDto(userService.create(user).getUsername());
    }
}
