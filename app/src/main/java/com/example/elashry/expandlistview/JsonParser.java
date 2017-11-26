package com.example.elashry.expandlistview;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonParser {


  public static   ArrayList<acticitesModel> actdata;
//    public static String namesc,phonesc,emailsc ,faxsc,addressc;
    public static String stage_name ,class_title,stage_id;
    public static   ArrayList<String> stage_names=new ArrayList<>();
    public static   ArrayList<String> stage_ids=new ArrayList<>();
    public static   ArrayList<String> class_titles=new ArrayList<>();
    public ArrayList<acticitesModel> JsonProcessActive(String jsonFile) {

        actdata = new ArrayList<>();

        try {

            JSONArray jsonArray = new JSONArray(jsonFile);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject mainObjectArray = jsonArray.getJSONObject(i);

                acticitesModel enca = new acticitesModel(
                        mainObjectArray.getString(KeyTags.stage_id_pk),mainObjectArray.getString(KeyTags.stage_name),mainObjectArray.getString(KeyTags.class_id_pk),mainObjectArray.getString(KeyTags.class_title));
                actdata.add(enca);
               // stage_name=mainObjectArray.getString(KeyTags.stage_name);
                stage_id=mainObjectArray.getString(KeyTags.stage_id_pk);
                class_title=mainObjectArray.getString(KeyTags.class_title);
                stage_names.add(mainObjectArray.getString(KeyTags.stage_name));
                stage_ids.add(mainObjectArray.getString(KeyTags.stage_id_pk));
                if (stage_id.equals(3)){
                class_titles.add(mainObjectArray.getString(KeyTags.class_title));}
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actdata;
    }
    public ArrayList<acticitesModel> getlist() {
        return actdata;
    }


}
