package com.example.administrator.bmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/1/4.
 */

public class Money extends BmobObject {
    private String bank;
    private String salary;


    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}