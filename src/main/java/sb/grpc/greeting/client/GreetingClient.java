package sb.grpc.greeting.client;

import com.proto.greeting.GreetingRequest;
import com.proto.greeting.GreetingResponse;
import com.proto.greeting.GreetingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class GreetingClient {

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            System.out.println("Need one argument to work");
            return;
        }

        // Channel will create tcp connection between client and the server.
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        switch (args[0]) {
            case "greet": doGreet(channel); break;
            case "greetManyTimes" : doGreetManyTimes(channel); break;
            case "longGreet" : doLongGreet(channel); break;
            case "greetEveryone": doGreetEveryone(channel); break;
            default: System.out.println("Keyword Invalid: " + args[0]);
        }

        System.out.println("Shutting Down");
        channel.shutdown();
    }

    private static void doGreet(ManagedChannel channel) {
        System.out.println("Enter doGreet");
        // The stub will make us feel that we are calling a service directly on the server.
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingResponse response = stub.greet(GreetingRequest.newBuilder().setFirstName("SauravB").build());

        System.out.println("Greeting: " + response.getResult());
    }

    private static void doGreetManyTimes(ManagedChannel channel) {
        System.out.println("Enter doGreetManyTimes");
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        stub.greetManyTimes(GreetingRequest.newBuilder().setFirstName("SauravB").build()).forEachRemaining(response ->
                System.out.println(response.getResult())
        );
    }

    private static void doLongGreet(ManagedChannel channel) throws InterruptedException {
        System.out.println("Enter doLongGreet");
        // Asynchronous stub
        GreetingServiceGrpc.GreetingServiceStub stub = GreetingServiceGrpc.newStub(channel);

        //longGreet function from server is returning a stream observer.
        List<String> names = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);
        Collections.addAll(names,"Clement","Marie","SauravB");
        StreamObserver<GreetingRequest> stream = stub.longGreet(new StreamObserver<GreetingResponse>() {
            // inside this longGreet StreamObserver(inside stream observer) is used on the client side.
            // the outside stream observer "stream" is used for sending request to the server.
            @Override
            public void onNext(GreetingResponse response) {
                System.out.println(response.getResult());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
              latch.countDown();
            }
        });

        for(String name:names){
            stream.onNext(GreetingRequest.newBuilder().setFirstName(name).build());
        }

        stream.onCompleted();
        latch.await(3, TimeUnit.SECONDS);
    }

    private static void doGreetEveryone(ManagedChannel channel) throws InterruptedException {
        System.out.println("Enter doGreetEveryone");
        GreetingServiceGrpc.GreetingServiceStub stub = GreetingServiceGrpc.newStub(channel);
        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<GreetingRequest> stream = stub.greetEveryone(new StreamObserver<GreetingResponse>() {
            @Override
            public void onNext(GreetingResponse response) {
                System.out.println(response.getResult());
            }

            @Override
            public void onError(Throwable t) {}

            @Override
            public void onCompleted() {
               latch.countDown();
            }
        });

        Arrays.asList("Clement","Marie","SauravB").forEach(name -> stream.onNext(GreetingRequest.newBuilder().setFirstName(name).build()));
        stream.onCompleted();
        latch.await(3,TimeUnit.SECONDS);
    }
}
