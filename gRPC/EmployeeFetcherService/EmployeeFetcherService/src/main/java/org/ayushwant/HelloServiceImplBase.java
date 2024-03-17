package org.ayushwant;

import io.grpc.stub.StreamObserver;

public interface HelloServiceImplBase {
    void hello(
            HelloRequest request, StreamObserver<HelloResponse> responseObserver);
}
