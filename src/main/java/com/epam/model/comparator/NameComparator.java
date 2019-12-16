package com.epam.model.comparator;

import com.epam.model.Tariff;

import java.util.Comparator;

public class NameComparator implements Comparator<Tariff> {

    @Override
    public int compare(Tariff o1, Tariff o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
