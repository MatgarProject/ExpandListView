package com.example.elashry.expandlistview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;



import java.util.List;


public class AdapterAds extends RecyclerView.Adapter<AdapterAds.MyViewHolder> implements View.OnClickListener{

    private List<acticitesModel> feedsList;
    private Context context;
    private LayoutInflater inflater;
    MainActivity mainActivity;

    public AdapterAds(Context context, List<acticitesModel> feedsList, MainActivity mainActivity) {

        this.context = context;
        this.mainActivity = mainActivity;
        this.feedsList = feedsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = inflater.inflate(R.layout.list_group, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        acticitesModel feeds = feedsList.get(position);
        //Pass the values of feeds object to Views


        holder.title.setText(feeds.getName());
        //     holder.content.setText(feeds.getContent());
    }

    @Override
    public int getItemCount() {
        return feedsList.size();
    }


    @Override
    public void onClick(View v) {


    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView content, title;
        private ImageView imageview;
        private ProgressBar ratingbar;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.lblListHeader);

            //    content = (TextView) itemView.findViewById(R.id.content_view);
            // Volley's NetworkImageView which will load Image from URL


        }

        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();

            acticitesModel   feeds = feedsList.get(position);

           /* Toast t=  Toast.makeText(context, feeds.getLink() + "222", Toast.LENGTH_SHORT);
            View vt=t.getView();
            vt.setBackgroundColor(Color.CYAN);

            t.show();*/


         //   Intent i=new Intent(mainActivity,WebViiew.class);


         //   mainActivity.startActivity(i);
        }
    }

}