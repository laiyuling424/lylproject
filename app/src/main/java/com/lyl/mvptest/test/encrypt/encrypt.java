package com.lyl.mvptest.test.encrypt;

public class encrypt {
    public static void main(String[] args) {
        int a=3;
        System.out.println("加密前数字:"+a);
        int b= a^2;
        System.out.println("加密后数字:"+b);
        int c=b^2;
        System.out.println("解密后数字:"+c);
        String s1="{\"1\":1}";
        System.out.println("加密前字符:"+s1);
        byte[] by=s1.getBytes();
        byte[] by2=new byte[by.length];
        for(int i=0;i<by.length;i++){
            by2[i]= (byte) (by[i]^2);
        }
        String s2=new String(by2);
        System.out.println("加密后字符:"+s2);
        byte[] by4=s2.getBytes();
        byte[] by3=new byte[by4.length];
        for(int i=0;i<by4.length;i++){
            by3[i]= (byte) (by4[i]^2);
        }
        String s3=new String(by3);
        System.out.println("解密后字符:"+s3);
    }
}
