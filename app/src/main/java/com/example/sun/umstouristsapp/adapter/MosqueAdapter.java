package com.example.sun.umstouristsapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sun.umstouristsapp.R;

/**
 * Created by sun on 3/1/2017.
 */

public class MosqueAdapter extends PagerAdapter {

    private int[] imgs = {R.drawable.mosque_1,R.drawable.mosque_2,R.drawable.mosque_3,R.drawable.mosque_4};
    private LayoutInflater inflater;
    private Context ctx;

    public MosqueAdapter(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view ==(LinearLayout)object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater =(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.attraction_swipe,container,false);
        ImageView img = (ImageView)v.findViewById(R.id.imageView);
//        TextView tv = (TextView)v.findViewById(R.id.textView);
        img.setImageResource(imgs[position]);
//        tv.setText("Image :"+position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}
