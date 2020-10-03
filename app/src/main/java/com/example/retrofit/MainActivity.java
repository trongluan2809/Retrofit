package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.retrofit.Retrofit.APIManager;
import com.example.retrofit.Retrofit.RetrofitClient;
import com.example.retrofit.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    ArrayList<Video> mListVideo;
    VideoAdapter mVideoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mListVideo = new ArrayList<>();
        getData();
        mVideoAdapter = new VideoAdapter(mListVideo, getBaseContext());
        binding.rcListVideo.setLayoutManager(new GridLayoutManager(getBaseContext(), 3,  RecyclerView.VERTICAL, false));
        binding.rcListVideo.setAdapter(mVideoAdapter);
    }
    public void getData(){
        APIManager service = RetrofitClient.getRetrofitClient(APIManager.BaseUrl).create(APIManager.class);
        Call<List<Video>>call = service.getDataVideo();
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                List<Video> mList = response.body();
                for(int i = 0; i < mList.size(); i++){
                    String title = mList.get(i).getTitle();
                    String thumb = mList.get(i).getThumb();
                    mListVideo.add(new Video(title, thumb));
                }
                mVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
