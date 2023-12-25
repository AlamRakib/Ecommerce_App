package com.example.ecommerceshoeapp;

import static com.example.ecommerceshoeapp.ProductDetailsActivity.MyBitmap;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myviewholder> {

    List<Shop> shops;

    public MyAdapter(List<Shop> shops) {
        this.shops = shops;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartsample_view,parent,false);

        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {


        holder.pname.setText(shops.get(position).getProductName());
        holder.discount.setText(String.valueOf(shops.get(position).getDiscount()));
        holder.price.setText(String.valueOf(shops.get(position).getPrice()));

        if(MyBitmap!=null)
        {
                holder.pimage.setImageBitmap(MyBitmap);

        }

        holder.dltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(holder.dltBtn.getContext());
                alertDialogBuilder.setMessage("Do you want to delete from cart");
                alertDialogBuilder.setCancelable(false);


                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AppDatabase db = Room.databaseBuilder(holder.dltBtn.getContext(),
                                AppDatabase.class, "room_databasee").allowMainThreadQueries().build();

                        ShopDao shopDao = db.shopDao();

                        shopDao.deleterecord(shops.get(position).getUid());

                        shops.remove(position);

                        notifyDataSetChanged();

                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.cancel();

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return shops.size();
    }



    class  myviewholder extends RecyclerView.ViewHolder
    {

        TextView pname,discount, price;

        ImageView pimage,dltBtn;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            pname = itemView.findViewById(R.id.eachCartItemName);
            discount = itemView.findViewById(R.id.eachCartItemBrandNameTv);
            price = itemView.findViewById(R.id.eachCartItemPriceTv);
            pimage = itemView.findViewById(R.id.eachCartItemIV);
            dltBtn = itemView.findViewById(R.id.eachCartItemDeleteBtn);





        }
    }
}

