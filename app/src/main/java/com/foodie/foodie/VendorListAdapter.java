package com.foodie.foodie;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class VendorListAdapter extends RecyclerView.Adapter<VendorListAdapter.VendorViewHolder> {

    private ArrayList<Vendor> vendors;

    VendorListAdapter(ArrayList<Vendor> vendors) {
        this.vendors = vendors;
    }

    @NonNull
    @Override
    public VendorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.vendor,viewGroup,false);
        return new VendorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorViewHolder vendorViewHolder, int position) {
        vendorViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return vendors.size();
    }

    class VendorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView vendorImage;
        TextView vendorName;

        VendorViewHolder(View itemView) {
            super(itemView);
            vendorImage = itemView.findViewById(R.id.iv_vendorImage);
            vendorName = itemView.findViewById(R.id.tv_vendorName);
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            vendorName.setText(vendors.get(position).getName());
            GlideApp.with(itemView.getContext()).load(vendors.get(position).getImageResource()).diskCacheStrategy(DiskCacheStrategy.ALL).into(vendorImage);
        }

        @Override
        public void onClick(View view) {
            //int clickedPosition = getAdapterPosition();
            Log.d("Foodie-VLA",vendors.get(getAdapterPosition()).getName());
            Intent intent = new Intent(view.getContext(),MenuActivity.class);
            //this would cause problem when the name of vendors are duplicates.
            intent.putExtra("selectedVendor",vendors.get(getAdapterPosition()).getNameInDatabase());
            view.getContext().startActivity(intent);

        }
    }
}
