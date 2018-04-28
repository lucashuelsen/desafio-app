package com.example.webeleven.view;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webeleven.R;
import com.example.webeleven.adapters.AdapterAlbum;
import com.example.webeleven.adapters.AdapterMusic;
import com.example.webeleven.controller.TaskSearchAlbum;
import com.example.webeleven.controller.TaskSearchMusic;
import com.example.webeleven.model.Album;
import com.example.webeleven.model.Music;
import com.example.webeleven.util.ActivityBase;

import java.util.ArrayList;

public class MainActivity extends ActivityBase implements View.OnClickListener, TextWatcher {
    private EditText txtPesquisa = null;
    private TextView lblDestaque = null;
    private RecyclerView rcvResultado = null;
    private RecyclerView rcvDestaques = null;
    private AdapterMusic adapterMusic = null;
    private ProgressBar progressBar = null;

    private ArrayList<Music> arrMusic = null;
    private ArrayList<Album> arrAlbum = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void iniciarControles() {
        txtPesquisa = (EditText) findViewById(R.id.txtPesquisa);
        lblDestaque = (TextView) findViewById(R.id.lblDestaque);
        rcvResultado = (RecyclerView) findViewById(R.id.rcvResultado);
        rcvDestaques = (RecyclerView) findViewById(R.id.rcvDestaques);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        txtPesquisa.addTextChangedListener(this);
    }

    @Override
    public void carregaDados() {
        new TaskSearchAlbum(this, "iron maiden").execute();
    }

    private void preencheListaDestaque()
    {
        if(arrAlbum == null)
        {
            return;
        }

        AdapterAlbum adapterAlbum = new AdapterAlbum(this, arrAlbum);

        rcvDestaques.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false));
        rcvDestaques.setAdapter(adapterAlbum);
        rcvDestaques.setHasFixedSize(true);
        rcvDestaques.setNestedScrollingEnabled(false);;

        adapterAlbum.notifyDataSetChanged();
    }


    private void preencheListaResultado()
    {
        if(arrMusic == null)
        {
            arrMusic = new ArrayList<>();
        }

        adapterMusic = new AdapterMusic(this, arrMusic);

        rcvResultado.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        rcvResultado.setAdapter(adapterMusic);
        rcvResultado.setHasFixedSize(true);
        rcvResultado.setNestedScrollingEnabled(false);;

        adapterMusic.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        try{
            new TaskSearchMusic(this, "Iron Maiden").execute();
        }catch (Exception e){
           Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        }catch (Exception e){

        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onReceiveData(Class classe, boolean result, Object... objects) {
        super.onReceiveData(classe, result, objects);
        DlgDetalhesMusic dlgDetalhesMusic = null;
        DlgDetalhesDestaques dlgDetalhesDestaques = null;

        try {
            progressBar.setVisibility(View.GONE);
            rcvResultado.setVisibility(View.VISIBLE);

            if(classe == TaskSearchMusic.class) {
                if(result) {
                    arrMusic = (ArrayList<Music>) objects[0];

                    preencheListaResultado();
                }
            }
            else if(classe == TaskSearchAlbum.class){
                if(result)
                {
                    arrAlbum = (ArrayList<Album>) objects[0];

                    preencheListaDestaque();
                }
            }
            else if(classe == AdapterAlbum.class){
                dlgDetalhesDestaques = new DlgDetalhesDestaques();
                dlgDetalhesDestaques.album = (Album) objects[0];
                dlgDetalhesDestaques.show(getSupportFragmentManager(),"");
            }
            else if(classe == AdapterMusic.class){
                dlgDetalhesMusic = new DlgDetalhesMusic();
                dlgDetalhesMusic.music = (Music) objects[0];
                dlgDetalhesMusic.show(getSupportFragmentManager(),"");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void pesquisar(String artista) throws Exception
    {
        progressBar.setVisibility(View.VISIBLE);
        rcvResultado.setVisibility(View.GONE);

        new TaskSearchMusic(this, artista).execute();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
            pesquisar(s.toString());
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
