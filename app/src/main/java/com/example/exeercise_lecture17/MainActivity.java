package com.example.exeercise_lecture17;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IUserInterfaceClass{

    TextView tv_test;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager  layoutManager;
    ItemAdapter itemAdapter;
    Button btn_create;
    List<User> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rvItem);
        tv_test = findViewById(R.id.tv_test);
        btn_create = findViewById(R.id.btn_create);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivityForResult(intent, 102);
            }
        });
        if(isNetworkAvailable()){
        getUserRequest request = new getUserRequest();
            request.setRequest("https://gorest.co.in/public-api/users");
        new NetWorkAsynTask(this, this).execute(request);
        }

    }

    private boolean isNetworkAvailable(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void loadListUser(List<User> listData) {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        this.listData = new ArrayList<User>();
        this.listData = listData;
        itemAdapter= new ItemAdapter( this, this.listData);
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==102){
            Intent intent = getIntent();

            if(intent!=null){
                String response = data.getStringExtra("arrayUser");
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject meta = object.getJSONObject("_meta");
                    int code = meta.getInt("code");
//                    if(code==200){
                        JSONObject result = object.getJSONObject("result");
                        String first_name = result.getString("first_name");
                        String last_name = result.getString("last_name");
                        String email = result.getString("email");
                        String gender = result.getString("gender");
                        listData.add(new User(first_name, last_name, email, gender));
                        itemAdapter.notifyDataSetChanged();
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                try {
//                    JSONArray results = object.getJSONArray("result");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                User user = new User(response[0], response[1], userArray[2], userArray[3]);
//                this.listData.add(user);
//                itemAdapter.notifyDataSetChanged();
            }
        }
    }
}
