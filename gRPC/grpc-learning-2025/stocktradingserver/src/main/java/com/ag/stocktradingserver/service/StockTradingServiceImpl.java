package com.ag.stocktradingserver.service;

import com.ag.stocktradingserver.entity.Stock;
import com.ag.stocktradingserver.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
public class StockTradingServiceImpl extends com.javatechie.grpc.StockTradingServiceGrpc.StockTradingServiceImplBase {

    private final StockRepository stockRepository;

    @Override
    public void getStockPrice(com.javatechie.grpc.StockRequest request,
                              io.grpc.stub.StreamObserver<com.javatechie.grpc.StockResponse> responseObserver) {
        String stockSymbol = request.getSymbol();

        Stock stockEntity = stockRepository.findBySymbol(stockSymbol);

        responseObserver.onNext(com.javatechie.grpc.StockResponse.newBuilder()
                .setSymbol(stockEntity.getSymbol())
                .setPrice(101010101)
                .build());
        responseObserver.onCompleted();
    }
}
