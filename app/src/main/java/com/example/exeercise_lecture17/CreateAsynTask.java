package com.example.exeercise_lecture17;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import androidx.annotation.NonNull;

public class CreateAsynTask extends AsyncTask<getUserRequest, Void, String> {

    User user;
    ICreateUser iCreateUser;
    public CreateAsynTask( @NonNull Context c, User user, ICreateUser iCreateUser){

        this.user = user;
        this.iCreateUser = iCreateUser;
    }



    public void setiCreateInterface(ICreateUser iCreateUser){
        this.iCreateUser = iCreateUser;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(getUserRequest... getUserRequests) {
        getUserRequest request = getUserRequests[0];
        String response = APIClient.createUser(request.getRequest(), user );
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iCreateUser.ResponseCreate(s);

    }
}
