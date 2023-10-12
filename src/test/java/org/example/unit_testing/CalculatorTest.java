package org.example.unit_testing;

import dev.failsafe.internal.util.Assert;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    void verifySumMethod() {
        int result = calculator.sum(1, 2);
        Assertions.assertEquals(3, result);
    }

    @Test
    void verifyDivideMethod() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculator.divide(3, 0), "Second number can not be 0");
    }

    @ParameterizedTest(name = "test name")
    @ValueSource(ints = {-1, -2, -3})
    void verifySumWithNegativeNumbers(int value) {
        int result = calculator.sum(value, -1);
        Assertions.assertEquals(value-1, result);
    }

    @ParameterizedTest
    @MethodSource("generatePositiveNumbers")
    void verifySumWithPositiveNumbersAndZero(int value) {
        int result = calculator.sum(value, 0);
        Assertions.assertEquals(value, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv")
    void useCSV(String name, String surname, int birthDay) {
        System.out.println("name -> " + name);
        System.out.println("surname -> " + surname);
        System.out.println("birthday -> " + birthDay);
    }


    private static  int[] generatePositiveNumbers(){
        int[] array = {1,2,3};
        return array;
    }

}