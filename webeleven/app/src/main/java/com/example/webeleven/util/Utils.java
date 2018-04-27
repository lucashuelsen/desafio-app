package com.example.webeleven.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.webeleven.model.Response;
import com.example.webeleven.model.Results;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Utils
{
    public Response getJson(String url) throws Exception
    {
        JSONObject jsonResponse = null;
        Response response = null;
        Response responseRetorno = null;

        jsonResponse = NetworkUtils.getJSONFromAPI(url);

        if(jsonResponse != null) {
            response = new Gson().fromJson(jsonResponse.toString(), Response.class);
        }

        responseRetorno = new Response();

        responseRetorno.setResultCount(response.getResultCount());
        responseRetorno.setResults(new ArrayList<Results>());

        for(Results results : response.getResults())
        {
            responseRetorno.getResults().add(results);
        }

        return responseRetorno;
    }
}
