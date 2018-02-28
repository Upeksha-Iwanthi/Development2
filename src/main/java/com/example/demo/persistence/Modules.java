package com.example.demo.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
public class Modules {
    @Id
    @GeneratedValue
    private long id;

    private String svnURL;

    private long revision;

    private String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "modules")
    private List<IssueId> issueList = new ArrayList<>();

    public Modules(){}

    public Modules(String svnURL, long revision){
        this.svnURL=svnURL;
        this.revision = revision;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSvnURL() {
        return svnURL;
    }

    public void setSvnURL(String svnURL) {
        this.svnURL = svnURL;
    }

    public long getRevision() {
        return revision;
    }

    public void setRevision(long revision) {
        this.revision = revision;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<IssueId> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<IssueId> issueList) {
        this.issueList = issueList;
    }
}
