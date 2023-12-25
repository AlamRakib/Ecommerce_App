package com.example.ecommerceshoeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;

public class CartListViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list_view);

        getroomdata();
    }

    public void getroomdata(){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_databasee").allowMainThreadQueries().build();

        ShopDao shopDao = db.shopDao();
        recyclerView = findViewById(R.id.revViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Shop> shops = shopDao.getallproducts();
        MyAdapter adapter = new MyAdapter(shops);
        recyclerView.setAdapter(adapter);

    }
}