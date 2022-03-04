import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BigNumArithmetic {

    public static void main(String[] args) throws IOException {
        File source = new File(args[0]);
        LStack string1 = new LStack();
        ArrayList stringArray=new ArrayList();

        if (source.exists()) {
            //if It exists, opens up a file reader
            FileReader reader = new FileReader(source);
            StringBuilder sb = new StringBuilder();
            char ch;
            while ((ch = (char) reader.read()) >= 0) {
                //intended to catch/handle linebreaks, isn't working properly at the moment been working on this for the past 6 hours
                while (ch!= '\n') {

                    if (ch == ' ') {
                        //Catches spaces, when it finds a whitespace it "locks in" the previous value, and utilizing a simple counter variable, saves it to value 1 (corresponding to red) 2 (corresponding to green) or 3(corresponding to Blue)
                        String value = sb.toString();
                        sb.setLength(0);
                        stringArray.add(value);
                    }

                }
            }
        }
    }
