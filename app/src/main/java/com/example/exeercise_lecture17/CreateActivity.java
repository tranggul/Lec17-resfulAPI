package com.example.exeercise_lecture17;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CreateActivity extends AppCompatActivity implements ICreateUser {
    EditText ed_firstName;
    EditText ed_lastName;
    EditText ed_email;
    EditText ed_gender;
    Button btn_done;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        ed_firstName = findViewById(R.id.ed_firstName);
        ed_lastName = findViewById(R.id.ed_lastName);
        ed_email = findViewById(R.id.ed_email);
        ed_gender = findViewById(R.id.ed_gender);
        btn_done= findViewById(R.id.btn_done);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = ed_firstName.getText().toString();
                String lastName = ed_lastName.getText().toString();
                String email = ed_email.getText().toString();
                String gender = ed_gender.getText().toString();
                User user = new User(firstName, lastName, email, gender);
                if(isNetworkAvailable()){
                    getUserRequest request = new getUserRequest();
                    request.setRequest("https://gorest.co.in/public-api/users");
                    new CreateAsynTask(CreateActivity.this, user, CreateActivity.this).execute(request);
                }


            }
        });

    }
    private boolean isNetworkAvailable(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }


    @Override
    public void ResponseCreate(String response) {
        Intent intent = new Intent();
        intent.putExtra("arrayUser", response);
        setResult(RESULT_OK, intent);
        finish();
    }
}
