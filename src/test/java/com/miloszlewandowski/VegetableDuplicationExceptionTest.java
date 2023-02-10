package com.miloszlewandowski;

import com.miloszlewandowski.micronaut.VegetableCommand;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VegetableDuplicationExceptionTest extends BaseTest{
    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void duplicatedVegetableShouldReturn422() {
        VegetableCommand tomato = new VegetableCommand("Tomato");
        HttpRequest<?> request = HttpRequest.POST("/vegetables", tomato);
        HttpResponse<?> createResponse = httpClient.toBlocking().exchange(request);
        assertEquals(HttpStatus.CREATED, createResponse.status());
        HttpRequest<?> request2 = HttpRequest.POST("/vegetables", tomato);
        HttpClientResponseException exception = assertThrows(
                HttpClientResponseException.class,
                () -> httpClient.toBlocking().exchange(request2)
        );
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getStatus());
        HttpRequest<?> deleteRequest = HttpRequest.DELETE("/vegetables", tomato);
        HttpResponse<?> deleteResponse = httpClient.toBlocking().exchange(deleteRequest);
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.status());
    }
}