import java.util.*;
import java.io.*;

public class BigNumArithmetic {

    public static void main(String[] args) throws IOException {
        File source = new File(args[0]);
        LStack string1 = new LStack();
        ArrayList<String> stringArray = new ArrayList()<String>;
        int topLength=0;

        if (source.exists()) {
            //if It exists, opens up a scanner
	    Scanner s = new Scanner(source);
	    while (s.hasnextline()){
	    String raw = s.nextline();
	    String retval = raw;
	    String[] arrayRaw=raw.split(" ");
	    int count = 0;
	    ArrayList<String> inputs = new ArrayList<String>;
	    ArrayList<String> operators;= new ArrayList<String>;
	    for(String i :arrayRaw){
	    	if (i.equalsIgnoreCase("+")||i.equalsIgnoreCase("*")||i.equalsIgnoreCase("^")){
			if(i.equalsIgnoreCase("+"){
				//String val1= arrayRaw.get(i);
				//String val2= arrayRaw.get(i-1);
                String val1= string1.pop();
                String val2= string1.pop();
				retval = addition(val1,val2);
				}
			else if(i.equalsIgnoreCase("*"){
                                //String val1= arrayRaw.get(i);
                                //String val2= arrayRaw.get(i-1);
                                String val1=string1.pop();
                                string val2=string1.pop();
                                retval = multiply(val1,val2);
                                string1.push(retval);


                                }
			else if(i.equalsIgnoreCase("^"){
                                //String val1= arrayRaw.get(i);
                                //String val2= arrayRaw.get(i-1);
                                    String val1=string1.pop();
                                    String val2=string1.pop();
                                retval = exponent(val1,val2);
                                }
			}
		else{
			//inputs.add(i);
            string1.push(i);
			count++;
			}
		    System.out.println(retval);
		}
	}	    /*if It exists, opens up a file reader
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
            }*/
        }
    }

    /** Addition Method
     * This method takes two input strings, It then formats the two strings to the same length implementing leading 0s until the smaller string matches the larger.
     * Once the two strings are the same length, it begins pushing all of firstVars digits one by one onto the Linked Stack labled one. it then pushes all of the secondVars digits onto stack 2 with each digit taking a node.
     * Once finished it goes through the shared length of the two strings, popping each digit starting at the ones place. with the digit in each place popped it adds the two together, and if the carry value is marked as true, it adds an additional value of 1 to the total.
     * If a resulting value is greater than 10 it marks the Boolean Value of Carry as True, and subtracts 10 from the total. If the value is less than ten it sets the Boolean Value of Carry to false.
     * Once Carry has been checked and prepped for the next place slot, it pushes the value onto a third Linked Stack labled "Solved".
     * It then repeats this proccess for each node in the two Stacks.
     * When this is done the Solved Linked stack should have it's values for individual placement in reverse order starting at the highest place value and descending to the last which represents the ones place.
     * It then adds these to a string labeled endString, appending the values as individual characters. When the Stack is empty it returns the endString value.
     * @param firstVar The first Variable being added in string form.
     * @param secondVar The second variable being added in string form
     * @return The fully added value in string form.
     */
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
        //This is so when adding the two together any empty spots in the smaller number has a numeric value of 0.
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
/* well this was dumb
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
        }*/


    /** Multiply
     * Formats the string variables fed into the method and an independent "Total" String to having the same total length.
     *  Breaks the two formatted String variables into Stacks with each digit being stored in a seperate node.
     *  Takes the first Operand and pops the first digit off of it.
     *  The method then goes through every single digit in the second operand, multiplying the two digits together and adding it to a running total via the addition method.
     *  It does this by popping the digits from the 2nd operand stored in Stack two. After the basic calculation of the two one digit variables is done it pushes the value to the Stack temp.
     *  Once all the values have been popped from two, the next value is popped from One and the process repeats by popping each one digit value off of Temp/Two alternating between the two with each digit popped from Stack One.
     *
     * @param firstVar
     * @param secondVar
     * @return
     */
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
        String formattedTotal=String.format("%0"+topLength+"d", total);

        //Goes through the formatted string pushing each character individually to the first stack.
        for (int i=0; i<formattedFirst.length(); i++){
            one.push(formattedFirst.charAt(i));
        }
        //Does the above to a second stack
        for (int i=0; i<formattedSecond.length(); i++){
            two.push(formattedSecond.charAt(i));
        }
        //for the total length (how many digits) it goes through, pops the top off of the first stack
        for(int i=0; i<topLength; i++){
            int topOne= (int) one.pop();
            //checks if Stack two is empty or the Temp Stack is empty
            if(two.isEmpty()) {

                //Repeats the below for every single value in the Stack 2.
                for (int c = 0; c < topLength; c++) {
                    //Pops off the top value of the temp Stack
                    int value = (int) temp.pop();
                    //Multiplies the single digit value by the topOne value
                    int tempTotalInt = value * topOne;
                    //adds the string to a temp value to check the result of that step of the long multiplication
                    String tempTotal = Integer.toString(tempTotalInt);
                    //adds the tempTotal to the actual Total via the above Addition Method.
                    total = addition(total, tempTotal);
                    //pushes the value into the two stack ensuring that every value alternates between the two for each iteration.
                    two.push(value);
                }
            }else{
                //if temp is empty go through each value in Stack 2
                for(int c=0; c<topLength; c++) {
                    //Pop the top value of stack two. casting it to an Integer
                    int value = (int) two.pop();
                    //Multiply the popped digit by topOne
                    int tempTotalInt = value * topOne;
                    //Parses the integer to a string saving it to tempTotal
                    String tempTotal = Integer.toString(tempTotalInt);
                    //Adds the total to the overall multiplication total
                    total = addition(total, tempTotal);
                    //Pushes the value to Temp.
                    temp.push(value);
                }
            }
        }


        //returns the final Total.
            return total;
    }
}
public String exponent(String val1, String val2){
	LinkedList<Integer> numbah = new LinkedList<Integer>;
	int orig = Integer.parseInt(val1);
	int expo = Integer.parseInt(val2);
	String stexpo = ""
	if (expo == 0){return 1;}
	//else if (expo == 1){return (String)val1};
	else if (expo%2 ==0){
		stexpo=Integer.toString(expo/2);
		return exponent(multiply(val1, val1),stexpo)};
	else if (expo%2 ==1){
		stexpo=Integer.toStrong(expo-1);
		return(multiply(val1,val1),stexpo);
}

//scrap code
/**
 * public static String addition(String firstVar, String secondVar){
 *         //three stacks, two for containing the character of each source numeric string, one for the solution
 *         LStack one= new LStack();
 *         LStack two= new LStack();
 *         LStack solved=new LStack();
 *         //Placeholder declaration for the returned numerical string
 *         String endString="";
 *         //boolean for carrying a 1
 *         boolean carry=false;
 *         //top length integer for sizing purposes
 *         int lengthDif=0;
 *         int topLength=0;
 *         //check if firstVar or secondVar are longer, then set the longest length to top leength
 *         if(firstVar.length()>=secondVar.length()){
 *                 topLength=firstVar.length();
 *         lengthDif=firstVar.length()-secondVar.length();
 *                 for (int i=0; i<firstVar.length(); i++) {
 *                         one.push(firstVar.charAt(i));
 *                 }
 *                 for (int i=0; i<lengthDif; i++) {
 *                         two.push(0);
 *                 }for (int i=0; i<secondVar.length(); i++) {
 *                         two.push(secondVar.charAt(i));}
 *                 }else{
 *                 topLength=secondVar.length();
 *                 lengthDif=secondVar.length()-firstVar.length();
 *                 for (int i=0; i<secondVar.length(); i++) {
 *                         one.push(secondVar.charAt(i));
 *                 }
 *                 for (int i=0; i<lengthDif; i++) {
 *                         two.push(0);
 *                 }
 *                 for (int i=0; i<firstVar.length(); i++){
 *                         two.push(secondVar.charAt(i));
 *                 }
 *         }
 *         //formats two new strings to the top length, formatting the smaller one with leading 0s (technically reformats both but the larger doesn't undergo any changes)
 *
 *         //Goes through the formatted string pushing each character individually to the first stack.
 *         //for the total length (how many digits) it goes through, pops the top off of each stack (which would be the ones place)
 *         for(int i=0; i<topLength; i++){
 *         int total=0;
 *         int topOne= Integer.parseInt((String) one.pop());
 *         int topTwo= Integer.parseInt((String) two.pop());
 *         //checks if the previous calculation had a carried one.
 *         if(carry) {
 *         //adds the carry value and the two popped values.
 *         total = +1 + topOne + topTwo;
 *         }else{
 *         //no carry value just adds two values.
 *         total=topOne+topTwo;
 *         }
 *         //checks if the total is greater than 10
 *         if(total>10){
 *         //sets carry value to true and subtracts 10 from the total value
 *         carry=true;
 *         total=total-10;
 *         }else{
 *         //sets carry to false
 *         carry=false;
 *         //adds the values to a "Solved" stack in reverse order.
 *         } solved.push(total);
 *         }
 *         //while solved is not empty it goes through popping each numeric char and then adds them to end string.
 *         while (!solved.isEmpty()){
 *         char newChar= (char) solved.pop();
 *         endString=endString+newChar;
 *         }
 *         //returns endstring.
 *         return endString;
 *         }
 */

