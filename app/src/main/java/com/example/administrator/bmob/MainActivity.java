package com.example.administrator.bmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String mObjectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "49b47a423f8c5b3d88bd34592b27eb3f");
        Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    mObjectId = objectId;
                    Log.e(TAG,"添加数据成功，返回objectId为："+objectId);
                }else{
                    Log.e(TAG,"创建数据失败：" + e.getMessage());
                }
            }
        });


        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("c7383a4bc9", new QueryListener<Person>() {
            @Override
            public void done(Person object,BmobException e) {
                if(e==null){
                    Log.e(TAG,"成功："+object);
                }else{
                }
            }
        });
    }
}
