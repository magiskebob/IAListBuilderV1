package com.example.kongsgaard.ialistbuilderv1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.padding;
import static android.R.id.list;

/**
 * Created by Kongsgaard on 16-03-2017.
 */

public class ImageAdapter extends PagerAdapter {
    Context context;
    private List<Integer> GallImages = new ArrayList<>();
    private int[] GalImages;

    ImageAdapter(Context context, List<Integer> intlist){
        this.context=context;
        GallImages = intlist;
        GalImages = listToArray(GallImages);
    }
    public int[] listToArray(List<Integer> intlist){
        int[] ret = new int[GallImages.size()];
        int i = 0;
        for (Integer e : GallImages)
            ret[i++] = e.intValue();
        return ret;
    }

    @Override
    public int getCount() {
        return GallImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
       // int padding = context.getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
       // imageView.setPadding(padding, padding, padding, padding);
       // imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(GalImages[position]);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
