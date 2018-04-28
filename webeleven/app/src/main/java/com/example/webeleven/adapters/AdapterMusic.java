package com.example.webeleven.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.webeleven.R;
import com.example.webeleven.model.Music;
import com.example.webeleven.util.Share;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMusic extends RecyclerView.Adapter implements View.OnClickListener {

    private Context context = null;
    private ArrayList<Music> arrResults = null;

    public AdapterMusic(Context contextParam, ArrayList<Music> arrMusicParam) {
        context = contextParam;
        arrResults = arrMusicParam;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup vwgGrupo, int viewType) {
        View view = null;
        Holder holder = null;

        try
        {
            view = LayoutInflater.from(context).inflate(R.layout.lst_resultado, vwgGrupo, false);
            holder = new Holder(view);
        }
        catch(Exception err)
        {
            err.printStackTrace();
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Music music = null;

        try
        {
            Holder holder = (Holder) viewHolder;

            music = arrResults.get(position);

            Picasso.get().load(music.getArtworkUrl100()).into(holder.imgAlbum);;
            holder.lblMusica.setText(music.getTrackName());
            holder.lblCompositor.setText(music.getArtistName());

            holder.lnlLinha.setOnClickListener(this);
            holder.lnlLinha.setTag(music);
        }
        catch(Exception err)
        {
            err.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return arrResults.size();
    }

    @Override
    public void onClick(View v) {
        ((Share)context).share(getClass(), true, v.getTag());
    }

    private class Holder extends RecyclerView.ViewHolder{
        private final LinearLayout lnlLinha;
        private final ImageView imgAlbum;
        private final TextView lblMusica;
        private final TextView lblCompositor;


        public Holder(View itemView) {
            super(itemView);

            lnlLinha = (LinearLayout) itemView.findViewById(R.id.lnlLinha);
            imgAlbum = (ImageView) itemView.findViewById(R.id.imgAlbum);
            lblMusica = (TextView) itemView.findViewById(R.id.lblMusica);
            lblCompositor = (TextView) itemView.findViewById(R.id.lblCompositor);
        }
    }
}
