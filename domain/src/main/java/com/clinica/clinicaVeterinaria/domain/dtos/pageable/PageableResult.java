package com.clinica.clinicaVeterinaria.domain.dtos.pageable;

import java.util.List;

public class PageableResult<T> {

    private int pageNumber;

    private int count;

    private List<T> results;

    public PageableResult(int pageNumber, int count, List<T> elements) {
        this.pageNumber = pageNumber;
        this.count = count;
        this.results = elements;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
