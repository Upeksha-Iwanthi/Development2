package com.example.demo.persistence;

import javax.persistence.*;

@Table
@Entity
public class IssueId {
    @Id
    @GeneratedValue
    private long id;

    private String issueId;

    @ManyToOne
    @JoinColumn(name = "module_class_id")
    private ModuleClass moduleClass;

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

    public ModuleClass getModules() {
        return moduleClass;
    }

    public void setModules(ModuleClass moduleClass) {
        this.moduleClass = moduleClass;
    }
}
