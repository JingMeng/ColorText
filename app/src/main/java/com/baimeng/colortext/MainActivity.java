package com.baimeng.colortext;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baimeng.colortext.indicator.ColorTrackTextContainer;
import com.baimeng.colortext.indicator.IndicatorAdapter;
import com.baimeng.colortext.view.ColorTrackTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String [] items = {"直播","推荐","视频"
            ,"段友秀","图片","段子"
            , "同城","精华","游戏"
    };
    private ColorTrackTextView colorText;
    private ColorTrackTextContainer mIndicatorContainer;
    private ViewPager mViewPager;
    private List<ColorTrackTextView> mIndicators;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIndicators = new ArrayList<>() ;
        mIndicatorContainer = (ColorTrackTextContainer) findViewById(R.id.indiicator_view);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        initIndicator();
        initViewPager();
        //colorText = (ColorTrackTextView) findViewById(R.id.color_text);
    }

    private void initViewPager() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return ItemFragment.newInstance(items[position]);
            }

            @Override
            public int getCount() {
                return items.length;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //在滚动的过程中会不断的回调
                ColorTrackTextView left = mIndicators.get(position);
                left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                left.setCurrentProgress(1-positionOffset);
                try {
                    ColorTrackTextView right = mIndicators.get(position + 1);
                    right.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                    right.setCurrentProgress(positionOffset);
                }catch (Exception e){
                    e.printStackTrace();
                }



            }

            @Override
            public void onPageSelected(int position) {
                //选中回调
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //状态改变回调
            }
        });
    }

    private void initIndicator() {
        mIndicatorContainer.setAdapter(new IndicatorAdapter() {
            @Override
            public int getCount() {
                return items.length;
            }

            @Override
            public View getView(int position, ViewGroup parent) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                params.topMargin = 15 ;
//                params.bottomMargin = 15 ;
                ColorTrackTextView colorTrackTextView = new ColorTrackTextView(MainActivity.this);
                colorTrackTextView.setTextSize(14);
                colorTrackTextView.setGravity(Gravity.CENTER);
               // colorTrackTextView.setBackgroundColor(Color.parseColor("#ff5566"));
                colorTrackTextView.setText(items[position]);
                colorTrackTextView.setChangeColor(Color.RED);
                colorTrackTextView.setLayoutParams(params);
                mIndicators.add(colorTrackTextView);
                colorTrackTextView.setTextColor(Color.BLACK);
                return colorTrackTextView;
            }

            @Override
            public void highLightIndicator(View view) {
                TextView textView = (TextView) view;
                textView.setTextColor(Color.RED);
            }

            @Override
            public void restoreIndicator(View view) {
                TextView textView = (TextView) view;
                textView.setTextColor(Color.BLACK);
            }

            @Override
            public View getBottomTrackView() {
                View view = new View(MainActivity.this);
                view.setBackgroundColor(Color.RED);
                view.setLayoutParams(new ViewGroup.MarginLayoutParams(88,8));
                return view;
            }
        },mViewPager);
//        for (int i = 0 ; i < items.length ; i++){
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            params.weight = 1 ;
//            ColorTrackTextView colorTrackTextView = new ColorTrackTextView(this);
//            colorTrackTextView.setTextSize(20);
//            colorTrackTextView.setText(items[i]);
//            colorTrackTextView.setChangeColor(Color.RED);
//            colorTrackTextView.setLayoutParams(params);
//            mIndicatorContainer.addView(colorTrackTextView);
//            mIndicators.add(colorTrackTextView);
//        }
    }

//    public void leftToRight(View view){
//        colorText.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
//        ValueAnimator animator = ObjectAnimator.ofFloat(0, 1f).setDuration(2000);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
//
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float currentProgress = (float)animation.getAnimatedValue();
//                colorText.setCurrentProgress(currentProgress);
//            }
//        });
//        animator.start();
//    }
//
//    public void rightToLeft(View view){
//        colorText.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
//        ValueAnimator animator = ObjectAnimator.ofFloat(0f, 1f).setDuration(2000);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
//
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float currentProgress = (float)animation.getAnimatedValue();
//                colorText.setCurrentProgress(currentProgress);
//            }
//        });
//        animator.start();
//    }
}
