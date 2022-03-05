package dev.matyaqubov.networkingjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PosterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    MainActivity activity;
   ArrayList<Poster> items;

    public PosterAdapter(MainActivity activity, ArrayList<Poster> items) {
        this.activity = activity;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Poster poster = items.get(position);

        if (holder instanceof PosterViewHolder) {
            ((PosterViewHolder) holder).tv_title.setText(poster.title);
            ((PosterViewHolder) holder).tv_body.setText(poster.body);
            ((PosterViewHolder) holder).ll_poster.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    activity.dialogPoster(poster);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PosterViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        TextView tv_body;
        LinearLayout ll_poster;

        public PosterViewHolder(View view){
            super(view);
            tv_title = view.findViewById(R.id.tv_title);
            tv_body = view.findViewById(R.id.tv_body);
            ll_poster=view.findViewById(R.id.ll_poster);
        }



    }
}
