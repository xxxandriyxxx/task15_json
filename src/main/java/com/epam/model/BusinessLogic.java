package com.epam.model;

import com.epam.model.comparator.NameComparator;
import com.epam.model.comparator.PayrollComparator;
import com.epam.model.json.MyGsonParser;
import com.epam.model.json.MyJsonValidator;
import com.epam.model.json.MyJacksonParser;
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
            logger.trace("Validation result: " + MyJsonValidator.validate(jsonFile, schemaFile));
        } catch (Exception e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void parseByJackson() {
        try {
            MyJacksonParser myJacksonParser = new MyJacksonParser();
            List<Tariff> tariffs = myJacksonParser.getTariffList(jsonFile);
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
        MyGsonParser myGsonParser = new MyGsonParser();
        List<Tariff> tariffs = null;
        try {
            tariffs = myGsonParser.getTariffList(jsonFile);
        } catch (FileNotFoundException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
        return tariffs;
    }


    @Override
    public void writeToFile() {
        try {
            MyGsonParser myGsonParser = new MyGsonParser();
            List<Tariff> tariffs = parseByGsonHelper();
            myGsonParser.writeToFile(tariffs, newJsonFile);
            logger.info("File 'tariffsNew.json' saved successfully!");
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void writeToFileSortName() {
        try {
            MyGsonParser myGsonParser = new MyGsonParser();
            List<Tariff> tariffs = parseByGsonHelper();
            NameComparator nameComparator = new NameComparator();
            tariffs.sort(nameComparator);
            myGsonParser.writeToFile(tariffs, jsonSortedByNameFile);
            logger.info("File 'tariffsSortName.json' saved successfully!");
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void writeToFileSortPayroll() {
        try {
            PayrollComparator payrollComparator = new PayrollComparator();
            MyGsonParser myGsonParser = new MyGsonParser();
            List<Tariff> tariffs = parseByGsonHelper();
            tariffs.sort(payrollComparator);
            myGsonParser.writeToFile(tariffs, jsonSortedByPayrollFile);
            logger.info("File 'tariffsSortPayroll.json' saved successfully!");
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }

}
