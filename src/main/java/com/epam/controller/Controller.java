package com.epam.controller;

public interface Controller {

    void validate();

    void parseByJackson();

    void parseByGson();

    void writeToFile();

    void writeToFileSortName();

    void writeToFileSortPayroll();
}
