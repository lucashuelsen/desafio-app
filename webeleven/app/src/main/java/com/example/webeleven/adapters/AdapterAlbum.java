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
import com.example.webeleven.model.Album;
import com.example.webeleven.util.Share;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterAlbum extends RecyclerView.Adapter implements View.OnClickListener {

    private Context context = null;
    private ArrayList<Album> arrAlbum = null;

    public AdapterAlbum(Context contextParam, ArrayList<Album> arrAlbumParam) {
        context = contextParam;
        arrAlbum = arrAlbumParam;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup vwgGrupo, int viewType) {
        View view = null;
        Holder holder = null;

        try
        {
            view = LayoutInflater.from(context).inflate(R.layout.lst_destaques, vwgGrupo, false);
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
        Album album = null;

        try
        {
            Holder holder = (Holder) viewHolder;

            album = arrAlbum.get(position);

            holder.lblTitulo.setText(album.getCollectionName());
            holder.lblArtista.setText(album.getArtistName());
            Picasso.get().load(album.getArtworkUrl100()).into(holder.imgAlbum);;

            holder.lnlLinha.setOnClickListener(this);
            holder.lnlLinha.setTag(album);

        }
        catch(Exception err)
        {
            err.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return arrAlbum.size();
    }

    @Override
    public void onClick(View v) {
        ((Share)context).share(getClass(), true, v.getTag());
    }

    private class Holder extends RecyclerView.ViewHolder{
        private final LinearLayout lnlLinha;
        private final ImageView imgAlbum;
        private final TextView lblTitulo;
        private final TextView lblArtista;


        public Holder(View itemView) {
            super(itemView);
            lnlLinha = (LinearLayout) itemView.findViewById(R.id.lnlLinha);
            imgAlbum = (ImageView) itemView.findViewById(R.id.imgAlbum);
            lblTitulo = (TextView) itemView.findViewById(R.id.lblTitulo);
            lblArtista = (TextView) itemView.findViewById(R.id.lblArtista);
        }
    }
}
