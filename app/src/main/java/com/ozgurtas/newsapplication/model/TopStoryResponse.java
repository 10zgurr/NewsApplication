package com.ozgurtas.newsapplication.model;

import java.util.ArrayList;

/**
 * Created by Ozgur on 11.03.2018.
 */

public class TopStoryResponse {
    private String status;
    private String copyright;
    private String section;
    private String last_updated;
    private Integer num_results;
    private ArrayList<Result> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public Integer getNum_results() {
        return num_results;
    }

    public void setNum_results(Integer num_results) {
        this.num_results = num_results;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}
