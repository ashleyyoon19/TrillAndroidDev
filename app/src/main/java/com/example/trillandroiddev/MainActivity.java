package com.example.trillandroiddev;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TextView quoteBlock;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteBlock=findViewById(R.id.quoteBlock);
        pullAPI();
    }

    private void pullAPI(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://quotes.rest/qod.json?";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
//                        Log.d("success", "success");
                        JSONObject content=response.getJSONObject("contents");
                        JSONArray quotesArr=content.getJSONArray("quotes");
                        JSONObject quoteObj=quotesArr.getJSONObject(0);
                        String quote=quoteObj.getString("quote");
                        quoteBlock.setText(quote);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
//                    Log.d("ERROR", "error");
                    quoteBlock.setText("Error");

                });
        queue.add(request);
    }
}
