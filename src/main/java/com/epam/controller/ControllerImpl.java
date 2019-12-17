package com.epam.controller;

import com.epam.model.BusinessLogic;
import com.epam.model.Model;

public class ControllerImpl implements Controller {

    private Model model;

    public ControllerImpl() {
        model = new BusinessLogic();
    }

    @Override
    public void validate() {
        model.validate();
    }

    @Override
    public void parseByJackson() {
        model.parseByJackson();
    }

    @Override
    public void parseByGson() {
        model.parseByGson();
    }

    @Override
    public void writeToFile() {
        model.writeToFile();
    }

    @Override
    public void writeToFileSortName() {
        model.writeToFileSortName();
    }

    @Override
    public void writeToFileSortPayroll() {
        model.writeToFileSortPayroll();
    }
}
