package com.ag.stocktradingserver.service;

import com.ag.stocktradingserver.entity.Stock;
import com.ag.stocktradingserver.repository.StockRepository;
import com.javatechie.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
public class StockTradingServiceImpl extends com.javatechie.grpc.StockTradingServiceGrpc.StockTradingServiceImplBase {

    /**
     * @info: In GRPC, the response is always sent back via StreamObserver because GRPC natively supports streaming.
     * Even for single response scenarios, StreamObserver is used to maintain a consistent API.
     * This allows for flexibility in handling both unary and streaming responses seamlessly.
     */
    private final StockRepository stockRepository;

    /**
     * Get Stock Price for a given stock symbol
     * Single Request - Single Response
     */
    @Override
    public void getStockPrice(com.javatechie.grpc.StockRequest request,
                              io.grpc.stub.StreamObserver<com.javatechie.grpc.StockResponse> responseObserver) {
        String stockSymbol = request.getStockSymbol();

        // get the data from the database and return to the server
        Stock stockEntity = stockRepository.findBySymbol(stockSymbol);

        responseObserver.onNext(com.javatechie.grpc.StockResponse.newBuilder()
                .setStockSymbol(stockEntity.getSymbol())
                .setPrice(101010101)
                .build());
        responseObserver.onCompleted();
    }

    /**
     * Subscribe to Stock Price updates for a given stock symbol
     * Single Request - Stream Response
     * We get a single request and return multiple responses one by one
     */
    @Override
    public void subscribeStockPrice(StockRequest request, StreamObserver<StockResponse> responseObserver) {
        String stockSymbol = request.getStockSymbol();

        // get the data from the database and return to the server
        Stock stockEntity = stockRepository.findBySymbol(stockSymbol);

        // Simulating streaming updates as response - in real scenario, this could be hooked to a real-time data source
        for (int i = 0; i < 10; i++) {
            responseObserver.onNext(StockResponse.newBuilder()
                    .setStockSymbol(stockEntity.getSymbol())
                    .setPrice(stockEntity.getPrice() + i * 10) // Simulated price update
                    .build());
            try {
                Thread.sleep(1000); // Simulate delay between updates
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        responseObserver.onCompleted();
    }

    /** Place Bulk Stock Orders from the client and return a summary
     * Stream Request - Single Response
     * We get multiple requests from the client and return a single response summary when the input is completed
     *
     * @info: Whenever we have client streaming or bidirectional streaming, we return a Stream of input type from the method
     * This is so that the client can keep sending multiple requests via the returned StreamObserver
     *
     * @param responseObserver the response observer to send back the summary
     * @return StreamObserver<StockOrder> to handle incoming stock orders from the client
     */
    @Override
    public StreamObserver<StockOrder> bulkStockOrder(StreamObserver<OrderSummary> responseObserver) {

        // We'll return a stream of input type to the client, so that it can keep sending multiple requests
        // We'll handle the continuous input stream and finally send a single response summary when the client indicates completion
        return  new StreamObserver<>() {

            int totalOrders = 0;
            double totalValue = 0.0;
            int successfulOrders = 0;

            @Override
            public void onNext(StockOrder stockOrder) {
                totalOrders++;
                totalValue += stockOrder.getQuantity() * stockOrder.getPrice();
                successfulOrders++; // In a real scenario, we would have logic to determine if the order
            }

            @Override
            public void onError(Throwable throwable) {
                // Handle error scenario if needed
            }

            @Override
            public void onCompleted() {
                // Once the client indicates that it has finished sending orders, we send back the summary
                OrderSummary summary = OrderSummary.newBuilder()
                        .setTotalOrders(totalOrders)
                        .setTotalAmount(totalValue)
                        .setTotalOrders(successfulOrders)
                        .build();

                // now we return the summary to the client
                responseObserver.onNext(summary);
                responseObserver.onCompleted();
            }
        };
    }


    /**
     * Live Trading - Bidirectional Streaming
     * Stream Request - Stream Response
     * We get multiple stock orders from the client and return trade status updates for each order
     * <p>
     * @info: We'll again return a StreamObserver to handle incoming stock orders from the client
     * and use the provided responseObserver to send back trade status updates
     *
     * @param responseObserver the response observer to send back trade status updates
     * @return StreamObserver<StockOrder> to handle incoming stock orders from the client
     */
    @Override
    public StreamObserver<StockOrder> liveTrading(StreamObserver<TradeStatus> responseObserver) {
        return  new StreamObserver<>() {
            @Override
            public void onNext(StockOrder stockOrder) {
                // have a login to handle the input
                // here we simply simulate processing and respond back with a trade status

                System.out.printf("Received order: %s, Quantity: %d, Price: %.2f%n",
                        stockOrder.getOrderId(), stockOrder.getQuantity(), stockOrder.getPrice());


                TradeStatus tradeStatus = TradeStatus.newBuilder()
                        .setOrderId(stockOrder.getOrderId())
                        .setStatus("EXECUTED")
                        .setMessage("Done") // In real scenario, this could be different
                        .build();

                responseObserver.onNext(tradeStatus);
            }

            @Override
            public void onError(Throwable throwable) {
                // handle error if needed
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
