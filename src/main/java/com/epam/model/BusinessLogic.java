package com.epam.model;

import com.epam.model.comparator.NameComparator;
import com.epam.model.comparator.PayrollComparator;
import com.epam.model.json.GsonParser;
import com.epam.model.json.JSONValidator;
import com.epam.model.json.JacksonParser;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class BusinessLogic implements Model {

    private File jsonFile;
    private File schemaFile;
    private File newJsonFile;
    private File jsonSortedByNameFile;
    private File jsonSortedByPayrollFile;

    public BusinessLogic() {
        jsonFile = new File("src/main/resources/tariffs.json");
        schemaFile = new File("src/main/resources/tariffsSchema.json");
        newJsonFile = new File("src/main/resources/tariffsNew.json");
        jsonSortedByNameFile = new File("src/main/resources/tariffsSortName.json");
        jsonSortedByPayrollFile = new File("src/main/resources/tariffsSortPayroll.json");
    }

    @Override
    public void validate() {
        try {
            System.out.println(JSONValidator.validate(jsonFile, schemaFile));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parseByJackson() {
        JacksonParser jacksonParser = new JacksonParser();
        try {
            System.out.println(jacksonParser.getTariffList(jsonFile).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parseByGson() {
        List<Tariff> tariffs = parseByGsonHelper();
        System.out.println(tariffs.toString());
    }


    private List<Tariff> parseByGsonHelper() {
        GsonParser gsonParser = new GsonParser();
        List<Tariff> tariffs = null;
        try {
            tariffs = gsonParser.getTariffList(jsonFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tariffs;
    }


    @Override
    public void writeToFile() {
        GsonParser gsonParser = new GsonParser();
        List<Tariff> tariffs = parseByGsonHelper();
        try {
            gsonParser.writeToFile(tariffs, newJsonFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFileSortName() {
        GsonParser gsonParser = new GsonParser();
        List<Tariff> tariffs = parseByGsonHelper();
        NameComparator nameComparator = new NameComparator();
        tariffs.sort(nameComparator);
        try {
            gsonParser.writeToFile(tariffs, jsonSortedByNameFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFileSortPayroll() {
        PayrollComparator payrollComparator = new PayrollComparator();
        GsonParser gsonParser = new GsonParser();
        List<Tariff> tariffs = parseByGsonHelper();
        tariffs.sort(payrollComparator);
        try {
            gsonParser.writeToFile(tariffs, jsonSortedByPayrollFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
