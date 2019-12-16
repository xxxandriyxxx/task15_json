package com.epam;

import com.epam.model.Tariff;
import com.epam.model.comparator.NameComparator;
import com.epam.model.comparator.PayrollComparator;
import com.epam.model.json.GsonParser;
import com.epam.model.json.JSONValidator;
import com.epam.model.json.JacksonParser;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException, ProcessingException {

        File jsonFile = new File("src/main/resources/tariffs.json");
        File schemaFile = new File("src/main/resources/tariffsSchema.json");
        File newJsonFile = new File("src/main/resources/newTariffs.json");
        File jsonSortedByNameFile = new File("src/main/resources/tariffsSortName.json");
        File jsonSortedByPayrollFile = new File("src/main/resources/tariffsSortPayroll.json");


        System.out.println(JSONValidator.validate(jsonFile, schemaFile));

        GsonParser gsonParser = new GsonParser();
        List<Tariff> tariffs = gsonParser.getTariffList(jsonFile);
        System.out.println(tariffs.toString());
        
        System.out.println("-----------------");
        JacksonParser jacksonParser = new JacksonParser();
        System.out.println(jacksonParser.getTariffList(jsonFile).toString());

        gsonParser.writeToFile(tariffs, newJsonFile);

//        sort
        NameComparator nameComparator = new NameComparator();
        PayrollComparator payrollComparator = new PayrollComparator();

        tariffs.sort(nameComparator);
        gsonParser.writeToFile(tariffs, jsonSortedByNameFile);
        tariffs.sort(payrollComparator);
        gsonParser.writeToFile(tariffs, jsonSortedByPayrollFile);


    }
}
