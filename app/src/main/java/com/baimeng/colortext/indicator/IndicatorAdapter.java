package com.baimeng.colortext.indicator;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by BaiMeng on 2017/8/7.
 */
public abstract class IndicatorAdapter {
    //获取子控件数量
    public abstract int getCount();
    //获取子控件的view
    public abstract View getView(int position ,ViewGroup parent);
    //高亮当前位置
    public void highLightIndicator(View view){

    }
    //重置当前位置
    public void restoreIndicator(View view){

    }

    //添加底部跟踪的指示器
    public View getBottomTrackView(){
        return null ;
    }
}
