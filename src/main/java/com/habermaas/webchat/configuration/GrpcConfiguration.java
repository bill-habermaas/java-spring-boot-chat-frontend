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

package com.habermaas.webchat.configuration;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Configuration;
import webchat.chatc.ChatcGrpc;

@Configuration
public class GrpcConfiguration {

    ChatcGrpc.ChatcBlockingStub stub;

    GrpcConfiguration() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(
                        "localhost", 50051)
                .usePlaintext()
                .build();

        stub = ChatcGrpc.newBlockingStub(channel);
    }

    public ChatcGrpc.ChatcBlockingStub getstub() {
        return stub;
    }
}
