package com.example.utkalmerchandise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<ProductResult> mArraylist;
    Context mcontext;

    public RecyclerAdapter(ArrayList<ProductResult> mArraylist, Context mcontext) {
        this.mArraylist = mArraylist;
        this.mcontext = mcontext;


    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mproducttext;
        public TextView mpricetext;
        public ImageView mimageview;
        //public TextView mimageurltext;

        public ViewHolder(final View itemView) {
            super(itemView);

            mproducttext = itemView.findViewById(R.id.productnametext);
            mpricetext = itemView.findViewById(R.id.pricetext);
            mimageview=itemView.findViewById(R.id.productimageview);
            //mimageurltext=itemView.findViewById(R.id.imageurltext);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();

                }
            });

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder mviewholder = new ViewHolder(v);
        return mviewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        ProductResult mProductResult= mArraylist.get(i);

        String productname= mProductResult.getProductname();
        String price=mProductResult.getPrice();
        String imageurl=mProductResult.getImageurl();

        viewHolder.mproducttext.setText("" + productname);
        viewHolder.mpricetext.setText("₹ " + price);
        Picasso.get().load(imageurl).fit().centerInside().into(viewHolder.mimageview);
        //viewHolder.mimageurltext.setText("" + imageurl);
    }

    @Override
    public int getItemCount() {
        return mArraylist.size();
    }

}