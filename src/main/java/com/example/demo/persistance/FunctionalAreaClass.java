package com.example.demo.persistance;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Set;

@Table
@Entity
public class FunctionalAreaClass {
    @Id
    @GeneratedValue
    private long id;

    private long jiraIssueId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "functionalArea_id")
    private FunctionalArea functionalArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_class_id")
    private ModuleClass moduleClass;


    public FunctionalAreaClass(){
    }

    public FunctionalAreaClass(long jiraIssueId){
        this.jiraIssueId = jiraIssueId;
    }

    public FunctionalAreaClass(long jiraIssueId,FunctionalArea functionalArea){
        this.jiraIssueId = jiraIssueId;
        this.moduleClass = moduleClass;
        this.functionalArea = functionalArea;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getJiraIssueId() {
        return jiraIssueId;
    }

    public void setJiraIssueId(long jiraIssueId) {
        this.jiraIssueId = jiraIssueId;
    }

    public ModuleClass getModuleClass() {
        return moduleClass;
    }

    public void setModuleClass(ModuleClass moduleClass) {
        this.moduleClass = moduleClass;
    }


    public FunctionalArea getFunctionalArea() {
        return functionalArea;
    }

    public void setFunctionalArea(FunctionalArea functionalArea) {
        this.functionalArea = functionalArea;
    }

    @Override
    public String toString(){
        String info = "";
        JSONObject jsonInfo = new JSONObject();
        try {
            jsonInfo.put("jiraId", this.jiraIssueId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject moduleClassObj = new JSONObject();
        try {
            moduleClassObj.put("module",this.moduleClass.getModule());
            moduleClassObj.put("classPath",this.moduleClass.getClassPath());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject functionalAreaObj = new JSONObject();
        try {
            functionalAreaObj.put("name",this.functionalArea.getName());
            functionalAreaObj.put("productArea",this.functionalArea.getProductArea());
            functionalAreaObj.put("functionalAreaClass",this.functionalArea.getFunctionalAreaClass());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonInfo.put("moduleClass",moduleClassObj);
            jsonInfo.put("functionalArea",functionalAreaObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        info = jsonInfo.toString();
        return info;
    }


}
