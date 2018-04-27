package com.example.webeleven.model;

import java.util.ArrayList;

public class Response {

    private int resultCount;

    private ArrayList<Results> results;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }
}
