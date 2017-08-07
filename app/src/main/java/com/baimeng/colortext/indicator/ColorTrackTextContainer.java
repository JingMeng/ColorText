package com.baimeng.colortext.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.baimeng.colortext.R;

/**
 * Created by BaiMeng on 2017/8/7.
 */
public class ColorTrackTextContainer extends HorizontalScrollView {

    private LinearLayout mInnerContainer ;
    private int mTabVisibleNums = 0 ;
    private IndicatorAdapter mAdapter ;

    public ColorTrackTextContainer(Context context) {
        this(context,null);
    }

    public ColorTrackTextContainer(Context context, AttributeSet attrs) {
        this(context, attrs ,0);
        initAttrs(context,attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextContainer);
        mTabVisibleNums = array.getInt(R.styleable.ColorTrackTextContainer_tabVisibleNums, mTabVisibleNums);
        array.recycle();
    }

    public ColorTrackTextContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInnerContainer = new LinearLayout(getContext()) ;
        addView(mInnerContainer);
    }

    public void setAdapter(IndicatorAdapter adapter){
        if(adapter == null){
            throw new NullPointerException("adapter is Null") ;
        }
        mAdapter = adapter ;
        int itemCount = adapter.getCount();
        for(int i = 0 ; i < itemCount ; i++ ){
            View view = adapter.getView(i, mInnerContainer);
            mInnerContainer.addView(view);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            int itemWidth = getItemWidth();
            int itemCount = mAdapter.getCount() ;
            for (int i = 0 ; i < itemCount ; i++){
               mInnerContainer.getChildAt(i).getLayoutParams().width = itemWidth ;
            }
        }
    }

    private int getItemWidth() {
        int itemWidth = 0 ;
        int width = getWidth() ;
        int allWidth = 0 ;
        //如果设置了一屏显示几个
        if(mTabVisibleNums != 0){
            itemWidth = width / mTabVisibleNums ;
            return itemWidth ;
        }
        //如果没有设置一屏显示几个，我们要自己计算
        //获取到所有控件中的最宽的那个
        for (int i = 0 ; i < mAdapter.getCount() ; i++){
            if(itemWidth < mInnerContainer.getChildAt(i).getWidth()){
                itemWidth = mInnerContainer.getChildAt(i).getWidth() ;
            }
        }
        if(itemWidth * mAdapter.getCount() < width ){
            itemWidth = width / mAdapter.getCount() ;
            return itemWidth ;
        }
        for (int i = 0 ; i< mAdapter.getCount() ; i++){
            allWidth += itemWidth ;
            if(allWidth > width){
                itemWidth = width / i ;
                return itemWidth ;
            }
        }
        return itemWidth;
    }

}
