package ayushwant.springwebflux.numbers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController("rx")
@AllArgsConstructor
public class Controller {

    private final NumbersService numbersService;

    @GetMapping("getNumbers")
    public Flux<String> getNumbers(@RequestParam int total) {
        Flux<String> numbers = numbersService.getNumbers(total);

        System.out.printf("Returning %d numbers\n", total);

        return numbers;
    }

    @GetMapping("numbers")
    public Mono<Integer> getNumber(@RequestParam int total) {
        Mono<Integer> number = numbersService.getNumber(total);

        // these will be printed immediately irrespective the number is returned from the service or not.
/*        O/P:
        Trying to return 100000 number
        Returning MonoCreate number
        */
        System.out.printf("Trying to return %d number\n", total);
        System.out.printf("Returning %s number\n", number.toString());

        // the value will be returned only after returned from service.
        // controller remains open
        return number;
    }

}
