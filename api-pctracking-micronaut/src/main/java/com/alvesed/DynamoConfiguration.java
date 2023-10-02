package com.alvesed;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.ConfigurationProperties;
import jakarta.validation.constraints.NotBlank;

@Requires(property = "dynamodb.table-name")
@ConfigurationProperties("dynamodb")
public interface DynamoConfiguration {
    @NotBlank
    String getTableName();
}