package com.example.demo.persistance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Set;

@Table
@Entity
public class ModuleClass {
    @Id
    @GeneratedValue
    private long id;

    private String module;

    private String classPath;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moduleClass")
    private Set<FunctionalAreaClass> functionalAreaClasses;

    public ModuleClass(){
    }

    public ModuleClass(String module, String classPath){
        this.module = module;
        this.classPath = classPath;
    }

//    public ModuleClass(String module, String classPath, Set<FunctionalAreaClass> functionalAreaClasses){
//        this.module = module;
//        this.classPath = classPath;
//        this.functionalAreaClasses = functionalAreaClasses;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public Set<FunctionalAreaClass> getFunctionalAreaClasses() {
        return functionalAreaClasses;
    }

    public void setFunctionalAreaClasses(Set<FunctionalAreaClass> functionalAreaClasses) {
        this.functionalAreaClasses = functionalAreaClasses;
    }

    @Override
    public String toString(){
        String info = "";
        JSONObject jsonInfo = new JSONObject();
        try {
            jsonInfo.put("module",this.module);
            jsonInfo.put( "classPath",this.classPath);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray faArray = new JSONArray();
        this.functionalAreaClasses.forEach(fa->{
            JSONObject faJson = new JSONObject();
            try {
                faJson.put("jiraIssueId", fa.getJiraIssueId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            faArray.put(faJson);
        });
        try {
            jsonInfo.put("functionalAreas", faArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        info = jsonInfo.toString();
        return info;
    }
}
