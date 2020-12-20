package com.qf.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test1 {
    @Test
    public void test(){
        String str="1";
        Integer i=Integer.parseInt(str);
        Integer integer = Integer.valueOf(str);
        System.out.println(i);
    }
}
