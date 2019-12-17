package com.epam.model;

public interface Model {

    void validate();

    void parseByJackson();

    void parseByGson();

    void writeToFile();

    void writeToFileSortName();

    void writeToFileSortPayroll();
}
