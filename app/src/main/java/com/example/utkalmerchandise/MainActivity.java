package com.example.utkalmerchandise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String url="https://www.utkalmerchandise.com/wp-json/wc/v3/products?consumer_key=ck_a4caf13505a9bc857839aeb9e36997e1ba09ce98&consumer_secret=cs_bb0f74492f9eed5b89388abc3db86e9d315d71ff";

    ArrayList<ProductResult> mArraylist;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout mcollapsingtoolbarlayout = findViewById(R.id.collapsing_toolbar);
        mcollapsingtoolbarlayout.setTitle("Utkal Enterprises");
        mcollapsingtoolbarlayout.setContentScrimColor(Color.RED);
        mcollapsingtoolbarlayout.setCollapsedTitleTextColor(Color.WHITE);
        mcollapsingtoolbarlayout.setExpandedTitleColor(Color.WHITE);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        mArraylist=new ArrayList<>();



        RequestQueue mrequestqueue= Volley.newRequestQueue(this);

        JsonArrayRequest mjsonarrayrequest= new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for(int i=0; i<response.length();i++){
                        JSONObject mjsonobject= response.getJSONObject(i);
                        String jproductname=mjsonobject.getString("name");
                        String jprice= mjsonobject.getString("price");
                        String jimageurl= mjsonobject.getJSONArray("images").getJSONObject(0).getString("src");
                        mArraylist.add(new ProductResult(jproductname,jprice,jimageurl));
                    }

                    RecyclerView.Adapter adapter = new RecyclerAdapter(mArraylist,MainActivity.this);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mrequestqueue.add(mjsonarrayrequest);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }
}