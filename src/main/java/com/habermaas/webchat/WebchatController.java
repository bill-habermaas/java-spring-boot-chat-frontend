package com.habermaas.webchat;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import webchat.chatc.*;
import webchat.create.CreateRequest;
import webchat.create.CreateResponse;
import webchat.login.LoginCommandRequest;
import webchat.login.LoginCommandResponse;

@RestController
public class WebchatController {

    private ChatcGrpc.ChatcBlockingStub stub;

    WebchatController() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(
                "localhost", 50051)
                .usePlaintext()
                .build();

        stub = ChatcGrpc.newBlockingStub(channel);
    }

    @PostMapping("/login")
        public ResponseEntity<String> login(
                @RequestParam("username") String username,
                @RequestParam("password") String password) {

        LoginCommandResponse resp = stub.login(
                LoginCommandRequest.newBuilder()
                        .setCommand(0)
                        .setUsername("bill")
                        .setPassword("pass")
                        .build());

        return new ResponseEntity<>("xxxx", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(
            @RequestParam("username") String username,
            @RequestParam("password1") String password1,
            @RequestParam("password2") String password2,
            @RequestParam("aliasname") String aliasname,
            @RequestParam("phonenumber") String phonenumber,
            @RequestParam("emailaddress") String emailaddress)
    {
        CreateResponse resp = stub.create(CreateRequest.newBuilder()
                .setUsername(username)
                .setPassword1(password1)
                .setPassword2(password2)
                .setAliasname(aliasname)
                .setPhonenumber(phonenumber)
                .setEmailaddress(emailaddress)
                .build());

        String message = "unknown";
        switch (resp.getStatus()) {
            case CreateSuccess -> message = "";
            case CreateUserExists -> message = "user already exists;";
            case CreatePasswordDiffer -> message = "passwords do not match";
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/getmotd")
    public ResponseEntity<String> getmotd(
            @RequestParam("token") String token,
            @RequestParam("filter") String filter) {
        InfoResponse resp = stub.getinfo(
                InfoRequest.newBuilder()
                        .setToken(token)
                        .setFilter(filter)
                        .build());
        // token is ok. start the motd request
        if ( resp.getStatus().getSuccess() ) {
        }
        return new ResponseEntity<>("yyy", HttpStatus.OK);
    }
}