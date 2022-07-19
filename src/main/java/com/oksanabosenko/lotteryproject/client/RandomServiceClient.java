package com.oksanabosenko.lotteryproject.client;

import com.oksanabosenko.lotteryproject.util.exception.NoDataRetrieved;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Slf4j
@Component
public class RandomServiceClient {

    @Value(value = "${random-number-service.url}")
    private String randomServiceUrl;

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private static final String AMOUNT_RANDOM_NUM = "num";
    private static final String MIN_TO_WIN = "min";
    private static final String MAX_TO_WIN = "max";
    private static final String COLUMNS = "col";
    private static final String BASE = "base";
    private static final String FORMAT = "format";
    private static final String RND = "rnd";

    public Integer getRandomNumber(int minVal, int maxVal) {
        String uri = constructUri(1, String.valueOf(minVal), String.valueOf(maxVal));
        log.info("Constructed uri to make request: {}", uri);
        String randomNumber = REST_TEMPLATE.getForObject(uri, String.class);
        if (Objects.isNull(randomNumber)) {
            log.error("There was no value retrieved from randomizer service");
            throw new NoDataRetrieved("No data was retrieved from randomizer service");
        }
        return Integer.parseInt(randomNumber.trim());
    }

    private String constructUri(int amountOfNumbers, String min, String max) {
        log.info("Constructor URI with path: {}", randomServiceUrl);
        return UriComponentsBuilder.fromUriString(randomServiceUrl)
                .pathSegment("integers")
                .queryParam(AMOUNT_RANDOM_NUM, amountOfNumbers)
                .queryParam(MIN_TO_WIN, min)
                .queryParam(MAX_TO_WIN, max)
                .queryParam(COLUMNS, 1)
                .queryParam(BASE, 10)
                .queryParam(FORMAT, "plain")
                .queryParam(RND, "new")
                .build()
                .toUriString();
    }
}



