package com.habermaas.webchat;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import webchat.AuthGrpc;
import webchat.CreateRequest;
import webchat.LoginRequest;
import webchat.LoginResponse;


@RestController
public class WebchatController {

    @PostMapping("/login")
        public ResponseEntity<String> login(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        AuthGrpc.AuthBlockingStub stub =
                AuthGrpc.newBlockingStub(channel);

        LoginResponse resp = stub.login(
                LoginRequest.newBuilder()
                        .setUsername("bill")
                        .setPassword("pass")
                        .build());

        return new ResponseEntity<>("xxxx", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("password2") String password2) {
        return new ResponseEntity<>("yyy", HttpStatus.OK);
    }
}