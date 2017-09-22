package com.narkang.opencvjni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.opencv.android.OpenCVLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  参考文档： http://blog.csdn.net/ddjjll8877/article/details/52670097
 *            http://blog.csdn.net/u011630458/article/details/43112781
 */
public class ListActivity extends BaseActivity {

    private ListView lv_opencv;

    private HashMap<String, Class> dates = new HashMap<String, Class>(){
        {
            put("灰化/感应触屏/边缘检测", CameraActivity.class);
            put("灰度化", GrayActivity.class);
        }
    };

    static {
        OpenCVLoader.initDebug();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("功能列表");
        lv_opencv = (ListView) findViewById(R.id.lv_opencv);
        List<String> titles = new ArrayList<>();
        final List<Class> classes = new ArrayList<>();
        Iterator<Map.Entry<String, Class>> iterator = dates.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Class> next = iterator.next();
            String key = next.getKey();
            Class clazz = next.getValue();
            titles.add(key);
            classes.add(clazz);
        }
        lv_opencv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles));
        lv_opencv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(ListActivity.this, classes.get(i)));
            }
        });
    }

}
