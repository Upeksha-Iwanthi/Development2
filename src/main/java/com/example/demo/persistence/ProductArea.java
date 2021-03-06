package com.example.demo.persistence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table(name = "product_area")
@Entity
public class ProductArea {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "productArea")
    private List<FunctionalArea> fa = new ArrayList<>();

    public ProductArea(){
    }

    public ProductArea(String name){this.name = name;}

    public ProductArea(String name, List<FunctionalArea> fa){
        this.name = name;
        this.fa = fa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FunctionalArea> getFa() {
        return fa;
    }

    public void setFa(List<FunctionalArea> fa) {
        this.fa = fa;
    }

    public String toString(){
        String info = "";
        JSONObject jsonInfo = new JSONObject();
        try {
            jsonInfo.put("name",this.name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray functionalAreaArray = new JSONArray();
        if(this.fa != null){
            this.fa.forEach(fa1->{
                JSONObject subJson = new JSONObject();
                try {
                    subJson.put("name", fa1.getName());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                functionalAreaArray.put(subJson);
            });
        }
        try {
            jsonInfo.put("functionalArea", functionalAreaArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        info = jsonInfo.toString();
        return info;
    }

}
