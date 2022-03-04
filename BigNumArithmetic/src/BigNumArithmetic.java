import java.util.*;
import java.io.*;

public class BigNumArithmetic {

    public static void main(String[] args) throws IOException {
        File source = new File(args[0]);
        LStack string1 = new LStack();
        ArrayList<String> stringArray = new ArrayList();
        int topLength=0;

        if (source.exists()) {
            //if It exists, opens up a file reader
            FileReader reader = new FileReader(source);
            StringBuilder sb = new StringBuilder();
            char ch;
            while ((ch = (char) reader.read()) >= 0) {
                //intended to catch/handle linebreaks, isn't working properly at the moment been working on this for the past 6 hours
                while (ch != '\n') {
                topLength=0;
                    if (ch == ' ') {
                        //Catches spaces, when it finds a whitespace it "locks in" the previous value, and utilizing a simple counter variable, saves it to value 1 (corresponding to red) 2 (corresponding to green) or 3(corresponding to Blue)
                        String value = sb.toString();
                        sb.setLength(0);
                        stringArray.add(value);
                    }

                }

                    for (int counter = 0; counter < stringArray.size(); counter++) {
                        String value=stringArray.get(counter);
                        if (value.length()>topLength){
                            topLength=value.length();
                        }

                }
            }
        }
    }
    public String addition(String firstVar, String secondVar){
        LStack one= new LStack();
        LStack two= new LStack();
        LStack solved=new LStack();
        String endString="";
        boolean carry=false;
        int topLength=0;
        if(firstVar.length()>=secondVar.length()){
            topLength=firstVar.length();
        }else{
            topLength=secondVar.length();
        }
        String formattedFirst= String.format("%0"+topLength+"d", firstVar);
        String formattedSecond= String.format("%0"+topLength+"d", secondVar);
        for (int i=0; i<firstVar.length(); i++){
            one.push(firstVar.charAt(i));
        }
        for (int i=0; i<secondVar.length(); i++){
            two.push(secondVar.charAt(i));
        }
        for(int i=0; i<topLength; i++){
            int total=0;
            int topOne= (int) one.pop();
            int topTwo= (int) two.pop();
            if(carry) {
                total = +1 + topOne + topTwo;
            }else{
                total=topOne+topTwo;
            }
            if(total>10){
            carry=true;
            total=total-10;
        }else{
                carry=false;
            } solved.push(total);
    }

        while (!solved.isEmpty()){
            char newChar= (char) solved.pop();
            endString=endString+newChar;
        }
        return endString;
        }
}
