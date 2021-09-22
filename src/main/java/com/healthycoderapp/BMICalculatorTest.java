package com.healthycoderapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import java.util.ArrayList;
import java.util.List;

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

    @Test
    void shouldThrowArithmeticExceptionWhenHeightZero() {

        //given
        double weight = 50.0;
        double height = 0.00;

        //when
        Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

        //then
        assertThrows(ArithmeticException.class, executable);

    }

    @Test
    void shouldReturnCoderWithWorstBMI_whenCoderListNotEmpty() {
        //given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));

        //when
        Coder coderWorstBmi = BMICalculator.findCoderWithWorstBMI(coders);

        //then
        assertAll(
                () -> assertEquals(1.82, coderWorstBmi.getHeight()),
                () -> assertEquals(98.0, coderWorstBmi.getWeight())
        );
    }
}
