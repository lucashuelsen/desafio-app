package com.example.webeleven.util;

import com.example.webeleven.controller.TaskSearchAlbum;
import com.example.webeleven.controller.TaskSearchMusic;
import com.example.webeleven.model.Album;
import com.example.webeleven.model.Music;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;

public class Utils
{
    public Object getMusic(Class classe, String url) throws Exception
    {
        JSONObject jsonResponse = null;
        ArrayList<Music> arrMusic = null;
        ArrayList<Album> arrAlbum = null;

        jsonResponse = NetworkUtils.getJSONFromAPI(url);

        if(jsonResponse != null) {
            if (classe == TaskSearchMusic.class) {
                arrMusic = new Gson().fromJson(jsonResponse.getJSONArray("results").toString(), new TypeToken<ArrayList<Music>>() {
                }.getType());

                return arrMusic;
            } else if (classe == TaskSearchAlbum.class) {
                arrAlbum = new Gson().fromJson(jsonResponse.getJSONArray("results").toString(), new TypeToken<ArrayList<Album>>() {
                }.getType());

                return arrAlbum;
            }
        }

        return null;
    }
}
