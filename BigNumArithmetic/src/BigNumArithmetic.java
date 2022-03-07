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
        //three stacks, two for containing the character of each source numeric string, one for the solution
        LStack one= new LStack();
        LStack two= new LStack();
        LStack solved=new LStack();
        //Placeholder declaration for the returned numerical string
        String endString="";
        //boolean for carrying a 1
        boolean carry=false;
        //top length integer for sizing purposes
        int topLength=0;
        //check if firstVar or secondVar are longer, then set the longest length to top leength
        if(firstVar.length()>=secondVar.length()){
            topLength=firstVar.length();
        }else{
            topLength=secondVar.length();
        }
        //formats two new strings to the top length, formatting the smaller one with leading 0s (technically reformats both but the larger doesn't undergo any changes)
        String formattedFirst= String.format("%0"+topLength+"d", firstVar);
        String formattedSecond= String.format("%0"+topLength+"d", secondVar);

        //Goes through the formatted string pushing each character individually to the first stack.
        for (int i=0; i<formattedFirst.length(); i++){
            one.push(formattedFirst.charAt(i));
        }
        //Does the above to a second stack
        for (int i=0; i<formattedSecond.length(); i++){
            two.push(formattedSecond.charAt(i));
        }
        //for the total length (how many digits) it goes through, pops the top off of each stack (which would be the ones place)
        for(int i=0; i<topLength; i++){
            int total=0;
            int topOne= (int) one.pop();
            int topTwo= (int) two.pop();
            //checks if the previous calculation had a carried one.
            if(carry) {
                //adds the carry value and the two popped values.
                total = +1 + topOne + topTwo;
            }else{
                //no carry value just adds two values.
                total=topOne+topTwo;
            }
            //checks if the total is greater than 10
            if(total>10){
                //sets carry value to true and subtracts 10 from the total value
            carry=true;
            total=total-10;
        }else{
                //sets carry to false
                carry=false;
                //adds the values to a "Solved" stack in reverse order.
            } solved.push(total);
    }
        //while solved is not empty it goes through popping each numeric char and then adds them to end string.
        while (!solved.isEmpty()){
            char newChar= (char) solved.pop();
            endString=endString+newChar;
        }
        //returns endstring.
        return endString;
        }

 public String subtract(String firstVar, String secondVar){ //should be finished
        //three stacks, two for containing the character of each source numeric string, one for the solution
        LStack one= new LStack();
        LStack two= new LStack();
        LStack solved=new LStack();
        String endString="";
        boolean prevCarry=false;
        boolean carry=false;
        int topLength=0;
        
	if(firstVar.length()>=secondVar.length()){
              topLength=firstVar.length();
          }else{
              topLength=secondVar.length();
	      endString="-";
          }
        String formattedFirst= String.format("%0"+topLength+"d", firstVar);
        String formattedSecond= String.format("%0"+topLength+"d", secondVar);

        //Goes through the formatted string pushing each character individually to the first stack.
        for (int i=0; i<formattedFirst.length(); i++){
            one.push(formattedFirst.charAt(i));
        }
        //Does the above to a second stack
        for (int i=0; i<formattedSecond.length(); i++){
            two.push(formattedSecond.charAt(i));
        }
        //for the total length (how many digits) it goes through, pops the top off of each stack (which would be the ones place)
        for(int i=0; i<topLength; i++){
            int total=0;
            int topOne= (int) one.pop();
            int topTwo= (int) two.pop();
            if (prevCarry){
                 carry=true;
                 prevCarry=false;
            }
            if(carry&& topOne-1<topTwo){
                  prevCarry=true;
            }
            else if (topOne<topTwo){
                  prevCarry=true;
            }
            if(prevCarry){
                topOne+=10;
            }
            if(carry) {
                //adds the carry value and the two popped values.
                total = (topOne-1) - topTwo;
                carry=false;
            }else{
                //no carry value just adds two values.
                total=topOne-topTwo;
            }


            solved.push(total);

            }
    
        //while solved is not empty it goes through popping each numeric char and then adds them to end string.
        while (!solved.isEmpty()){
            char newChar= (char) solved.pop();
            endString=endString+newChar;
	 }
        //returns endstring.
        return endString;
        }
    public String multiply(String firstVar, String secondVar){
        //three stacks, two for containing the character of each source numeric string, one for the solution
        String total="000000000000000000";

        LStack one= new LStack();
        LStack two= new LStack();
        LStack temp=new LStack();
        LStack solved=new LStack();
        //Placeholder declaration for the returned numerical string
        String endString="";
        //boolean for carrying a 1
        boolean carry=false;
        //top length integer for sizing purposes
        int topLength=0;
        //check if firstVar or secondVar are longer, then set the longest length to top leength
        if(firstVar.length()>=secondVar.length()){
            topLength=firstVar.length();
        }else{
            topLength=secondVar.length();
        }
        //formats two new strings to the top length, formatting the smaller one with leading 0s (technically reformats both but the larger doesn't undergo any changes)
        String formattedFirst= String.format("%0"+topLength+"d", firstVar);
        String formattedSecond= String.format("%0"+topLength+"d", secondVar);

        //Goes through the formatted string pushing each character individually to the first stack.
        for (int i=0; i<formattedFirst.length(); i++){
            one.push(formattedFirst.charAt(i));
        }
        //Does the above to a second stack
        for (int i=0; i<formattedSecond.length(); i++){
            two.push(formattedSecond.charAt(i));
        }
        //for the total length (how many digits) it goes through, pops the top off of each stack (which would be the ones place)
        for(int i=0; i<topLength; i++){
            int topOne= (int) one.pop();

            if(two.isEmpty()) {
                for (int c = 0; c < topLength; c++) {

                    int value = (int) temp.pop();
                    int tempTotalInt = value * topOne;
                    String tempTotal = Integer.toString(tempTotalInt);

                    total = addition(total, tempTotal);
                    two.push(value);
                }
            }else{
                for(int c=0; c<topLength; c++) {

                    int value = (int) two.pop();
                    int tempTotalInt = value * topOne;
                    String tempTotal = Integer.toString(tempTotalInt);
                    total = addition(total, tempTotal);
                    temp.push(value);
                }
            }
        }


        //returns endstring.
            return total;
    }
}

