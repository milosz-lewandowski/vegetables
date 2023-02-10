package com.miloszlewandowski;

import com.miloszlewandowski.micronaut.VegetableCommand;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
public class VegetableValidationControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void vegetableBlankCommandIsValidated() {
        HttpClientResponseException exception = assertThrows(
                HttpClientResponseException.class,
                () -> httpClient.toBlocking().exchange(HttpRequest.POST("/vegetables", new VegetableCommand("", "")))
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }
}
