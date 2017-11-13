package es.vass.testing.testing;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by German on 13/11/17.
 */

class StringCalculator{
    private final String SPLITTER_COMMA_AND_RETURN = "[,\\n]";

    public int sum(String param) throws NegativeNumbersException{

        if (param != null) {

            String[] possibleNumbers = param.split(SPLITTER_COMMA_AND_RETURN);
            int sum = 0;

            ArrayList<String> negativeNumbersArray = new ArrayList<>();

            for (int i = 0; possibleNumbers != null && i < possibleNumbers.length; ++i) {
                try {
                    int newNumber = Integer.parseInt(possibleNumbers[i]);
                    sum += newNumber;

                    if (newNumber < 0){
                        negativeNumbersArray.add(possibleNumbers[i]);
                    }
                } catch (NumberFormatException ignore) {
                }

            }

            if (negativeNumbersArray.size() > 0){
                NegativeNumbersException negativeNumbersException = new NegativeNumbersException();
                negativeNumbersException.setNegativeNumbersFound(negativeNumbersArray.toArray(new String[negativeNumbersArray.size()]));
                throw negativeNumbersException;
            }
            return sum;
        }
        else {
            return 0;
        }

        //Así estaba antes
        /*try {
            return Integer.parseInt(param);
        }catch (Exception e){}

        return 0;*/
    }
}

class NegativeNumbersException extends Exception{
    public String[] getNegativeNumbersFound() {
        return negativeNumbersFound;
    }

    public void setNegativeNumbersFound(String[] negativeNumbersFound) {
        this.negativeNumbersFound = negativeNumbersFound;
    }

    private String[] negativeNumbersFound;
}

public class StringCalculatorTest {
    private StringCalculator stringCalculator = new StringCalculator();
    @Test
    public void test_empty_string_return_0(){
        int sum = 0;
        try {
            sum = stringCalculator.sum("");
        } catch (NegativeNumbersException ignore) {

        }

        assertEquals(0, sum);
    }

    @Test
    public void testReturnNumberPassedAsParam(){
        int sum = 0;
        try {
            sum = stringCalculator.sum("1");
        } catch (NegativeNumbersException ignore) {

        }

        assertEquals(1, sum);
    }

    @Test
    public void testReturnSumOfTwoNumbersSeparatedWithComma(){
        int sum = 0;
        try {
            sum = stringCalculator.sum("1,2");
        } catch (NegativeNumbersException ignore) {

        }

        assertEquals(3, sum);
    }

    @Test
    public void testReturnSumOfAnyNumbersSeparatedWithComma(){
        int sum = 0;
        try {
            sum = stringCalculator.sum("1,2,2,5");
        } catch (NegativeNumbersException ignore) {

        }

        assertEquals(10, sum);
    }

    @Test
    public void testReturnSumOfTwoNumbersSeparatedWithReturn(){
        int sum = 0;
        try {
            sum = stringCalculator.sum("1,2\n3");
        } catch (NegativeNumbersException ignore) {

        }

        assertEquals(6, sum);
    }

    @Test
    public void testReturn0onNullParam(){
        int sum = 0;
        try {
            sum = stringCalculator.sum(null);
        } catch (NegativeNumbersException ignore) {

        }

        assertEquals(0, sum);
    }

    @Test
    public void testNegativeNumberSumShouldReturnExceptionWithAllNegativeNumbersFound(){
        String[] negativeNumbersExpected = {"-1","-3"};

        try {
            int sum = stringCalculator.sum("-1,2,-3,4");
        }catch (NegativeNumbersException negativeNumbersException){

            assertArrayEquals(negativeNumbersExpected, negativeNumbersException.getNegativeNumbersFound());
        }
    }
}
