package com.clinica.clinicaVeterinaria.domain.filtros;

public class BaseFiltroDTO {
    private int pageElements;
    private int pageNumber;
    private String orderBy;
    private boolean orderDesc;
    private boolean pageable;

    public void setPageElements(int pageElements) {
        this.pageElements = pageElements;
    }

    public int getPageElements() {
        return this.pageElements;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public void setOrderDesc(boolean orderDesc) {
        this.orderDesc = orderDesc;
    }

    public boolean getOrderDesc() {
        return this.orderDesc;
    }

    public void setPageable(boolean pageable) {
        this.pageable = pageable;
    }

    public boolean getPageable() {
        return this.pageable;
    }
}