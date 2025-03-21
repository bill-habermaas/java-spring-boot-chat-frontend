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
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import webchat.chatc.ChatcGrpc;
import webchat.chatc.DbinitRequest;
import webchat.common.StatusResponse;

@Getter
@Component
public class GrpcConfiguration {

    private final String host;
    private final int port;
    private final String dbspec;
    private final String dbname;

    ChatcGrpc.ChatcBlockingStub stub;

    //@Autowired
    public GrpcConfiguration(@Value("${grpc.host}") String host,
                             @Value("${grpc.port}") int port,
                             @Value("${mongo.dbspec}") String dbspec,
                             @Value("${mongo.database}") String dbname
                  ) {
        this.host = host;
        this.port = port;
        this.dbspec = dbspec;
        this.dbname = dbname;
        /*
        ManagedChannel channel = ManagedChannelBuilder.forAddress(
                            host, port)
                .usePlaintext()
                .build();

        stub = ChatcGrpc.newBlockingStub(channel);

        DbinitRequest dbinit = DbinitRequest.newBuilder()
                .setDbspec(dbspec)
                .setDatabase(dbname)
                .build();

        try {
            StatusResponse resp = stub.dbinit(dbinit);
            System.out.println(resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().toString());
            System.exit(900);
        }
         */
    }
}
