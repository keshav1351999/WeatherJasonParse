package com.example.weatherjsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      et=findViewById(R.id.et);
      tv=findViewById(R.id.tv);
    }
     public void get(View v){
       String apikey="44ca2d5bfdf740cc8d665434212611";
       String city=et.getText().toString();
       String url="https://api.weatherapi.com/v1/forecast.json?key=44ca2d5bfdf740cc8d665434212611&q="+city+"&days=1&aqi=no&alerts=no";
         RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
         JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 try {
                     JSONObject object =   response.getJSONObject("current");
                     String temperature = object.getString("temp_c");
                     tv.setText(temperature);
                 } catch (JSONException e) {
                     Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                     e.printStackTrace();

                 }
             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
             }
         });

         queue.add(request);

     }
}