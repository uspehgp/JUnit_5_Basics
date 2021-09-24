package com.healthycoderapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all unit tests");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all unit tests");
    }

    @ParameterizedTest(name = "weight{0}, height{1}")
    @CsvFileSource(resources = "014 diet-recommended-input-data.csv", numLinesToSkip = 1)
    void shouldReturnTrueWhenDietRecommend(Double coderWeight, Double coderHeight) {

        //given
        double weight = coderWeight;
        double height = coderHeight;

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

    @Test
    void shouldReturnCoderWithWorstBMI_whenCoderListEmpty() {
        //given
        List<Coder> coders = new ArrayList<>();

        //when
        Coder coderWorstBmi = BMICalculator.findCoderWithWorstBMI(coders);

        //then
        assertNull(coderWorstBmi);
    }

    @Test
    void should_ReturnBMIScoresArray_whenCoderListNotEmpty() {
        //given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        double expected[] = {18.52, 29.59, 19.53};

        //when
        double[] bmiScores = BMICalculator.getBMIScores(coders);

        //then
        assertArrayEquals(expected, bmiScores);
    }

    @Test
    void shouldReturnCoderWithWorstBMIIn1Ms_whenCoderHas10000Elements() {

        //given
        List<Coder> coders = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            coders.add(new Coder(1.0 + i, 10.0 + i));
        }

        //when
        Executable executable = ()-> BMICalculator.findCoderWithWorstBMI(coders);

        //then
        assertTimeout(Duration.ofMillis(100), executable);
    }
}
