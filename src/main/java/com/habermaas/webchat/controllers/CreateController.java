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
import webchat.create.CreateRequest;
import webchat.create.CreateResponse;

@Controller
public class CreateController {

    final
    GrpcConfiguration grpcConfig;

    public CreateController(GrpcConfiguration grpcConfig) {
        this.grpcConfig = grpcConfig;
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
        CreateResponse resp = grpcConfig.getStub().create(CreateRequest.newBuilder()
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
}
