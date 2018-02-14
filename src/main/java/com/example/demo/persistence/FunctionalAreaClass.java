package com.example.demo.persistence;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;

@Table
@Entity
public class FunctionalAreaClass {
    @Id
    @GeneratedValue
    private long id;

    private String jiraIssueId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "functionalArea_id")
    private FunctionalArea functionalArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_class_id")
    private ModuleClass moduleClass;


    public FunctionalAreaClass(){
    }

    public FunctionalAreaClass(String jiraIssueId){
        this.jiraIssueId = jiraIssueId;
    }

    public FunctionalAreaClass(String jiraIssueId,FunctionalArea functionalArea){
        this.jiraIssueId = jiraIssueId;
        this.functionalArea = functionalArea;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJiraIssueId() {
        return jiraIssueId;
    }

    public void setJiraIssueId(String jiraIssueId) {
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

//    @Override
//    public String toString(){
//        String info = "";
//        JSONObject jsonInfo = new JSONObject();
//        try {
//            jsonInfo.put("jiraId", this.jiraIssueId);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        JSONObject moduleClassObj = new JSONObject();
//        try {
//            moduleClassObj.put("module",this.moduleClass.getModule());
//            moduleClassObj.put("classPath",this.moduleClass.getClassPath());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        JSONObject functionalAreaObj = new JSONObject();
//        JSONObject productAreaObj = new JSONObject();
//
//        try {
//            productAreaObj.put("name",this.functionalArea.getProductArea().getName());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        try {
//            functionalAreaObj.put("name",this.functionalArea.getName());
//            functionalAreaObj.put("productArea",productAreaObj);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        try {
//            jsonInfo.put("moduleClass",moduleClassObj);
//            jsonInfo.put("functionalArea",functionalAreaObj);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        info = jsonInfo.toString();
//        return info;
//    }


}
