package com.insa.consumer2producer3.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.insa.consumer2producer3.countries.Country;
import com.insa.consumer2producer3.global.Global;

import java.util.List;

@JacksonXmlRootElement(localName = "Data")
public class Data {
    private Global global;

    @JacksonXmlElementWrapper(localName = "countries")
    private List<Country> country;

    public Data(Global globals, List<Country> countries) {
        this.global = globals;
        this.country = countries;
    }

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Data{" +
                "global=" + global +
                ", country=" + country +
                '}';
    }
}

