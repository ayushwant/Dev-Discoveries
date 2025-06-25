package ayushwant.springwebflux.keyboard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class WiredKeyboard implements Keyboard{

    @Override
    public String getType() {
        return "Wired Keyboard";
    }
}
