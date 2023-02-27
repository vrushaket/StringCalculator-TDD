package org.example;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class StringCalculator1 {

    private int calledCount;
    private ArrayList<Integer> NegativeElements = new ArrayList<>();

    public int getCalledCount()
    {
        return calledCount;
    }

    public int add(String number) throws Exception {
        if (number.isEmpty()) {
            calledCount++;
            return 0;
        } else if (number.length() == 1){
            calledCount++;
            if(Integer.parseInt(number)<0) {
                throw new Exception("negatives not allowed " + number);
            }
            return Integer.parseInt(number);
        }
//        else if(number.startsWith("-"))
//        {
//            for (String num : number.split(",")) {
//                Integer.parseInt(num);
//            }
//            throw new Exception("negatives not allowed "+ number);
//        }
        else {

            int sum = 0;

            if (number.startsWith("//")) {
                String delimiter=null;
                int SeperatorIndex=0;
                if(number.startsWith("[",2))
                {
                    String[] tokens = number.split("\n");
                    delimiter = ",";

                    if (tokens[0].startsWith("//[")) {
                        String[] delimiterList = tokens[0].substring(3, tokens[0].length() - 1).split("\\]\\[");
                        delimiter = "";
                        for (String delim : delimiterList) {
                            delimiter += Pattern.quote(delim) + "|";
                        }
                        delimiter = delimiter.substring(0, delimiter.length() - 1);
                    }

                    String numbersString = tokens[tokens.length - 1];
                    String[] numbers = numbersString.split(delimiter);

                    for (String num : numbers) {
                        int a = Integer.parseInt(num.trim());
                        if(a<0)
                        {
                            NegativeElements.add(a);
                        }
                        else {
                            sum += Integer.parseInt(num.trim());
                        }
                    }
                }
                else {
                    SeperatorIndex = number.indexOf("\n");
                    delimiter = number.substring(SeperatorIndex - 1, SeperatorIndex);
                    String numberSubstring = number.substring(SeperatorIndex + 1, number.length());
                    for (String iterator : numberSubstring.split(delimiter)) {
                        if(Integer.parseInt(iterator)<0)
                        {
                            NegativeElements.add(Integer.parseInt(iterator));
                        }
                        else {
                            sum += Integer.parseInt(iterator);
                        }
                    }
                }
            } else {
                for (String num : number.replaceAll("\n",",").split(",")) {
                    sum += Integer.parseInt(num);
                }
            }
            if(!NegativeElements.isEmpty()){
                throw new RuntimeException("negatives not allowed " + NegativeElements.toString().replaceAll(" ",""));
            }
            calledCount++;
            return sum;
        }
    }
}