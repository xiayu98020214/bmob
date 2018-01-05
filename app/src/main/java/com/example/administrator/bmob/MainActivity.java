package com.example.administrator.bmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String mObjectId;
    private BmobUser mCurrentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "49b47a423f8c5b3d88bd34592b27eb3f");


        //注册
/*        BmobUser bu = new BmobUser();
        bu.setUsername("xiayu");
        bu.setPassword("123456");
        bu.setEmail("xiayu@163.com");
        bu.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser s, BmobException e) {
                if(e==null){
                    Log.e(TAG,"注册成功:" +s.toString());
                }else{
                    Log.e(TAG,""+e);
                }
            }
        });*/

        //登录
        BmobUser bu2 = new BmobUser();
        bu2.setUsername("sendi");
        bu2.setPassword("123456");
        bu2.login(new SaveListener<BmobUser>() {

            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if(e==null){
                    Log.e(TAG,"登录成功:");
                    mCurrentUser = BmobUser.getCurrentUser();
                    //manyToMany();
                    initlist();
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                }else{
                    Log.e(TAG,""+e);
                }
            }
        });




      /*  Person p2 = new Person();
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



        Money money = new Money();
        money.setBank("icbc");
        money.setSalary("company");
        money.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Log.e(TAG,"添加数据成功，返回objectId为："+objectId);
                }else{
                    Log.e(TAG,"创建数据失败：" + e.getMessage());
                }
            }
        });
*/



    }

    private Money mMoney;
    private void getMoney(){
        BmobQuery<Money> query = new BmobQuery<Money>();
        query.getObject("ecc3acc82c", new QueryListener<Money>() {

            @Override
            public void done(Money object, BmobException e) {
                if(e==null){
                    //获得playerName的信息
                    mMoney = object;
                    oneToOne2();
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }

        });
    }

    private void oneToOne(){
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
// 创建帖子信息
        Post post = new Post();
        post.setContent("你好");

//添加一对一关联
        post.setAuthor(user);
        post.save(new SaveListener<String>() {

            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Log.i("bmob","保存成功");
                }else{
                    Log.i("bmob","保存失败："+e.getMessage());
                }
            }
        });
    }


    private void oneToOne2(){

        Money money = new Money();
        money.setSalary("1000");
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
// 创建帖子信息
        Post2 post = new Post2();
        post.setMoney(mMoney);
        post.setContent("你好");
//添加一对一关联
        post.setAuthor(user);
        post.save(new SaveListener<String>() {

            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Log.i("bmob","保存成功");
                }else{
                    Log.i("bmob","保存失败："+e.getMessage());
                }
            }
        });
    }

    private void manyToMany(){
        BmobRelation relation = new BmobRelation();
//构造用户B
        MyUser user = new MyUser();
        user.setObjectId("9fbaeb455d");

        Post post = new Post();
        post.setObjectId("739a770c4e");
//将当前用户添加到Post表中的likes字段值中，表明当前用户喜欢该帖子
//将当前用户添加到多对多关联中
        relation.add(user);
//多对多关联指向`post`的`likes`字段
        post.setLikes(relation);
        post.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("bmob","多对多关联添加成功");
                }else{
                    Log.i("bmob","失败："+e.getMessage());
                }
            }

        });
    }

    private void initlist(){
        Person p = new Person();
        p.setObjectId("357c79eb15");
//添加String类型的数组
        p.add("hobbys", "唱歌");                              // 添加单个String
//p.addAll("hobbys", Arrays.asList("游泳", "看书"));    // 添加多个String
//添加Object类型的数组
        p.add("cards",new BankCard("工行卡", "工行卡账号"));
        List<BankCard> cards =new ArrayList<BankCard>();
        for(int i=0;i<2;i++){
            cards.add(new BankCard("建行卡"+i, "建行卡账号"+i));
        }
p.addAll("cards", cards);                         //添加多个Object值
        p.save(new SaveListener<String>() {

            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Log.i("bmob","保存成功");
                }else{
                    Log.i("bmob","保存失败："+e.getMessage());
                }
            }

        });
    }
}
