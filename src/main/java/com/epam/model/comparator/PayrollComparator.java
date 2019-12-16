package com.epam.model.comparator;

import com.epam.model.Tariff;

import java.util.Comparator;

public class PayrollComparator implements Comparator<Tariff> {

    @Override
    public int compare(Tariff o1, Tariff o2) {
        if (o1.getPayroll() == o2.getPayroll()) {
            return 0;
        } else if (o1.getPayroll() > o2.getPayroll()) {
            return 1;
        } else {
            return -1;
        }
    }
}
