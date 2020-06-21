package com.example.exeercise_lecture17;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>  {
    List<User> listData;
    Context c;
    public ItemAdapter(Context c, List<User> listData){
        this.c = c;
        this.listData= listData;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new MyViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = listData.get(position);
        holder.bindData(user);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_avt;
        TextView tv_name;
        TextView tv_address;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_avt = itemView.findViewById(R.id.iv_avt);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_address = itemView.findViewById(R.id.tv_address);
        }
        public void bindData(User user)  {
            String href = user.getHref();
//            loadImage load = new loadImage(iv_avt);
//            load.execute(href);
            tv_name.setText(user.getFirstName()+ " " + user.getLastName());
            tv_address.setText(user.getAddress());
        }
        private class loadImage extends AsyncTask<String, Void, Bitmap>{
            ImageView imageView;
            public loadImage(ImageView iv_avt){
                imageView = iv_avt;
            }
            @Override
            protected Bitmap doInBackground(String... strings) {
                String urlLink = strings[0];
                Bitmap bitmap = null;
                try {
                    InputStream inputStream = new java.net.URL(urlLink).openStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                iv_avt.setImageBitmap(bitmap);
            }
        }
    }
}

