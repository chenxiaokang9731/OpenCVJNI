package com.narkang.opencvjni;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GrayActivity extends BaseActivity {

    static {
        System.loadLibrary("native-lib");
    }

    private ImageView show_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gray);
        setTitle("JNI灰度化");
        show_image = (ImageView) findViewById(R.id.show_image);
        show_image.setImageResource(R.mipmap.ba);
        findViewById(R.id.pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //恢复
                backPic();
            }
        });
        findViewById(R.id.gray_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //变灰
                grayPic();
            }
        });
    }

    private void backPic(){
        show_image.setImageResource(R.mipmap.ba);
    }

    private void grayPic(){
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.mipmap.ba);
        int w = bmp.getWidth();
        int h = bmp.getHeight();
        int[] pixels = new int[w*h];
        bmp.getPixels(pixels, 0, w, 0, 0, w, h);
        //recall JNI
        int[] resultInt = getGrayImage(pixels, w, h);
        Bitmap resultImg = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        resultImg.setPixels(resultInt, 0, w, 0, 0, w, h);
        show_image.setImageBitmap(resultImg);
    }

    //图像处理
    public native int[] getGrayImage(int[] pixels, int w, int h);

}
