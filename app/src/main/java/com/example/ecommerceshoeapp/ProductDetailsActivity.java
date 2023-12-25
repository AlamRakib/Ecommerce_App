package com.example.ecommerceshoeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    public static Bitmap MyBitmap = null;

    ImageView imageView;

    TextView title,dicount,price,desc,band;
    EditText edName,edDis,edPrice;



    public static String TITLE = "";
    public static String DISCOUNT="";
    public static String PRICE = "";

    Button addtocartbtn;

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        imageView = findViewById(R.id.detailActivityShoeIV);
        title = findViewById(R.id.detailActivityShoeNameTv);
        dicount = findViewById(R.id.detailActivityShoeBrandNameTv);
        price = findViewById(R.id.detailActivityShoePriceTv);
        addtocartbtn = findViewById(R.id.detailActivityAddToCartBtn);
        edName = findViewById(R.id.NameId);
        edDis = findViewById(R.id.DiscountId);
        edPrice = findViewById(R.id.priceId);

        sharedPreferences = getSharedPreferences(""+getString(R.string.app_name),MODE_PRIVATE);

        SharedPreferences.Editor  editor = sharedPreferences.edit();


        title.setText(TITLE);
        dicount.setText(DISCOUNT);
        price.setText(PRICE);

        if(MyBitmap!=null)
        {
            imageView.setImageBitmap(MyBitmap);
        }

        String pName = title.getText().toString();
        editor.putString("title",""+pName);
        editor.commit();

        String pDiscount = dicount.getText().toString();
        editor.putString("discount",""+pDiscount);
        editor.commit();

        String pPrice = price.getText().toString();
        editor.putString("price",""+pPrice);
        editor.commit();


        String Name = sharedPreferences.getString("title",null);
        String Discount = sharedPreferences.getString("discount",null);
        String Price = sharedPreferences.getString("price",null);

        edName.setText(Name);
        edDis.setText(Discount);
        edPrice.setText(Price);


        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        //AppDatabase.class, "room_dbb").allowMainThreadQueries().build();

                //ShopDao shopDao = db.shopDao();

                //shopDao.insertrecord(new Shop(title.getText().toString(),(dicount.getText().toString()),(Long.parseLong(price.getText().toString()))));
                //title.setText("");
                //t.setText("");
                //t3.setText("");
                //lb1.setText("Inserted Successfully");



                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_databasee").allowMainThreadQueries().build();

                        ShopDao shopDao = db.shopDao();

                        shopDao.insertrecord(new Shop(edName.getText().toString(),(Double.parseDouble(edDis.getText().toString())),(Long.parseLong(edPrice.getText().toString()))));





                Intent intent = new Intent(getApplicationContext(), CartListViewActivity.class);
                startActivity(intent);


            }
        });

    }
}