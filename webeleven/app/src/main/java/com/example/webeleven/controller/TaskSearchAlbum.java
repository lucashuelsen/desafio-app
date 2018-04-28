package com.example.webeleven.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.webeleven.model.Album;
import com.example.webeleven.util.NetworkUtils;
import com.example.webeleven.util.Share;
import com.example.webeleven.util.Utils;

import java.util.ArrayList;

public class TaskSearchAlbum extends AsyncTask<Void, Void, Boolean>{

    private ProgressDialog progressDialog;
    private Context context = null;
    private ArrayList<Album> arrAlbum = null;
    private String term = "";
    private String erro = "";

    public TaskSearchAlbum(Context contextParam, String termParam) {
        context = contextParam;
        term = termParam;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        try {
            progressDialog = ProgressDialog.show(context, "Aguarde", "aguarde");
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        Utils utils = new Utils();
        try {
            if(NetworkUtils.VerificaConexao(context)){
                arrAlbum = (ArrayList<Album>) utils.getMusic(getClass(), "entity=album&term=" + term.replace(" ", "+"));
                return true;
            }
            else
            {

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

            ((Share)context).share(getClass(), aBoolean, arrAlbum);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally {
            progressDialog.dismiss();
        }
    }
}
