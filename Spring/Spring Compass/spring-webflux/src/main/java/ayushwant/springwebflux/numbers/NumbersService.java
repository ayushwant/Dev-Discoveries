package ayushwant.springwebflux.numbers;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class NumbersService {

    public void printNumbersBoundedElastic(int total) {

        long startTime = System.currentTimeMillis();

        // Use subscribeOn to move this work to a new thread from the boundedElastic scheduler
        getNumbers(total)
                .subscribeOn(Schedulers.boundedElastic())  // Run on a background thread pool
                .doOnComplete(() -> {

                    // perform a certain task after the operation is completed successfully.
                    // won't be called if there are any errors.
                    // if you wish to perform a task after the operation is completed,
                    // regardless of whether it was successful or not, you can use the doFinally() or doOnTerminate() method.

                    // also, doOnComplete() is called irrespective of the .subscribeOn(Schedulers.boundedElastic())
                    long endTime = System.currentTimeMillis();
                    System.out.println("Service function completed after " + (endTime - startTime) + " ms");
                })
                .subscribe(System.out::println);

/*      The issue with this approach is that you're measuring the time immediately after calling the subscribe() method.
        However, in a reactive flow, when you call subscribe(), the operation is performed asynchronously.
        This means the flow hasn’t completed yet by the time you calculate the time difference between startTime and endTime.
        To measure the time taken by the operation, you need to use the doOnComplete() or  method to calculate the time difference.
        long endTime = System.currentTimeMillis();

        System.out.println("Service function completed after " + (endTime - startTime) + " ms");
        */
    }

    // this is still blocking, because the loop of the service function is running in the same thread.
    public void printNumbersInBg(int total) {
        getNumbers(total).subscribe(System.out::println);
    }

    public Flux<String> getNumbers(int total) {

        return Flux.create(sink -> {

            for (long i = 1; i <= total; i++)
                for(long l = 1; l<=total; l++) {
                    if(l == total)
                        sink.next( i + " - " + l);
                }
            sink.complete();
        });
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
