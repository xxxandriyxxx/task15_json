package com.epam.model.json;

import com.epam.model.Tariff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class GsonParser {

    private Gson gson;

    public GsonParser() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    public List<Tariff> getTariffList(File jsonFile) throws FileNotFoundException {
        Tariff[] tariffs;
        tariffs = gson.fromJson(new FileReader(jsonFile), Tariff[].class);
        return Arrays.asList(tariffs);
    }

    public void writeToFile(List<Tariff> tariffs, File jsonFile) throws IOException {
        Writer writer = new FileWriter(jsonFile);
        writer.write(gson.toJson(tariffs));
//        gson.toJson(tariffs, writer); // it can also by used
        writer.close();
    }
}
