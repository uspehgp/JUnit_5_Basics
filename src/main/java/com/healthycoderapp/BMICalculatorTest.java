package com.healthycoderapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @Test
    void shouldReturnTrueWhenDietRecommend() {

        //given
        double weight = 89.0;
        double height = 1.72;

        //when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);

        //then
        assertTrue(recommended);

    }
    @Test
    void shouldReturnFalseWhenDietNotRecommend() {

        //given
        double weight = 50.0;
        double height = 1.92;

        //when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);

        //then
        assertFalse(recommended);

    }
}
