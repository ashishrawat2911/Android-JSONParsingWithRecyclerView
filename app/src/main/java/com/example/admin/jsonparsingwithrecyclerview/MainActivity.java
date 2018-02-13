package com.example.admin.jsonparsingwithrecyclerview;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<DevelopersList> developerslists;
    DevelopersAdaptor developersAdaptor;
    private static final String URL_DATA = "https://api.github.com/search/users?q=language:java+location:india";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        developerslists = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();

    }

    private void loadData() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pd.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("items");

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject jo = array.getJSONObject(i);

                        DevelopersList developers = new DevelopersList(
                                jo.getString("login"),
                                jo.getString("html_url"),
                                jo.getString("avatar_url"));
                        developerslists.add(developers);

                    }

                    developersAdaptor = new DevelopersAdaptor(getApplicationContext(), developerslists);
                    recyclerView.setAdapter(developersAdaptor);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
