package com.example.elashry.expandlistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import static com.example.elashry.expandlistview.JsonParser.actdata;
import static com.example.elashry.expandlistview.JsonParser.class_title;
import static com.example.elashry.expandlistview.JsonParser.class_titles;
import static com.example.elashry.expandlistview.JsonParser.stage_id;
import static com.example.elashry.expandlistview.JsonParser.stage_ids;
import static com.example.elashry.expandlistview.JsonParser.stage_name;
import static com.example.elashry.expandlistview.JsonParser.stage_names;
import static com.example.elashry.expandlistview.KeyTags.stage_id_pk;

public class MainActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    final static String api = "http://anwaralfyaha.com/api/stages";
    JsonParser parser = new JsonParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connector connector = new Connector();


        try {
            ArrayList<acticitesModel> arrayList = parser.JsonProcessActive(connector.execute(api).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
       prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

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
               Toast.makeText(getApplicationContext(),  stage_id+class_titles.get(0)+class_titles.get(1), Toast.LENGTH_SHORT).show();

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
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
     //   List<String> list = null;
        // Adding child data

        for (int i=0;i<actdata.size();i++){
            if (!listDataHeader.contains(stage_names.get(i))){
        listDataHeader.add(stage_names.get(i));
            // list  = new ArrayList<String>();
            }
           /* if (stage_ids.contains(i)){
                list.add(class_title);*/
            for (int x=0;x<listDataHeader.size();x++){

             //   List<String> list1 = new ArrayList<String>();

         //   for (int y=0;y<stage_ids.size();y++) {
                 //   if (stage_id.equals(y)){
                //    list1.add(class_titles.get(x));
                   // }else {
                      ///  Toast.makeText(this, stage_id +"    " + y, Toast.LENGTH_SHORT).show();
                     //  }

               // }
              /*  if (!listDataHeader.contains(stage_ids.get(i))){
                    list.add(class_titles.get(i));

                }else {
                    List<String> mlist = new ArrayList<String>();
                    mlist.add(class_titles.get(i));

                }*/

                listDataChild.put(listDataHeader.get(x), class_titles);
//                listDataChild.put(listDataHeader.get(1), list2);
//                listDataChild.put(listDataHeader.get(2), list3);
//                listDataChild.put(listDataHeader.get(3), list4);
//                listDataChild.put(listDataHeader.get(4), list5);
//                listDataChild.put(listDataHeader.get(5), list6);


            }
          }

      // Header, Child data

        // Adding child data
       /* List<String> top250 = new ArrayList<String>();
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
        comingSoon.add("Europa Report");*/


    }
}
