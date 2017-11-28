package com.example.elashry.expandlistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    List<String> listDataHeader;
    ArrayList<String> list;

    HashMap<String, List<String>> listDataChild;
    final static String api = "http://anwaralfyaha.com/api/stages";
    List<acticitesModel> feedsList = new ArrayList<acticitesModel>();
    RequestQueue queue;
    RecyclerView recyclerView;
    AdapterAds adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Connector connector = new Connector();
       /* recyclerView = (RecyclerView) findViewById(R.id.lvExp);
        adapter = new AdapterAds(this, feedsList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);*/
        //Getting Instance of Volley Request Queue

       /* try {
            ArrayList<acticitesModel> arrayList = parser.JsonProcessActive(connector.execute(api).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        queue = Controller.getInstance().getRequestQueue();

        JsonArrayRequest newsReq = new JsonArrayRequest(api, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject obj = response.getJSONObject(i);
                        acticitesModel feeds = new acticitesModel(obj.getString("stage_id_pk"), obj.getString("stage_name"));

                        // adding movie to movies array
                        feedsList.add(feeds);
                        list=new ArrayList<>();
                        list.add(obj.getString("stage_name"));
                        Toast.makeText(MainActivity.this,list.get(i), Toast.LENGTH_SHORT).show();
                        //   Toast.makeText(MainActivity.this, feedsList.get(2)+"", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        //Notify adapter about data changes
                    }
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
        prepareListData();
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data

        listAdapter = new ExpandableListAdapter(this, list, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

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
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
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
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });


    }

    /*
     * Preparing the list data
     */
    /*private void prepareListData() {
        feedsList = new ArrayList<acticitesModel>();
        listDataChild = new HashMap<>();
        List<String> myGroup = new ArrayList<>();
        myGroup.add("The Shawshank Redemption");
        myGroup.add("The Godfather");
        myGroup.add("The Godfather: Part II");
        myGroup.add("Pulp Fiction");
      listDataChild.put(feedsList.get(2), myGroup);

       *//* listDataHeader.add(String.valueOf(feedsList.get(0)));
        listDataHeader.add(String.valueOf(feedsList.get(1)));
        listDataHeader.add(String.valueOf(feedsList.get(2)));

        listDataChild.put(listDataHeader.get(0), myGroup);
        listDataChild.put(listDataHeader.get(1), myGroup);
        listDataChild.put(listDataHeader.get(2), myGroup);*//*

        //   List<String> list = null;
        // Adding child data

       *//* for (int i=0;i<feedsList.size();i++){
//            if (!listDataHeader.contains(stage_names.get(i))){
        listDataHeader.add(stage_names.get(i));
            // list  = new ArrayList<String>();
          //  }
           *//**//* if (stage_ids.contains(i)){
                list.add(class_title);*//**//*

            List<List<String>> generalList = new ArrayList<>();

            for (int x=0;x<listDataHeader.size();x++){
                List<String> myGroup = new ArrayList<>();
                generalList.add(myGroup);
myGroup.add(String.valueOf(feedsList.get(x)));
      //     for (int y=0;y<myGroup.size();y++) {
                 //   if (stage_id.equals(y)){
               //  myGroup.add(class_titles.get(y));
                   // }else {
                      ///  Toast.makeText(this, stage_id +"    " + y, Toast.LENGTH_SHORT).show();
                     //  }
             //  myGroup.add(class_title);

           *//**//*    if (stage_ids.contains(8)){
                   Toast.makeText(this, stage_id, Toast.LENGTH_SHORT).show();
               myGroup.add(class_title);}else if (stage_ids.contains(3)){
                   Toast.makeText(this, "kkk"+stage_id, Toast.LENGTH_SHORT).show();
                   myGroup.add(class_title);
               }else if (stage_ids.contains(4)){
                   Toast.makeText(this, "kkk"+stage_id, Toast.LENGTH_SHORT).show();
                   myGroup.add(class_title);
               }else if (stage_ids.contains(5)){
                   Toast.makeText(this, "kkk"+stage_id, Toast.LENGTH_SHORT).show();
                   myGroup.add(class_title);
               }else {}
               listDataChild.put(listDataHeader.get(y), myGroup);

           *//**//*
        //   }
              *//**//*  if (!listDataHeader.contains(stage_ids.get(i))){
                    list.add(class_titles.get(i));

                }else {
                    List<String> mlist = new ArrayList<String>();
                    mlist.add(class_titles.get(i));

                }*//**//*

              //  listDataChild.put(listDataHeader.get(x), myGroup);
            //    listDataChild.put(listDataHeader.get(1), class_titles1);
              //  listDataChild.put(listDataHeader.get(2), class_titles2);

                //  listDataChild.put(listDataHeader.get(1), class_titles1);

//                Toast.makeText(this, class_titles.get(0), Toast.LENGTH_SHORT).show();
//                listDataChild.put(listDataHeader.get(1), list2);
//                listDataChild.put(li stDataHeader.get(2), list3);
//                listDataChild.put(listDataHeader.get(3), list4);
//                listDataChild.put(listDataHeader.get(4), list5);
//                listDataChild.put(listDataHeader.get(5), list6);


            }*//*
        //  }

      // Header, Child data

        // Adding child data
       *//* List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");*//*


    }
*/
    private void prepareListData() {
       // listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
//        listDataHeader.add("Top 250");
      //  listDataHeader.add("Now Showing");
      //  listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

       // listDataChild.put(list.get(0), top250); // Header, Child data
       // listDataChild.put(list.get(1), nowShowing);
      //  listDataChild.put(list.get(2), comingSoon);
    }
}
