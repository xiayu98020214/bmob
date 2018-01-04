package com.example.administrator.bmob;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2018/1/4.
 */

public class MyUser extends BmobUser {

    private Integer age;//为用户表新增一个age字段，注意其必须为`Integer`类型，而不是int

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    //自行实现getter和setter方法
}