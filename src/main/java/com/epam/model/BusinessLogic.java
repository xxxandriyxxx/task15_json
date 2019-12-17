package com.epam.model;

import com.epam.model.comparator.NameComparator;
import com.epam.model.comparator.PayrollComparator;
import com.epam.model.json.GsonParser;
import com.epam.model.json.JSONValidator;
import com.epam.model.json.JacksonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BusinessLogic implements Model {

    private Logger logger;
    private File jsonFile;
    private File schemaFile;
    private File newJsonFile;
    private File jsonSortedByNameFile;
    private File jsonSortedByPayrollFile;

    public BusinessLogic() {
        logger = LogManager.getLogger(BusinessLogic.class);
        jsonFile = new File("src/main/resources/tariffs.json");
        schemaFile = new File("src/main/resources/tariffsSchema.json");
        newJsonFile = new File("src/main/resources/tariffsNew.json");
        jsonSortedByNameFile = new File("src/main/resources/tariffsSortName.json");
        jsonSortedByPayrollFile = new File("src/main/resources/tariffsSortPayroll.json");
    }

    @Override
    public void validate() {
        try {
            logger.trace("Validation result: " + JSONValidator.validate(jsonFile, schemaFile));
        } catch (Exception e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void parseByJackson() {
        try {
            JacksonParser jacksonParser = new JacksonParser();
            List<Tariff> tariffs = jacksonParser.getTariffList(jsonFile);
            for (Tariff t : tariffs) {
                logger.trace(t.toString());
            }
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void parseByGson() {
        List<Tariff> tariffs = parseByGsonHelper();
        for (Tariff t : tariffs) {
            logger.trace(t.toString());
        }
    }


    private List<Tariff> parseByGsonHelper() {
        GsonParser gsonParser = new GsonParser();
        List<Tariff> tariffs = null;
        try {
            tariffs = gsonParser.getTariffList(jsonFile);
        } catch (FileNotFoundException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
        return tariffs;
    }


    @Override
    public void writeToFile() {
        try {
            GsonParser gsonParser = new GsonParser();
            List<Tariff> tariffs = parseByGsonHelper();
            gsonParser.writeToFile(tariffs, newJsonFile);
            logger.info("File 'tariffsNew.json' saved successfully!");
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void writeToFileSortName() {
        try {
            GsonParser gsonParser = new GsonParser();
            List<Tariff> tariffs = parseByGsonHelper();
            NameComparator nameComparator = new NameComparator();
            tariffs.sort(nameComparator);
            gsonParser.writeToFile(tariffs, jsonSortedByNameFile);
            logger.info("File 'tariffsSortName.json' saved successfully!");
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void writeToFileSortPayroll() {
        try {
            PayrollComparator payrollComparator = new PayrollComparator();
            GsonParser gsonParser = new GsonParser();
            List<Tariff> tariffs = parseByGsonHelper();
            tariffs.sort(payrollComparator);
            gsonParser.writeToFile(tariffs, jsonSortedByPayrollFile);
            logger.info("File 'tariffsSortPayroll.json' saved successfully!");
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }

}
