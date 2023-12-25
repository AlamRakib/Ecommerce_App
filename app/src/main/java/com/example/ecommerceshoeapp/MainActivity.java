package com.example.ecommerceshoeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap<String,String> hashMap ;

    ImageView imgcart;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridViewId);
        progressBar = findViewById(R.id.progressBarId);
        imgcart = findViewById(R.id.imgcart);


        imgcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), CartListViewActivity.class));
            }
        });




        String url ="https://rakibalam.000webhostapp.com/apps/complex.json";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressBar.setVisibility(View.GONE);

                try {

                    JSONArray jsonArray = response.getJSONArray("products");


                    for(int x=0; x<jsonArray.length();x++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(x);
                        String title = jsonObject.getString("title");
                        String discount = jsonObject.getString("discount");
                        String price = jsonObject.getString("price");
                        String image_url = jsonObject.getString("image_url");

                        hashMap = new HashMap<>();
                        hashMap.put("title",title);
                        hashMap.put("discount",discount);
                        hashMap.put("price",price);
                        hashMap.put("image_url",image_url);
                        arrayList.add(hashMap);



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Server Error!",Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);



        MyAdapter myadapter = new MyAdapter();
        gridView.setAdapter(myadapter);
    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.sample_view, viewGroup, false);


            TextView titleTextView = myView.findViewById(R.id.titleTextViewId);
            TextView priceTextView = myView.findViewById(R.id.priceTextViewId);
            TextView dicountTextView = myView.findViewById(R.id.discountTextViewId);
            ImageView imageView  = myView.findViewById(R.id.imageViewId);
            RelativeLayout relativeLayout = myView.findViewById(R.id.layItemId);


            HashMap<String,String> hashMap = arrayList.get(position);

            String title =  hashMap.get("title");
            String price =  hashMap.get("price");
            String discount =  hashMap.get("discount");
            String image_url= hashMap.get("image_url");


            titleTextView.setText(title);
            priceTextView.setText(price);
            dicountTextView.setText(discount);

            Picasso.get()
                    .load(image_url)
                    .placeholder(R.drawable.baseline_image_24)
                    .into(imageView);

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ProductDetailsActivity.TITLE = title;
                    ProductDetailsActivity.DISCOUNT = discount;
                    ProductDetailsActivity.PRICE = price;

                    Bitmap bitmap = ( (BitmapDrawable) imageView.getDrawable() ).getBitmap();
                    ProductDetailsActivity.MyBitmap = bitmap;

                    startActivity(new Intent(getApplicationContext(),ProductDetailsActivity.class));


                }
            });




            return myView;
        }

    }
}