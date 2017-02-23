package com.example.sun.umstouristsapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sun on 24/01/2017.
 */

public class Packages_Adapter extends RecyclerView.Adapter<Packages_Adapter.MyViewHolder> {

    private List<Packages_Model> modelList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, location;
        public ImageView image;
        public RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout);
            title = (TextView) view.findViewById(R.id.list_title);
//            location = (TextView) view.findViewById(R.id.list_location);
//            year = (TextView) view.findViewById(R.id.list_dateconstructed);
            image = (ImageView) view.findViewById(R.id.list_imageview);
        }
    }

    public Packages_Adapter (List<Packages_Model> modelList) {
        this.modelList = modelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_packages_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Packages_Model packages_model = modelList.get(position);
        holder.title.setText(packages_model.getTitle());
//        holder.location.setText(attraction_model.getLocation());
//        holder.year.setText(attraction_model.getYear());
        holder.image.setImageResource(packages_model.getImage());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (modelList.get(position).getImage()){
                    case R.drawable.package_museum:
                        Intent main = new Intent(view.getContext(), Packages_gallery_museum.class);
                        view.getContext().startActivity(main);
                        break;

                    case R.drawable.package_nature_delight:
                        Intent delightIntent = new Intent(view.getContext(), Packages_nature_delight.class);
                        view.getContext().startActivity(delightIntent);
                        break;

                    case R.drawable.package_architecture:
                        Intent architectureIntent = new Intent(view.getContext(), Packages_architecture_heritage.class);
                        view.getContext().startActivity(architectureIntent);
                        break;

                    case R.drawable.package_sunset:
                        Intent sunsetIntent = new Intent(view.getContext(), Packages_sunset.class);
                        view.getContext().startActivity(sunsetIntent);
                        break;

                    case R.drawable.package_education_camp:
                        Intent educationIntent = new Intent(view.getContext(), Packages_education_camp.class);
                        view.getContext().startActivity(educationIntent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
