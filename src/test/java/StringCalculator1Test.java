
import org.example.StringCalculator1;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class StringCalculator1Test {
    StringCalculator1 stringCalculator = new StringCalculator1();
    @Test
    public void givenEmptyStringShouldReturnZero() throws Exception {
        Assert.assertEquals(0,stringCalculator.add(""));
    }
    @Test
    public void givenSpecificStringNumberShouldReturnSameNumber()throws Exception{
        Assert.assertEquals(1,stringCalculator.add("1"));
    }
    @Test
    public void givenTwoStringNumberShouldReturnSum() throws Exception {
        Assert.assertEquals(3,stringCalculator.add("1,2"));
    }
    @Test
    public void givenMoreThanTwoStringNumberShouldReturnSum() throws Exception {
        Assert.assertEquals(6,stringCalculator.add("1,2,3"));
    }

    @Test
    public void givenNumberStringNumberWithNewLinesShouldReturnSum() throws Exception {
        Assert.assertEquals(6,stringCalculator.add("1,\n2,3"));
    }
    @Test
    public void givenNumberStringNumberWithDelimitersShouldReturnSum() throws Exception {
        Assert.assertEquals(3,stringCalculator.add("//;\n1;2"));
    }

    @Test
    public void callingAddWithNegativeNumberShouldThrowException(){
        try{
            stringCalculator.add("1,-2,3,-4");
        }catch (RuntimeException exception){
            Assert.assertNotNull(exception);
            Assert.assertEquals("negatives not allowed [-2,-4]", exception.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void methodThatReturnTheCountOfAddMethodCalls(){
        Assert.assertEquals(0,stringCalculator.getCalledCount());
    }
    @Test
    public void givenANumberGreaterThan1000ShouldBeIgnored() throws Exception {
        Assert.assertEquals(2,stringCalculator.add("2,1001"));
    }
    @Test
    public void givenAStringNumberHavingDelimiterOfAnyLengthShouldReturnSum() throws Exception {
        Assert.assertEquals(6,stringCalculator.add("//[***]\n1***2***3"));
    }
    @Test
    public void givenAStringNumberHavingMulitpleDelimiterOfOneLengthShouldReturnSum() throws Exception {
        Assert.assertEquals(6,stringCalculator.add("//[*][%]\n1*2%3"));
    }
    @Test
    public void givenAStringNumberHavingMulitpleDelimiterOfLengthMoreThanOneShouldReturnSum() throws Exception {
        Assert.assertEquals(6,stringCalculator.add("//[**][%%]\n1**2%%3"));
    }

}
