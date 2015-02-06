/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.eureka2.config;

import com.netflix.eureka2.registry.datacenter.LocalDataCenterInfo;
import com.netflix.eureka2.registry.eviction.EvictionStrategyProvider.StrategyType;
import com.netflix.eureka2.server.config.EurekaCommonConfig;
import com.netflix.eureka2.transport.EurekaTransports;
import com.netflix.governator.annotations.Configuration;

/**
 * @author Tomasz Bak
 */
public class EurekaDashboardConfig extends EurekaCommonConfig {

    public static final int DEFAULT_DASHBOARD_PORT = 7001;
    public static final int DEFAULT_WEBSOCKET_PORT = 9000;

    @Configuration("eureka.dashboard.http.port")
    protected int dashboardPort = DEFAULT_DASHBOARD_PORT;

    @Configuration("eureka.dashboard.websocket.port")
    private int webSocketPort = DEFAULT_WEBSOCKET_PORT;


    // For property injection
    protected EurekaDashboardConfig() {
    }

    protected EurekaDashboardConfig(
            // common configs
            ResolverType resolverType,
            String[] serverList,
            String appName,
            String vipAddress,
            LocalDataCenterInfo.DataCenterType dataCenterType,
            Integer shutDownPort,
            Integer webAdminPort,
            Long connectionAutoTimeoutMs,
            EurekaTransports.Codec codec,
            Long evictionTimeoutMs,
            StrategyType evictionStrategyType,
            String evictionStrategyValue,
            // dashboard server configs
            Integer dashboardPort,
            Integer webSocketPort
    ) {
        super(
                resolverType,
                serverList,
                appName,
                vipAddress,
                dataCenterType,
                shutDownPort,
                webAdminPort,
                connectionAutoTimeoutMs,
                codec,
                evictionTimeoutMs,
                evictionStrategyType,
                evictionStrategyValue
        );

        this.dashboardPort = dashboardPort == null ? this.dashboardPort : dashboardPort;
        this.webSocketPort = webSocketPort == null ? this.webSocketPort : webSocketPort;
    }

    public int getDashboardPort() {
        return dashboardPort;
    }

    public int getWebSocketPort() {
        return webSocketPort;
    }

    public static EurekaDashboardConfigBuilder newBuilder() {
        return new EurekaDashboardConfigBuilder();
    }


    // builder
    public static class EurekaDashboardConfigBuilder
            extends EurekaCommonConfigBuilder<EurekaDashboardConfig, EurekaDashboardConfigBuilder> {

        protected Integer dashboardPort;
        protected Integer webSocketPort;

        protected EurekaDashboardConfigBuilder() {}

        public EurekaDashboardConfigBuilder withDashboardPort(int dashboardPort) {
            this.dashboardPort = dashboardPort;
            return self();
        }

        public EurekaDashboardConfigBuilder withWebSocketPort(int webSocketPort) {
            this.webSocketPort = webSocketPort;
            return self();
        }

        public EurekaDashboardConfig build() {
            return new EurekaDashboardConfig(
                    resolverType,
                    serverList,
                    appName,
                    vipAddress,
                    dataCenterType,
                    shutDownPort,
                    webAdminPort,
                    connectionAutoTimeoutMs,
                    codec,
                    evictionTimeoutMs,
                    evictionStrategyType,
                    evictionStrategyValue,
                    // dashboard server configs
                    dashboardPort,
                    webSocketPort
            );
        }
    }



}
