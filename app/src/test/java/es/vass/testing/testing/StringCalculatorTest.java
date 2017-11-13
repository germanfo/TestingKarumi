package es.vass.testing.testing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by German on 13/11/17.
 */

class StringCalculator{
    private final String SPLITTER_COMMA_AND_RETURN = "[,\\n]";

    public int sum(String param){


        String[] possibleNumbers = param.split(SPLITTER_COMMA_AND_RETURN);
        int sum = 0;

        for (int i = 0; i < possibleNumbers.length; ++i){
            try {
                sum += Integer.parseInt(possibleNumbers[i]);
            }catch (NumberFormatException ignore){}

        }

        return sum;

        //Así estaba antes
        /*try {
            return Integer.parseInt(param);
        }catch (Exception e){}

        return 0;*/
    }
}

public class StringCalculatorTest {
    private StringCalculator stringCalculator = new StringCalculator();
    @Test
    public void test_empty_string_return_0(){
        int sum = stringCalculator.sum("");

        assertEquals(0, sum);
    }

    @Test
    public void testReturnNumberPassedAsParam(){
        int sum = stringCalculator.sum("1");

        assertEquals(1, sum);
    }

    @Test
    public void testReturnSumOfTwoNumbersSeparatedWithComma(){
        int sum = stringCalculator.sum("1,2");

        assertEquals(3, sum);
    }

    @Test
    public void testReturnSumOfAnyNumbersSeparatedWithComma(){
        int sum = stringCalculator.sum("1,2,2,5");

        assertEquals(10, sum);
    }

    @Test
    public void testReturnSumOfTwoNumbersSeparatedWithReturn(){
        int sum = stringCalculator.sum("1,2\n3");

        assertEquals(6, sum);
    }
}
