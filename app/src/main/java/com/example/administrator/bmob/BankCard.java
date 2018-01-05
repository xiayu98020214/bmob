package com.example.administrator.bmob;

/**
 * Created by Administrator on 2018/1/5.
 */
public class BankCard{
    private String cardNumber;
    private String bankName;
    public BankCard(String bankName, String cardNumber){
        this.bankName = bankName;
        this.cardNumber = cardNumber;
    }
}