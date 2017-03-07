package com.example.toieclearning.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.toieclearning.Api.ApiHelper;
import com.example.toieclearning.Api.ApiRequest;
import com.example.toieclearning.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiHelper.setContext(getApplicationContext());
        JSONObject object = new JSONObject();
        try {
            object.put("email","minh.nguyenvan95@gmail.com");
            object.put("password","123456");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest apiRequest = new ApiRequest(Request.Method.POST, ApiHelper.API_URL + "/user/login", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    String status = (String) response.get("status");
                    JSONObject message = response.getJSONObject("message");

                    if(status == "success") {
                        String email = (String) message.get("email");
                        String password = (String) response.get("password");
                        String api_token = (String) response.get("api_token");
                        ApiHelper.API_TOKEN = api_token;
                    }
                    //TODO: fail????
                    //TODO: LUU VAO SHARED REFERENCE
                    //test thu update
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getCause();
                //.makeText(MainActivity.this, , Toast.LENGTH_SHORT).show();
            }
        });
        ApiHelper.addToRequestQueue(apiRequest,"dang_nhap");
    }
}
