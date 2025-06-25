package ayushwant.springwebflux.keyboard;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {


    @Autowired
    WirelessKeyboard keyboard;

    @Autowired
    private ApplicationContext context;

    @Autowired
    UncleService uncleService;

    @PostConstruct
    public void init() {
        System.out.println("MainController initialized");

//        uncleService = new UncleServiceImpl(new WirelessKeyboard());
        UncleService wirelessUncle = context.getBean(UncleServiceImpl.class, context.getBean("wirelessKeyboard", Keyboard.class));
        System.out.println(wirelessUncle.getKeyboardType()); // Wireless

//        System.out.println(uncleService.getKeyboardType());
    }
}
