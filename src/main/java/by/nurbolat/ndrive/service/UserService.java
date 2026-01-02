package by.nurbolat.ndrive.service;

import by.nurbolat.ndrive.database.entity.User;
import by.nurbolat.ndrive.database.repository.UserRepository;
import dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDto create(UserDto userDto){
        User user = User.builder()
            .username(userDto.getUsername())
            .password(userDto.getPassword())
            .build();

        User user1 = userRepository.save(user);

        return new UserDto(user1.getUsername(),user1.getPassword());
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
