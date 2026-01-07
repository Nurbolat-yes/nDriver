package by.nurbolat.ndrive.map;

import by.nurbolat.ndrive.database.entity.User;
import dto.UserCreateDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserCreateDto, User>{
    @Override
    public User mapFrom(UserCreateDto o) {
        return User.builder()
                .username(o.getUsername())
                .password(o.getPassword())
                .build();
    }
}
