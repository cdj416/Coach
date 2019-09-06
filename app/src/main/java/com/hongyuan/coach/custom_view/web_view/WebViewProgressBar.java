package com.hongyuan.coach.custom_view.web_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 20170227 on 2018/6/22.
 */

public class WebViewProgressBar extends View {
    private int progress = 1;//进度默认为1
    private final static int HEIGHT = 5;//进度条高度为5
    private Paint paint;//进度条的画笔
    // 渐变颜色数组
    private final static int colors[] = new int[]{0xFFFF9B80, 0xFFEF5B48}; //int类型颜色值格式：0x+透明值+颜色的rgb值

    public WebViewProgressBar(Context context) {
        this(context, null);
    }

    public WebViewProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebViewProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    private void initPaint(Context context) {
        //颜色渐变从colors[0]到colors[1],透明度从0到1
    LinearGradient shader = new LinearGradient(
        0, 0,
        100, HEIGHT,
        colors,
        new float[]{1.0f , 1.0f},
        Shader.TileMode.MIRROR);
        paint = new Paint(Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.STROKE);// 填充方式为描边
        paint.setStrokeWidth(HEIGHT);//设置画笔的宽度
        paint.setAntiAlias(true);// 抗锯齿
        paint.setDither(true);// 使用抖动效果
        //paint.setColor(context.getResources().getColor(R.color.tabIndicatorColor));//画笔设置颜色
        paint.setShader(shader);//画笔设置渐变
    }

    /**
     * 设置进度
     *
     * @param progress 进度值
     */
    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();//刷新画笔
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth() * progress / 100, HEIGHT, paint);//画矩形从（0.0）开始到（progress,height）的区域
    }
}
