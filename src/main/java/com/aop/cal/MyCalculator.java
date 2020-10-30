package com.aop.cal;

import org.springframework.stereotype.Service;

@Service
public class MyCalculator implements Calculator {
    public int add(int i, int j) {
        return i+j;
    }


    public int sub(int i, int j) {
        return i-j;
    }

    public int div(int i, int j) {
        return i/j;
    }
}
