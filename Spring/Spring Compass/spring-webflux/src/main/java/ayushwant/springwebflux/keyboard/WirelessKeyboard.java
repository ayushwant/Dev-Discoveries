package ayushwant.springwebflux.keyboard;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class WirelessKeyboard implements Keyboard {
    @Override
    public String getType() {
        return "Wireless Keyboard";
    }
}
