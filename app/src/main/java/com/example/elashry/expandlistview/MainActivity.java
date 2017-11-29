package com.example.elashry.expandlistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<acticitesModel> list;
    ArrayList<String> listtitle;

    HashMap<String, List<String>> listDataChild;
    final static String api = "http://anwaralfyaha.com/api/stages";
    final static String capi = "http://anwaralfyaha.com/api/classes";

    List<acticitesModel> feedsList = new ArrayList<acticitesModel>();
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Controller.getInstance().getRequestQueue();

        JsonArrayRequest newsReq = new JsonArrayRequest(api, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                list=new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject obj = response.getJSONObject(i);
                        acticitesModel feeds = new acticitesModel(obj.getString("stage_id_pk"), obj.getString("stage_name"));

                    list.add(feeds);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        //Notify adapter about data changes
                    }
                }
                if (list.size()>0){
                    getfeed(list);
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                System.out.println(error.getMessage());
            }
        });
        //Adding JsonArrayRequest to Request Queue
        queue.add(newsReq);


      //  prepareListData();
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data

      //  listAdapter = new ExpandableListAdapter(this, list, listDataChild);

        // setting list adapter
//        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
           //     Toast.makeText(getApplicationContext(), stage_name +stage_id+class_title, Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(),listDataHeader.get(0)+listDataHeader.get(1)+listDataHeader.get(2), Toast.LENGTH_SHORT).show();
//               Toast.makeText(getApplicationContext(),  stage_id+class_titles.get(0)+class_titles.get(1), Toast.LENGTH_SHORT).show();
//             Toast.makeText(getApplicationContext(),stage_ids.get(4)+ stage_ids.get(5)+ stage_ids.get(6)+ stage_ids.get(0)+ stage_ids.get(1)+ stage_ids.get(2), Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(),
                        list.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        list.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        list.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                list.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });


    }

    public acticitesModel getfeed(final ArrayList<acticitesModel> feed){


        JsonArrayRequest newsReqq = new JsonArrayRequest(capi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
              listtitle=new ArrayList<>();
                listDataChild = new HashMap<String, List<String>>();

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject obj = response.getJSONObject(i);
                        acticitesModel feeds = new acticitesModel(obj.getString("class_id_pk"),obj.getString("stage_id_fk"), obj.getString("class_title"));

                        for (int x=0;x<feed.size();x++){
                            if (feeds.getStage_id_fk().equals(feed.get(x).getId())){
                                listtitle.add(feeds.getCtitle());
                                listDataChild.put(feed.get(x).getName(),listtitle);
                            }
                        }
//                     if (feeds.getStage_id_fk().equals(feed.getId())){
//                         listtitle.add(feeds.getCtitle());
//                         listDataChild.put(feed.getName(),listtitle);
//                     }

                        // }
                        //  Toast.makeText(MainActivity.this, feedsList.get(2)+"", Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        //Notify adapter about data changes
                    }
                }

                if (listtitle.size()>0&&listDataChild.size()>0){
                listAdapter = new ExpandableListAdapter(MainActivity.this, listDataChild);

                expListView.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();

            }}

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                System.out.println(error.getMessage());
            }
        });
        //Adding JsonArrayRequest to Request Queue
        queue.add(newsReqq);
        return null;
    }


}
