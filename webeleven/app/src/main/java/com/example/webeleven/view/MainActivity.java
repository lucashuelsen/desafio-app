package com.example.webeleven.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webeleven.R;
import com.example.webeleven.adapters.AdapterResultado;
import com.example.webeleven.controller.TaskSearch;
import com.example.webeleven.model.Response;
import com.example.webeleven.model.Results;
import com.example.webeleven.util.ActivityBase;

import java.util.ArrayList;

public class MainActivity extends ActivityBase implements View.OnClickListener, TextWatcher {
    private EditText txtPesquisa = null;
    private TextView lblDestaque = null;
    private RecyclerView rcvResultado = null;
    private ArrayList<Results> arrResultado = null;
    private AdapterResultado adapterResultado = null;

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

        txtPesquisa.addTextChangedListener(this);
    }

    @Override
    public void carregaDados() {
        preencheListaResultado();
    }

    private void preencheListaResultado()
    {
        if(arrResultado == null)
        {
            arrResultado = new ArrayList<>();
        }

        adapterResultado = new AdapterResultado(this, arrResultado);

        rcvResultado.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        rcvResultado.setAdapter(adapterResultado);
        rcvResultado.setHasFixedSize(true);
        rcvResultado.setNestedScrollingEnabled(true);;

        adapterResultado.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        try{
            new TaskSearch(this, "Iron Maiden").execute();
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

        try {
            if(classe == TaskSearch.class) {
                Response response = (Response) objects[0];

                arrResultado = response.getResults();

                preencheListaResultado();
            }
            else if(classe == AdapterResultado.class)
            {
                startActivity(new Intent(this, FrmDetalhesResultado.class).putExtra("Result", (Results)objects[0]));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        new TaskSearch(this, s.toString()).execute();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
