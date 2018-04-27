package com.example.webeleven.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.webeleven.model.Response;
import com.example.webeleven.util.Share;
import com.example.webeleven.util.Utils;

public class TaskSearch extends AsyncTask<Void, Void, Boolean>{

    private ProgressDialog progressDialog;
    private Context context = null;
    private Response response = null;
    private String term = "";

    public TaskSearch(Context contextParam, String termParam) {
        context = contextParam;
        term = termParam;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        try {
           // progressDialog = ProgressDialog.show(context, "Aguarde", "aguarde");
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        Utils utils = new Utils();
        try {
            response = utils.getJson("term="+term.replace(" ","+")+"&entity=musicTrack&limit=5");

            return true;
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        try{
            ((Share)context).share(getClass(), aBoolean, response);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally {
            //progressDialog.dismiss();
        }
    }
}
