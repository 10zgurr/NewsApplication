package com.ozgurtas.newsapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ozgurtas.newsapplication.R;
import com.ozgurtas.newsapplication.activity.DetailActivity;
import com.ozgurtas.newsapplication.databinding.CardViewNewsItemBinding;
import com.ozgurtas.newsapplication.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ozgur on 11.03.2018.
 */

public class StreamAdapter extends RecyclerView.Adapter {

    private CardViewNewsItemBinding binding;
    private Context context;
    private ArrayList<Result> results;

    public StreamAdapter(Context context, ArrayList<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.card_view_news_item, parent, false);
        return new StreamViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Set the section and the title
        if (results.get(position) != null) {
            if (results.get(position).getSubsection() != null)
                binding.subsection.setText("Section: " + results.get(position).getSubsection());
            if (results.get(position).getTitle() != null)
                binding.title.setText("Title: " + results.get(position).getTitle());

            //Show YY-MM-DD format to the User
            if (results.get(position).getPublished_date() != null) {
                if (results.get(position).getPublished_date().contains("T")) {
                    String[] date = results.get(position).getPublished_date().split("T");
                    if (date[0] != null)
                        binding.byline.setText("Published Date: " + date[0]);
                }
            }

            //Set the thumbnail
            if (results.get(position).getMultimedia() != null) {
                if (results.get(position).getMultimedia().size() > 0) {
                    if (results.get(position).getMultimedia().get(0) != null) {
                        if (results.get(position).getMultimedia().get(0).getUrl() != null) {
                            Picasso.with(context).load(results.get(position).getMultimedia().get(0).getUrl()).into(binding.thumbnail);
                        }
                    }
                }
            }

            //More detail about the news
            binding.frame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (results.get(position).getDetail() != null && results.get(position).getMultimedia() != null) {
                        if (!results.get(position).getDetail().isEmpty()) {
                            Intent intent = new Intent(context, DetailActivity.class);
                            intent.putExtra("detail", results.get(position).getDetail());
                            intent.putParcelableArrayListExtra("multimedia", results.get(position).getMultimedia());
                            intent.putExtra("url", results.get(position).getUrl());
                            context.startActivity(intent);
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class StreamViewHolder extends RecyclerView.ViewHolder {
        StreamViewHolder(View view) {
            super(view);
        }
    }
}
