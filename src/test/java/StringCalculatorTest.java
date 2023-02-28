import org.example.StringCalculator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();
    @Test
    public void givenEmptyStringShouldReturnZero(){
        Assert.assertEquals(0,stringCalculator.add(""));
    }
    @Test
    public void givenSpecificStringNumberShouldReturnSameNumber(){
        Assert.assertEquals(1,stringCalculator.add("1"));
    }
    @Test
    public void givenTwoStringNumberShouldReturnSum(){
        Assert.assertEquals(3,stringCalculator.add("1,2"));
    }
    @Test
    public void givenMoreThanTwoStringNumberShouldReturnSum(){
        Assert.assertEquals(6,stringCalculator.add("1,2,3"));
    }

    @Test
    public void givenNumberStringNumberWithNewLinesShouldReturnSum(){
        Assert.assertEquals(6,stringCalculator.add("1,\n2,3"));
    }
    @Test
    public void givenNumberStringNumberWithDelimitersShouldReturnSum(){
        Assert.assertEquals(3,stringCalculator.add("//;\n1;2"));
    }

    @Test
    public void callingAddWithNegativeNumberShouldThrowException(){
        try{
            stringCalculator.add("1,-2,3,-4");
        }catch (RuntimeException exception){
            Assert.assertNotNull(exception);
            Assert.assertEquals("negatives not allowed [-2,-4]", exception.getMessage());
        }
    }

    @Test
    public void methodThatReturnTheCountOfAddMethodCalls(){
        Assert.assertEquals(0,stringCalculator.getCalledCount());
    }
    @Test
    public void givenANumberGreaterThan1000ShouldBeIgnored(){
        Assert.assertEquals(2,stringCalculator.add("2,1001"));
    }
    @Test
    public void givenAStringNumberHavingDelimiterOfAnyLengthShouldReturnSum(){
        Assert.assertEquals(6,stringCalculator.add("//[***]\n1***2***3"));
    }
    @Test
    public void givenAStringNumberHavingMulitpleDelimiterOfOneLengthShouldReturnSum(){
        Assert.assertEquals(6,stringCalculator.add("//[*][%]\n1*2%3"));
    }
    @Test
    public void givenAStringNumberHavingMulitpleDelimiterOfLengthMoreThanOneShouldReturnSum(){
        Assert.assertEquals(6,stringCalculator.add("//[**][%%]\n1**2%%3"));
    }
}
