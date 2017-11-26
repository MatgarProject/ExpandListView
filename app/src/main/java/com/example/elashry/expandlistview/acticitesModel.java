package com.example.elashry.expandlistview;

/**
 * Created by elashry on 11/25/2017.
 */

public class acticitesModel {

    private String id;
    private String name;
    private String cid;
    private String ctitle;

    public acticitesModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public acticitesModel(String id, String name, String cid, String ctitle) {
        this.id = id;
        this.name = name;
        this.cid = cid;
        this.ctitle = ctitle;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCid() {
        return cid;
    }

    public String getCtitle() {
        return ctitle;
    }
}
