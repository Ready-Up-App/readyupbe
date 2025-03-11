package com.readyup.ri.configuration;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.cosmos.*;
import com.azure.cosmos.models.CosmosClientTelemetryConfig;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableCosmosRepositories(basePackages = "com/readyup/ri/repository/jpa")
public class DBConfiguration extends AbstractCosmosConfiguration {
    @Value("${azure.cosmosdb.uri}")
    private String uri;

    @Value("${azure.cosmosdb.key}")
    private String key;

    @Value("${azure.cosmosdb.database}")
    private String dbName;


    private AzureKeyCredential azureKeyCredential;

    @Bean
    public CosmosClientBuilder getCosmosClientBuilder() {
        this.azureKeyCredential = new AzureKeyCredential(key);
        DirectConnectionConfig directConnectionConfig = new DirectConnectionConfig();
        GatewayConnectionConfig gatewayConnectionConfig = new GatewayConnectionConfig();
        return new CosmosClientBuilder()
                .endpoint(uri)
                .credential(azureKeyCredential)
                .directMode(directConnectionConfig, gatewayConnectionConfig)
                .clientTelemetryConfig(
                        new CosmosClientTelemetryConfig()
                                .diagnosticsThresholds(
                                        new CosmosDiagnosticsThresholds()
//                                                .setNonPointOperationLatencyThreshold(Duration.ofMillis(nonPointOperationLatencyThresholdInMS))
//                                                .setPointOperationLatencyThreshold(Duration.ofMillis(pointOperationLatencyThresholdInMS))
//                                                .setPayloadSizeThreshold(payloadSizeThresholdInBytes)
//                                                .setRequestChargeThreshold(requestChargeThresholdInRU)
                                )
                                .diagnosticsHandler(CosmosDiagnosticsHandler.DEFAULT_LOGGING_HANDLER));
    }

    @Override
    public CosmosConfig cosmosConfig() {
        return CosmosConfig.builder()
//                .enableQueryMetrics(queryMetricsEnabled)
//                .enableIndexMetrics(indexMetricsEnabled)
//                .maxDegreeOfParallelism(maxDegreeOfParallelism)
//                .maxBufferedItemCount(maxBufferedItemCount)
//                .responseContinuationTokenLimitInKb(responseContinuationTokenLimitInKb)
//                .responseDiagnosticsProcessor(new ResponseDiagnosticsProcessorImplementation())
                .build();
    }

    @Override
    protected String getDatabaseName() {
        return dbName;
    }
}
