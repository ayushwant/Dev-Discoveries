package ayushwant.springwebflux.numbers;

import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class NumbersService {

    public void printNumbersBoundedElastic(int total) {

        long startTime = System.currentTimeMillis();

        // Use subscribeOn to move this work to a new thread from the boundedElastic scheduler
        Flux<String> flux = getNumbers(total)
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
                .concatWith(Flux.error(new RuntimeException("An error occurred"))) // This line is used to concatenate an error signal to the end of the Flux sequence. Used for testing purposes
                .doOnError(e -> System.out.println("Error: " + e.getMessage()));

/*
        Params:
        consumer – the consumer to invoke on each value
        errorConsumer – the consumer to invoke on error signal
        completeConsumer – the consumer to invoke on complete signal
        Returns:
        a new Disposable that can be used to cancel the underlying
*/
        Disposable disposable = flux.subscribe(System.out::println,
                e -> System.out.println("Error: " + e.getMessage()),
                () -> {
                    System.out.println("Completed");
                });

        // this immediately disposes the subscription, and the process stops even before it starts.
//        stopProcess(disposable);
    }

    public void stopProcess(Disposable disposable) {
        // Dispose of the subscription when you want to stop the process
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            System.out.println("Process stopped.");
        }
    }

    public Flux<String> getNumbers(int total) {

        return Flux.create(sink -> {

//            throw new RuntimeException("Error in service function");

            for (long i = 1; i <= total; i++)
                for(long l = 1; l<=total; l++) {
                    if(l == total)
                        sink.next( i + " - " + l);
                }
            sink.complete();
        });
    }

    // this is still blocking, because the loop of the service function is running in the same thread.
    public void printNumbersInBg(int total) {
        getNumbers(total).subscribe(System.out::println);
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
