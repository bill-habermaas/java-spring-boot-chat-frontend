/*
 * Copyright 2025 Habermaas Systems, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.habermaas.webchat.controllers;

import com.habermaas.webchat.configuration.GrpcConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webchat.login.LoginCommandRequest;
import webchat.login.LoginCommandResponse;

@Controller
public class LoginController {

    final GrpcConfiguration grpcConfig;

    public LoginController(GrpcConfiguration grpcConfig) {
        this.grpcConfig = grpcConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
            ){
        System.out.println(username + "/"+ password);
        /*
        LoginCommandResponse resp = grpcConfig.getStub().login(
                LoginCommandRequest.newBuilder()
                        .setCommand(0)
                        .setUsername(username)
                        .setPassword(password)
                        .build());

        String message = resp.getErrorMessage();
        */
        return new ResponseEntity<>("User was not found", HttpStatus.NOT_FOUND);
    }
}
