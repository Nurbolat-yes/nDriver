package by.nurbolat.ndrive.map;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<F,T>{

    T mapFrom(F o);
}
