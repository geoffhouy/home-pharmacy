package edu.wvu.statler.lcsee.cs450.group4.homepharmacy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.MainActivity;

import static android.content.ContentValues.TAG;

public class Notifier extends BroadcastReceiver {
    Context c;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, NotificationActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        notificationIntent.putExtras(intent);
        context.startActivity(notificationIntent);
        c = context;
        //TODO
        //notifyAlexa("Take Pill "+intent.getExtras().getString("PillName"));

        Bundle extra = intent.getExtras();
        Log.d(TAG, "PROGRESS: Getting to notifier" + extra.getString("PillName"));
    }

//    public void notifyAlexa(String message){
//
//        String accessCode = "amzn1.ask.account.AGLR4VVM4MX6CCBCFZIUAQG4TDY465KMMRT7AE4VVHX2N5JJJZZD3XX7SB55A7YZY6UBNZQEE7MOIH2G6VLGKJUDQHIOUKAON75RKJV47DLQX6ZUTOOIEMKBKSTURQGTIPBPQC6RXHVJLKXIMWVP45S63G4FXPI25NJAG6CBKZPG2NDAMYJQ2MXRHPWEDIAHQJ5OARP46DFOUWA";
//        RequestQueue rq = Volley.newRequestQueue(c);
//        HttpsURLConnection connection = null;
//        String targetURL = "https://api.notifymyecho.com/v1/NotifyMe";
//
//        String urlParameters = null;
//        try {
//            urlParameters = "notification=" + URLEncoder.encode(message, "UTF-8")+ "&accessCode="+URLEncoder.encode(accessCode, "UTF-8");
////            System.out.println(urlParameters);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        StringRequest request = new StringRequest(Request.Method.POST, targetURL+"?"+urlParameters, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        rq.add(request);
//
//        urlParameters.replace("+", "%20");
//        try {
//            //Create connection
//            URL url = new URL(targetURL+"?"+urlParameters);
//            System.out.println(url.toString());
//            connection = (HttpsURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type",
//                    "application/x-www-form-urlencoded");
//
//            connection.setRequestProperty("Content-Length",
//                    Integer.toString(urlParameters.getBytes().length));
//            connection.setRequestProperty("Content-Language", "en-US");
//
//            connection.setUseCaches(false);
//            connection.setDoOutput(true);
//
//            //Send request
//            DataOutputStream wr = new DataOutputStream (
//                    connection.getOutputStream());
//            wr.writeBytes(urlParameters);
//            wr.close();
//
//            //Get Response
//            InputStream is = connection.getInputStream();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
//            String line;
//            while ((line = rd.readLine()) != null) {
//                response.append(line);
//                response.append('\r');
//            }
//            rd.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//        }
//    }
}