package com.example.demo.persistence;

import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;

@Table
@Entity
public class FunctionalArea {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_area_id")
    private ProductArea productArea;

    @OneToOne(mappedBy = "functionalArea")
    private FunctionalAreaClass functionalAreaClass;

    public FunctionalArea(){
    }

    public FunctionalArea(String name){
        this.name = name;

    }

    public FunctionalArea(String name,ProductArea productArea){
        this.name = name;
        this.productArea = productArea;
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


    public ProductArea getProductArea() {
        return productArea;
    }

    public void setProductArea(ProductArea productArea) {
        this.productArea = productArea;
        }

    public FunctionalAreaClass getFunctionalAreaClass() {
        return functionalAreaClass;
    }

    public void setFunctionalAreaClass(FunctionalAreaClass functionalAreaClass) {
        this.functionalAreaClass = functionalAreaClass;
    }

    public String toString(){
        String info ="";
        JSONObject jsonInfo = new JSONObject();
        try {
            jsonInfo.put("name",this.name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject productAreaObj = new JSONObject();
        try {
            productAreaObj.put("name",this.productArea.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonInfo.put("productArea",productAreaObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        info = jsonInfo.toString();
        return info;
    }
}
