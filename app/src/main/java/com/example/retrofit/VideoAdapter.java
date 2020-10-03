package com.example.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    ArrayList<Video> mListVideo;
    Context mContext;
    public VideoAdapter(ArrayList<Video> listVideo, Context mContext) {
        mListVideo = listVideo;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.video, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Video video = mListVideo.get(position);
        Glide.with(mContext).load(video.getThumb()).into(holder.imageVideo);
        holder.tvNameVideo.setText(video.getTitle());
    }

    @Override
    public int getItemCount() {
        return mListVideo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageVideo;
        TextView tvNameVideo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageVideo = itemView.findViewById(R.id.imageVideo);
            tvNameVideo = itemView.findViewById(R.id.tvNameVideo);
        }
    }
}
