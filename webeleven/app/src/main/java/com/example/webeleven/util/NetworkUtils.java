package com.example.webeleven.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class NetworkUtils
{
    public static final String URL = "https://itunes.apple.com/search?";
    public static final String TERMS_CONFIGURATION = "&country=BR&limit=20";

    public  static JSONObject getJSONFromAPI(String params) throws Exception{
        JSONObject retorno = null;
        StringBuilder sbUrl = null;

        try
        {
            sbUrl = new StringBuilder();
            sbUrl.append(URL);
            sbUrl.append(params);
            sbUrl.append(TERMS_CONFIGURATION);

            URL apiEndereco = new URL(sbUrl.toString());
            int codResposta;
            HttpURLConnection httpURLConnection;
            InputStream inputStream;

            httpURLConnection = (HttpURLConnection) apiEndereco.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();

            codResposta = httpURLConnection.getResponseCode();

            if(codResposta < HttpURLConnection.HTTP_BAD_REQUEST)
            {
                inputStream = httpURLConnection.getInputStream();
            }
            else
            {
                inputStream = httpURLConnection.getErrorStream();
            }

            retorno = new JSONObject(converterInputStreamToString(inputStream));

            inputStream.close();
            httpURLConnection.disconnect();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    private static String converterInputStreamToString(InputStream inputStream) throws Exception
    {
        StringBuffer stringBuffer = new StringBuffer();

        try
        {
            BufferedReader bufferedReader;
            String linha;

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while((linha = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(linha);
            }

            bufferedReader.close();
        }catch (IOException e)
        {
            return e.getMessage();
        }

        return stringBuffer.toString();
    }

    public static boolean VerificaConexao(Context contexto){
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }
}
