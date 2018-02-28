package com.example.demo.Data;

import com.example.demo.persistence.Modules;

public class ModuleSaveResponse {

    private String message;
    private Iterable<Modules>  branchList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Iterable<Modules> getBranchList() {
        return branchList;
    }

    public void setBranchList(Iterable<Modules> branchList) {
        this.branchList = branchList;
    }

}
