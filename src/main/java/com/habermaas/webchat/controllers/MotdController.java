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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webchat.chatc.InfoRequest;
import webchat.chatc.InfoResponse;

public class MotdController {

    final
    GrpcConfiguration grpcConfig;

    public MotdController(GrpcConfiguration grpcConfig) {
        this.grpcConfig = grpcConfig;
    }

    @PostMapping("/getmotd")
    public ResponseEntity<String> getmotd(
            @RequestParam("token") String token,
            @RequestParam("filter") String filter) {
        InfoResponse resp = grpcConfig.getstub().getinfo(
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
