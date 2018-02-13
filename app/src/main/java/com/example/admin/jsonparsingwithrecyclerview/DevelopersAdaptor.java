package com.example.admin.jsonparsingwithrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by admin on 2/13/2018.
 */


public class DevelopersAdaptor extends RecyclerView.Adapter<DevelopersAdaptor.MyViewHolder> {
    private Context ctx;
    private List<DevelopersList> developerList;

    public DevelopersAdaptor(Context ctx, List<DevelopersList> developerList) {
        this.ctx = ctx;
        this.developerList = developerList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(ctx).inflate(R.layout.developer_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.login.setText(developerList.get(position).getLogin());
        Picasso.with(ctx).load(developerList.get(position).getAvtar_url()).into(holder.avtar_url);

    }

    @Override
    public int getItemCount() {
        return developerList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView login, html_url;
        private ImageView avtar_url;

        public MyViewHolder(View itemView) {
            super(itemView);
            login = itemView.findViewById(R.id.login);
         //   html_url = itemView.findViewById(R.id.htmlurl);
            avtar_url = itemView.findViewById(R.id.avtarimage);


        }
    }
}

