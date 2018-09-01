package com.ankit626.startup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private ViewPager viewPager;
    private Intromanager intromanager;
    private int layouts[];
    private TextView[] dots;
    private LinearLayout dotslayout;
    Button next,skip;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        intromanager=new Intromanager(this);
        if(!intromanager.check()){
            intromanager.setfirst(false);
            Intent i=new Intent(Main2Activity.this,Main3Activity.class);
            startActivity(i);
            finish();
        }
        layouts =new int[]{R.layout.activity_guide,R.layout.activity_pay,R.layout.activity_support,R.layout.activity_vehicle};
        skip=(Button)findViewById(R.id.btn_skip);
        next=(Button)findViewById(R.id.btn_next);
        dotslayout=(LinearLayout)findViewById(R.id.layoutDots);
        addBottomDots(0);
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        viewPagerAdapter=new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(a);
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current=getitem(+1);
                if(current<layouts.length){
                    viewPager.setCurrentItem(current);
                }
                else{
                    Intent a=new Intent(Main2Activity.this,Main3Activity.class);
                    startActivity(a);
                    finish();
                }
            }
        });
    }
      private void addBottomDots(int position){

        dots=new TextView[layouts.length];
        int[] colorActive=getResources().getIntArray(R.array.dot_active);
        int[] colorInActive=getResources().getIntArray(R.array.dot_inactive);
        dotslayout.removeAllViews();
        for(int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&/8226"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(colorInActive[position]);
            dotslayout.addView(dots[i]);
        }
        if(dots.length>0){
            dots[position].setTextColor(colorInActive[position]);
        }
      }

      public int getitem(int i){
         return viewPager.getCurrentItem()+i;
      }
    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
          addBottomDots(position);
          if (position==layouts.length-1){
              next.setText("PROCEED");
              skip.setVisibility(View.GONE);
          }
          else{
              next.setText("NEXT");
              skip.setVisibility(View.VISIBLE);
          }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    public class ViewPagerAdapter extends PagerAdapter {
           private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v=layoutInflater.inflate(layouts[position],container,false);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v=(View)object;
            container.removeView(v);
        }
    }
}
