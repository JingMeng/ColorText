package com.baimeng.colortext;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.baimeng.colortext.view.ColorTrackTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String [] items = {"直播","推荐","视频","图片","段子","精华"};
    private ColorTrackTextView colorText;
    private LinearLayout mIndicatorContainer;
    private ViewPager mViewPager;
    private List<ColorTrackTextView> mIndicators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIndicators = new ArrayList<>() ;
        mIndicatorContainer = (LinearLayout) findViewById(R.id.indiicator_view);
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
        for (int i = 0 ; i < items.length ; i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1 ;
            ColorTrackTextView colorTrackTextView = new ColorTrackTextView(this);
            colorTrackTextView.setTextSize(20);
            colorTrackTextView.setText(items[i]);
            colorTrackTextView.setChangeColor(Color.RED);
            colorTrackTextView.setLayoutParams(params);
            mIndicatorContainer.addView(colorTrackTextView);
            mIndicators.add(colorTrackTextView);
        }
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
