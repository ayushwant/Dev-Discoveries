package ayushwant.springwebflux.keyboard;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UncleServiceImpl implements UncleService {

    private final Keyboard keyboard;

    public UncleServiceImpl(@Qualifier("wiredKeyboard")
                            Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public String getKeyboardType() {
        return keyboard.getType();
    }
}
