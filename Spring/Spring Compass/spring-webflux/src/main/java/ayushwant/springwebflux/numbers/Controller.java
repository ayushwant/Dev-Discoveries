package ayushwant.springwebflux.numbers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController("rx")
@AllArgsConstructor
public class Controller {

    private final NumbersService numbersService;

    /**
     * Releases the controller immediately, because the service uses a different thread to run the function,
     * by making the use of boundedElastic scheduler.
     * <p>
     * We can return both Mono and ResponseEntity, and both will return immediately and be non-blocking.
     * This is because service runs in a diff thread.
     * return Mono<String> response that returns immediately.
     * return ResponseEntity<String> response that returns immediately.
     */
    @GetMapping("printNumbersInBg")
    public ResponseEntity<String> printNumbersInBg(@RequestParam int total) {

        // calling subscribe in a reactive chain is a bad practice.
//        numbersService.getNumbers(total).subscribe(System.out::println);
        /** Inspection info:
         * Calling 'subscribe' in non-blocking context is not recommended
         *         Inspection info:
         *         Reports subscribe() calls in "reactive" methods.
         *         Methods returning a Publisher type (including Flux and Mono) should not call the subscribe() method directly because it can break the reactive call chain.
         *         Instead of using subscribe(), consider using composition operators like flatMap(), zip(), then(), and so on.
         *         Example:
         *           Flux<String> stringFlux(){
         *             Flux<String> flux = Flux.just("abc");
         *             flux.subscribe(); // <- blocking 'subscribe' call in non-blocking context
         *             return flux;
         *           }
         */

        // do a void call to a service for non-blocking flow of controller,
        // and run the service function in background in a diff thread using boundedElastic scheduler.
        numbersService.printNumbersBoundedElastic(total);

        System.out.printf("Returning %d numbers\n", total);

//        return Mono.just("Numbers will be printed to console");
        return ResponseEntity.ok("Numbers will be printed to console");
    }

    // doesn't release the controller until service function is completed.
    // returning a ResponseEntity makes the controller blocking.
    @GetMapping("printNumbers")
    public ResponseEntity<String> printNumbers(@RequestParam int total) {

        numbersService.getNumbers(total).subscribe(System.out::println)
        ;

        System.out.printf("Returning %d numbers\n", total);

        return ResponseEntity.ok("Numbers will be printed to console");
    }


    // doesn't release the controller until service function is completed.
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
