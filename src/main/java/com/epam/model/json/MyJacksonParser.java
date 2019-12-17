package com.epam.model.json;

import com.epam.model.Tariff;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MyJacksonParser {

    private ObjectMapper objectMapper;

    public MyJacksonParser() {
        objectMapper = new ObjectMapper();
    }

    public List<Tariff> getTariffList(File jsonFile) throws IOException {
        Tariff[] tariffs;
        tariffs = objectMapper.readValue(jsonFile, Tariff[].class);
        return Arrays.asList(tariffs);
    }
}
