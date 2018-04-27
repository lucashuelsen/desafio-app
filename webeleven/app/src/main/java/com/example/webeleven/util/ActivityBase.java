package com.example.webeleven.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class ActivityBase extends AppCompatActivity implements Share{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            iniciarControles();

            carregaDados();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public abstract void iniciarControles();

    public abstract void carregaDados();

    public void onReceiveData(Class classe, boolean result, Object... objects){


    }

    @Override
    public void share(Class classe, boolean result, Object... objects) {
        try
        {
            onReceiveData(classe, result, objects);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
