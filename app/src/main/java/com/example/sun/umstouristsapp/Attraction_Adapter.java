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

public class Attraction_Adapter extends RecyclerView.Adapter<Attraction_Adapter.MyViewHolder> {

    private List<Attraction_Model> dataModelList;

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

    public Attraction_Adapter(){}

    public Attraction_Adapter( List<Attraction_Model> dataModelList) {
        this.dataModelList = dataModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_customview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Attraction_Model attraction_model = dataModelList.get(position);
        holder.title.setText(attraction_model.getTitle());
//        holder.location.setText(attraction_model.getLocation());
//        holder.year.setText(attraction_model.getYear());
        holder.image.setImageResource(attraction_model.getImage());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (dataModelList.get(position).getImage()){
                    case R.drawable.ums_dewan_canselor_resize:
                        Intent main = new Intent(view.getContext(), Attraction_chancellor_hall.class);
                        view.getContext().startActivity(main);
                        break;

                    case R.drawable.ums_masjid_resize:
                        Intent mosqueIntent = new Intent(view.getContext(), Attraction_mosque.class);
                        view.getContext().startActivity(mosqueIntent);
                        break;

                    case R.drawable.ums_clock_menarajam_resize:
                        Intent clockIntent = new Intent(view.getContext(), Attraction_clock_tower.class);
                        view.getContext().startActivity(clockIntent);
                        break;

                    case R.drawable.ums_chancellery:
                        Intent chancelleryIntent = new Intent(view.getContext(), Attraction_chancellery.class);
                        view.getContext().startActivity(chancelleryIntent);
                        break;

                    case R.drawable.ums_aquarium_and_museum:
                        Intent aquariumIntent = new Intent(view.getContext(), Attraction_aquarium_and_museum.class);
                        view.getContext().startActivity(aquariumIntent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

}
