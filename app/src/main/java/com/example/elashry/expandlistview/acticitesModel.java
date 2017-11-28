package com.example.elashry.expandlistview;

/**
 * Created by elashry on 11/25/2017.
 */

public class acticitesModel {

    private String id;
    private String name;
    private String cid;
    private String ctitle;
    private String stage_id_fk;

    public acticitesModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public acticitesModel(String cid, String ctitle, String stage_id_fk) {
        this.cid = cid;
        this.ctitle = ctitle;
        this.stage_id_fk = stage_id_fk;
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

    public String getStage_id_fk() {
        return stage_id_fk;
    }
}