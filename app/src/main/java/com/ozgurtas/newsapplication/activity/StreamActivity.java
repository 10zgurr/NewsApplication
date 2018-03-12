package com.ozgurtas.newsapplication.activity;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.ozgurtas.newsapplication.NewsApplication;
import com.ozgurtas.newsapplication.R;
import com.ozgurtas.newsapplication.adapter.StreamAdapter;
import com.ozgurtas.newsapplication.databinding.ActivityStreamBinding;
import com.ozgurtas.newsapplication.model.Result;
import com.ozgurtas.newsapplication.model.TopStoryResponse;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StreamActivity extends AppCompatActivity {

    private ActivityStreamBinding binding;
    private ArrayList<Result> results;
    private ProgressDialog dialog;

    //Use calls with @Named annotations
    @Inject
    @Named("world")
    Call<TopStoryResponse> worldSectionCall;
    @Inject
    @Named("tech")
    Call<TopStoryResponse> techSectionCall;
    @Inject
    @Named("sport")
    Call<TopStoryResponse> sportSectionCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stream);

        //Injection
        NewsApplication.getApp().getConnectionComponent().inject(this);

        String type = getIntent().getStringExtra("type");
        getTopStories(type);
    }

    //Make different calls upon type variable
    private void getTopStories(String type) {
        if (type != null) {
            switch (type) {
                case "world":
                    getStream(worldSectionCall);
                    break;
                case "technology":
                    getStream(techSectionCall);
                    break;
                case "sports":
                    getStream(sportSectionCall);
                    break;
            }
        }
    }

    //Get Stream
    private void getStream(Call<TopStoryResponse> call) {
        showLoadingDialog();
        call.enqueue(new Callback<TopStoryResponse>() {
            @Override
            public void onResponse(Call<TopStoryResponse> call, Response<TopStoryResponse> response) {
                hideLoadingDialog();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus() != null) {
                            if (response.body().getStatus().equals("OK")) {
                                if (response.body().getResults() != null) {
                                    if (results == null) {
                                        results = response.body().getResults();
                                    } else {
                                        results.clear();
                                        results = response.body().getResults();
                                    }
                                    StreamAdapter adapter = new StreamAdapter(StreamActivity.this, results);
                                    GridLayoutManager manager = new GridLayoutManager(StreamActivity.this, 1);
                                    binding.streamingList.setLayoutManager(manager);
                                    binding.streamingList.setAdapter(adapter);
                                }
                            }
                        }
                    } else
                        Toast.makeText(StreamActivity.this, "No News Right Now", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TopStoryResponse> call, Throwable t) {
                Log.e("onFailure", t.toString());
            }
        });
    }

    //Show Progress Dialog
    private void showLoadingDialog() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    //Hide Progress Dialog
    private void hideLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
