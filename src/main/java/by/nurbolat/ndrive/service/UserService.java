package by.nurbolat.ndrive.service;

import by.nurbolat.ndrive.database.entity.User;
import by.nurbolat.ndrive.database.repository.UserRepository;
import by.nurbolat.ndrive.map.UserMapper;
import dto.UserCreateDto;
import dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserReadDto createUser(UserCreateDto userCreateDto){
        User user = User.builder()
                .username(userCreateDto.getUsername())
                .password(passwordEncoder.encode(userCreateDto.getPassword()))
                .build();

       return new UserReadDto(userRepository.save(user).getUsername());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.emptyList()
                )).orElseThrow(()-> new UsernameNotFoundException("Failed to retrieve user" + username));
    }
}
