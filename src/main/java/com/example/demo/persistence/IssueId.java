package com.example.demo.persistence;

import javax.persistence.*;

@Table
@Entity
public class IssueId {
    @Id
    @GeneratedValue
    private long id;

    private String issueId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private Modules modules;

    public IssueId(){
    }

    public IssueId(String issueId){
        this.issueId = issueId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public Modules getModules() {
        return modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }
}
