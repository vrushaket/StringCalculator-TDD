package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private int addMethodCallCount;
    public int add(String numbers){
        addMethodCallCount++;
        ArrayList<Integer> negativeNumbers = new ArrayList<>();
        if(numbers.isEmpty()){
            return 0;
        }
        if(numbers.length() == 1){
            return Integer.parseInt(numbers);
        }
        else{
            String delimiter = ",";
            String escapedDelimiter = null;
            int sum = 0;
            ArrayList<String> numberArray = new ArrayList<>();
            if(numbers.startsWith("//")){
                if(numbers.startsWith("[",2)){
                    String delimiters = numbers.substring(numbers.indexOf("["),numbers.indexOf("]\n")+1);
                    String actualStartofNumbers = numbers.substring(numbers.indexOf("]\n")+1).replace("\n","");
                    for (String delimit: delimiters.replace("[","").split("]")) {
                        actualStartofNumbers = actualStartofNumbers.replaceAll(Pattern.quote(delimit),",");
                    }
                    numberArray.addAll(Arrays.asList(actualStartofNumbers.split(",")));
                }
                else{
                    int lastDelimiterCloseIndex = numbers.indexOf("\n");
                    delimiter = numbers.substring(2,lastDelimiterCloseIndex);
                    escapedDelimiter = Pattern.quote(delimiter);
                    numberArray.addAll(Arrays.asList(numbers.substring(3).replaceAll("\n","").split(escapedDelimiter)));
                }
            }
            else{
                numberArray.addAll(Arrays.asList(numbers.replaceAll("\n","").split(delimiter)));
            }

            for (String number: numberArray) {
                if(number.length()>0){
                    int num = Integer.parseInt(number);
                    if(num<0 ){
                        negativeNumbers.add(num);
                    }
                    else if (num<=1000) {
                        sum+=num;
                    }
                }
            }
            if(!negativeNumbers.isEmpty()){
                throw new RuntimeException("negatives not allowed " + negativeNumbers.toString().replaceAll(" ",""));
            }
            return sum;
        }
    }

    public int getCalledCount(){
        return addMethodCallCount;
    }
}
