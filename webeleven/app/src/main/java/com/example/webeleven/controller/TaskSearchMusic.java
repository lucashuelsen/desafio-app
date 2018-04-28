package com.example.webeleven.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.webeleven.model.Music;
import com.example.webeleven.util.NetworkUtils;
import com.example.webeleven.util.Share;
import com.example.webeleven.util.Utils;

import java.util.ArrayList;

public class TaskSearchMusic extends AsyncTask<Void, Void, Boolean>{

    private ProgressDialog progressDialog;
    private Context context = null;
    private ArrayList<Music> arrMusic = null;
    private String term = "";
    private String erro = "";

    public TaskSearchMusic(Context contextParam, String termParam) {
        context = contextParam;
        term = termParam;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        Utils utils = new Utils();
        try {
            if(NetworkUtils.VerificaConexao(context)){
                arrMusic = (ArrayList<Music>) utils.getMusic(getClass(), "entity=musicTrack&term=" + term.replace(" ", "+"));
                return true;
            }
            else
            {
                erro = "Sem Conexão";
                return false;
            }
        } catch (Exception e) {
            erro = e.getMessage();
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        try{
            if(!aBoolean)
            {
                Toast.makeText(context, erro, Toast.LENGTH_SHORT).show();
            }

            ((Share)context).share(getClass(), aBoolean, arrMusic);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
