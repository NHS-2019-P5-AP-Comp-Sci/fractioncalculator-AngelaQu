/**
 * @author Mr. Rasmussen
 */

package fracCalc;
import java.util.*;
public class FracCalc {

    public static void main(String[] args) {
    	Scanner userInput = new Scanner(System.in);
    	String equation = userInput.nextLine();
    	//The user inputs the equation he/she wants solved (e.g. 3/4 - 3_4/5).
    	while (!equation.equals("quit")) {
    		//Until the user types "quit" the program will continue.
    		System.out.println(produceAnswer(equation));
    		//Prints the answer.
    		equation = userInput.nextLine();
    	}
    	userInput.close();
    }

    public static String produceAnswer(String equation) {
    	//Splits the given equation (e.g. 3/4 - 3_4/5) into the first fraction 
    	//the operator and the second fraction.
    	int space = equation.indexOf(" ");
    	String operand1 = equation.substring(0, space);
    	//This would be the first fraction (e.g. 3/4).
    	String operation = equation.substring(space + 1, space + 2);
    	//This would be the operator (e.g. - (substract)).
    	String operand2 = equation.substring(space + 3);
    	//This would be the second fraction (e.g. 3_4/5).
        return parse(operand2);
        //This will return the output of the the method "parse" from the input 
        //operand 2.
    }
    
    public static String parse (String fraction) {
    	String whole;
    	String numerator;
    	String denominator;
    	int underscore = fraction.indexOf("_");
    	int divide = fraction.indexOf("/");
    	if (underscore == -1) {
    		if (divide == -1) {
        		whole = (fraction);
        		//If there is no division sign anywhere in the fraction but there
        		//also is no underscore, this means the entire value given is the
        		//whole number thus setting the whole number value as the entire
        		//fraction.
    		}else 
        		whole = "0";
    			//If there is no whole number but there is a fraction then the 
    			//whole number is set to 0.
    		}
    	else {
    		whole = fraction.substring(0, underscore);
    		//Finding the whole number of the fraction given.
    	}
    	if (divide != -1) {
    		numerator = fraction.substring(underscore + 1, divide);
    		//Finding the numerator of the fraction given.
    		denominator = fraction.substring(divide + 1);
    		//Finding the denominator of the fraction given.
    	} else {
    		numerator = "0";
    		//If the division sign does not exist in the fraction given then the
    		//numerator does not exist thus setting numerator to 0.
    		denominator = "1";
    		//If the division sign does not exist in the fraction given then the 
    		//denominator does not exist but if the denominator is 0 then the 
    		//number is mathematically impossible thus setting the denominator
    		//value to 1.
    	}
    	return ("whole:" + whole + " numerator:" + numerator + " denominator:" + denominator);
    	//This would return the whole number, the numerator, and the denominator 
    	//to the method "parse" in the form "whole:x numerator:y denominator:z."
    	//(e.g. whole:3 numerator:4 denominator:5).
    }
}
