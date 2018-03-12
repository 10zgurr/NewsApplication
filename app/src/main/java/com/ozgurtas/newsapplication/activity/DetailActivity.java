package com.ozgurtas.newsapplication.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ozgurtas.newsapplication.R;
import com.ozgurtas.newsapplication.databinding.ActivityDetailBinding;
import com.ozgurtas.newsapplication.model.Multimedia;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ozgur on 12.03.2018.
 */

public class DetailActivity extends AppCompatActivity {

    private ArrayList<Multimedia> multimedia;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        //Get data
        String detail = getIntent().getStringExtra("detail");
        url = getIntent().getStringExtra("url");
        if (getIntent().getParcelableArrayListExtra("multimedia") != null)
            multimedia = getIntent().getParcelableArrayListExtra("multimedia");

        //Set data
        if (detail != null)
            binding.detail.setText(detail);

        //Set the largest image "multimedia.get(4)"
        if (multimedia.size() > 0) {
            if (multimedia.get(4) != null) {
                Picasso.with(this).load(multimedia.get(4).getUrl()).into(binding.image);
                binding.caption.setText("Caption: " + multimedia.get(4).getCaption());
                binding.copyright.setText("Copyright: " + multimedia.get(4).getCopyright());
            }
        }

        //Set url
        if (url != null)
            binding.url.setText("More Info: " + url);

        //Go to url
        binding.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (url != null)
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
    }
}
