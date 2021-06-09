package com.redisgeek.function.acre.export;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.profile.AzureProfile;
import com.azure.identity.AzureAuthorityHosts;
import com.azure.identity.EnvironmentCredentialBuilder;
import com.azure.resourcemanager.AzureResourceManager;
import com.azure.resourcemanager.redisenterprise.RedisEnterpriseManager;
import com.azure.resourcemanager.redisenterprise.models.Cluster;
import com.azure.resourcemanager.redisenterprise.models.Database;
import com.azure.resourcemanager.redisenterprise.models.ExportClusterParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Function;

@Component
public class Export implements Function<Mono<Optional<String>>, Mono<String>> {

    @Value("${acre_id}")
    private String acre_id;

    @Value("${acre_db_id}")
    private String acre_db_id;

    @Value("${rg_name}")
    private String rg_name;

//    @Value("${accountSasUri}")
//    private String accountSasUri;

    @Value("${blobSasUri}")
    private String blobSasUri;

    @Value("${storageKey}")
    private String storageKey;

//    @Value("${storageName}")
//    private String storageName;
//
//    @Value("${AZURE_SUBSCRIPTION_ID}")
//    private String azure_subscription_id;

    public Mono<String> apply(Mono<Optional<String>> request) {
        try {
            TokenCredential credential = new EnvironmentCredentialBuilder()
                    .authorityHost(AzureAuthorityHosts.AZURE_PUBLIC_CLOUD)
                    .build();
            AzureProfile profile = new AzureProfile(AzureEnvironment.AZURE);
            RedisEnterpriseManager redisEnterpriseManager = RedisEnterpriseManager
                    .authenticate(credential, profile);
//            AzureResourceManager.Authenticated azureResourceManager = AzureResourceManager.configure()
//                    .withLogLevel(HttpLogDetailLevel.BASIC)
//                    .authenticate(credential, profile);

            Cluster cluster = redisEnterpriseManager.redisEnterprises().getById(acre_id);
            Database database = redisEnterpriseManager.databases().getById(acre_db_id);
            ExportClusterParameters exportClusterParameters =
                    new ExportClusterParameters().withSasUri(blobSasUri + ";" + storageKey);
            exportClusterParameters.validate();
            redisEnterpriseManager
                    .databases()
                    .export(rg_name,cluster.name(), database.name(), exportClusterParameters);
            return Mono.just("Export Complete");
        } catch (Exception e) {
            return Mono.just(e.getMessage());
        }
    }
}