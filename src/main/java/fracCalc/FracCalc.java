/**
 * @author Mr. Rasmussen
 */

package fracCalc;
import java.util.*;
public class FracCalc {

    public static void main(String[] args) {
    	Scanner userInput = new Scanner(System.in);
    	String equation = userInput.nextLine();
   		System.out.println(produceAnswer(equation));
    	//while (!equation.equals("quit")) {
    		//System.out.println(produceAnswer(equation));
    		//equation = userInput.nextLine();
    	//}
    	userInput.close();
    }

    public static String produceAnswer(String input) {
    	int space = input.indexOf(" ");
    	String operand1 = input.substring(0, space);
    	String operation = input.substring(space + 1, space + 2);
    	String operand2 = input.substring(space + 3);
        return operand2;
    }
}
