package com.example.webeleven.view;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.webeleven.R;
import com.example.webeleven.util.ActivityBase;

public class FrmDetalhesResultado extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.frm_detalhes_resultado);
        super.onCreate(savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        getWindow().setLayout((int)(displayMetrics.widthPixels * .8),(int)(displayMetrics.heightPixels * .8));
    }

    @Override
    public void iniciarControles() {

    }

    @Override
    public void carregaDados() {

    }
}
