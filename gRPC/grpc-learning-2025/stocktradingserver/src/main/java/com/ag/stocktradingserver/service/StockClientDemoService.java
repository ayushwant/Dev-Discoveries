package com.ag.stocktradingserver.service;

import com.javatechie.grpc.*;
import io.grpc.stub.StreamObserver;
//import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * Client service to demonstrate various gRPC communication patterns with StockTradingService
 * The client is called by the UI/Controller layer to perform operations
 */
@Service
public class StockClientDemoService {

    //    @GrpcClient("stockService")
    private StockTradingServiceGrpc.StockTradingServiceBlockingStub stockTradingServiceBlockingStub;

    private StockTradingServiceGrpc.StockTradingServiceStub stockTradingServiceStub;

    /**
     * The client call to get the stock price for a symbol
     * Notice we don't build the stream of response observer, it'll be done by gRPC since we're using a blocking stub
     * If we use normal stub (async), we need to build the response observer as well
     *
     * @param stockSymbol the stock symbol UI wants to know the price for
     * @return the response returned by the server
     */
    public StockResponse getStockPrice(String stockSymbol) {
        StockRequest request = StockRequest.newBuilder().setStockSymbol(stockSymbol).build();

        // when using blocking stub
        return stockTradingServiceBlockingStub.getStockPrice(request);

        // when using async stub
//        return stockTradingServiceStub.getStockPrice(request , new StreamObserver<StockResponse>() {
//            @Override
//            public void onNext(StockResponse stockResponse) {
//                System.out.println("Received stock price: " + stockResponse.getPrice());
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                System.out.println("Error occurred: " + throwable.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("Stock price retrieval completed.");
//            }
//        });
    }

    /**
     * The client call to subscribe to stock price updates for a symbol
     * We build a request and create a new response observer to handle the stream of responses from the server
     * @param symbol requested by UI
     */
    public void subscribeStockPrice(String symbol) {
        // 1. create a request
        StockRequest request = StockRequest.newBuilder()
                .setStockSymbol(symbol)
                .build();

        // 2. create a response observer and handle the stream of responses
        StreamObserver<StockResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(StockResponse response) {
                System.out.println("Stock Price Update: " + response.getStockSymbol() +
                        " Price: " + response.getPrice() + " " +
                        " Time: " + response.getTimestamp());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error : " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("stock price stream live update completed !");
            }
        };

        // 3. call the server method with request and response observer
        stockTradingServiceStub.subscribeStockPrice(request, responseObserver);
    }


    public void placeBulkOrders() {

        // create a response observer and handle the single summary response from the server
        StreamObserver<OrderSummary> responseObserver = new StreamObserver<OrderSummary>() {
            @Override
            public void onNext(OrderSummary summary) {
                System.out.println("Order Summary Received from Server:");
                System.out.println("Total Orders: " + summary.getTotalOrders());
                System.out.println("Successful Orders: " + summary.getSuccessCount());
                System.out.println("Total Amount: $" + summary.getTotalAmount());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Order Summary Receivedn error from Server:" + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Stream completed , server is done sending summary !");
            }
        };

        // create a request observer to send multiple stock order requests to the server
        StreamObserver<StockOrder> requestObserver = stockTradingServiceStub.bulkStockOrder(responseObserver);

        // send multiple steam of stock order message/request
        try {

            requestObserver.onNext(StockOrder.newBuilder()
                    .setOrderId("1")
                    .setStockSymbol("AAPL")
                    .setOrderType("BUY")
                    .setPrice(150.5)
                    .setQuantity(10)
                    .build());

            requestObserver.onNext(StockOrder.newBuilder()
                    .setOrderId("2")
                    .setStockSymbol("GOOGL")
                    .setOrderType("SELL")
                    .setPrice(2700.0)
                    .setQuantity(5)
                    .build());

            requestObserver.onNext(StockOrder.newBuilder()
                    .setOrderId("3")
                    .setStockSymbol("TSLA")
                    .setOrderType("BUY")
                    .setPrice(700.0)
                    .setQuantity(8)
                    .build());

            //done sending orders
            requestObserver.onCompleted();
        } catch (Exception ex) {
            requestObserver.onError(ex);
        }

    }


    public void startLiveTrading() throws InterruptedException {

        // 1. create a response observer to handle the stream of trade status updates from the server
        StreamObserver<TradeStatus> responseObserver =  new StreamObserver<>() {

            @Override
            public void onNext(TradeStatus tradeStatus) {
                System.out.println("server response : " + tradeStatus);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error : " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("stream completed. ");
            }
        };

        // 2. create a request observer to send multiple stock order requests to the server
        StreamObserver<StockOrder> requestObserver = stockTradingServiceStub.liveTrading(responseObserver);

        // 3. sending multiple order request from client
        for (int i = 1; i <= 10; i++) {
            StockOrder stockOrder = StockOrder.newBuilder()
                    .setOrderId("ORDER-" + i)
                    .setStockSymbol("APPL")
                    .setQuantity(i * 10)
                    .setPrice(150.0 + i)
                    .setOrderType("BUY")
                    .build();
            requestObserver.onNext(stockOrder);
            Thread.sleep(500);
        }
        requestObserver.onCompleted();
    }
}
