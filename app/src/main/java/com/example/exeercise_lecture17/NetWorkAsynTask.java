package com.example.exeercise_lecture17;

import android.content.Context;
import android.os.AsyncTask;


import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetWorkAsynTask extends AsyncTask<getUserRequest, Void, String> {
  IUserInterfaceClass iUserInterfaceClass;

    public NetWorkAsynTask(@NonNull  Context context, IUserInterfaceClass iUserInterfaceClass){
    this.iUserInterfaceClass = iUserInterfaceClass;
    }
    public void setLoaderProgressListener(IUserInterfaceClass iUserInterfaceClass){
        this.iUserInterfaceClass = iUserInterfaceClass;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(getUserRequest... getUserRequests) {
        getUserRequest request = getUserRequests[0];
        String response = APIClient.getUserRequest(request.getRequest());
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        List<User> listData = new ArrayList<User>();
        try {
            JSONObject object = new JSONObject(s);
            JSONArray results = object.getJSONArray("result");
            for(int i= 0; i< results.length();i++){
                JSONObject user = results.getJSONObject(i);
                String id = user.getString("id");
                String first_name = user.getString("first_name");
                String last_name = user.getString("last_name");
                String address = user.getString("address");
                JSONObject links = user.getJSONObject("_links");
                JSONObject avatar = links.getJSONObject("avatar");
                String href = avatar.getString("href");
                listData.add(new User(id, first_name, last_name, address, href));
            }
            iUserInterfaceClass.loadListUser(listData);

        } catch ( JSONException e){
            e.printStackTrace();
        }


    }
}
