package ayushwant.springwebflux.numbers;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.Thread.sleep;

@Service
public class NumbersService {

    public Flux<String> getNumbers(int total) {

        Flux<String> numbers = Flux.create(sink -> {

            for (long i = 1; i <= total; i++)
                for(long l = 1; l<=total; l++) {
                    if(l == total)
                        sink.next( "\n" + i + " - " + l);
                }
            sink.complete();
        });

        return numbers;
    }

    public Mono<Integer> getNumber(int total) {

        // this takes a lot of time.
        return Mono.create(sink -> {

            int num = 0;
            for (long i = 1; i <= Integer.MAX_VALUE; i++) {

//                for(long j = 1; j <= Integer.MAX_VALUE; j++)
//                    for (long k = 1; k<= Integer.MAX_VALUE; k++)
                        for(long l = 1; l<=Integer.MAX_VALUE; l++){
//                            num = 1;
                        }
            }
            sink.success(total);
        });
    }

}
