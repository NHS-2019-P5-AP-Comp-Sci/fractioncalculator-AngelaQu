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
    		//Every answer will appear on a new line.
    	}
    	userInput.close();
    }

    public static String produceAnswer(String equation) {
    	//Splits the given equation (e.g. 3/4 - 3_4/5) into the first fraction 
    	//the operator and the second fraction.
    	int space = equation.indexOf(" ");
    	String operand1 = equation.substring(0, space);
    	//This would be the first fraction (e.g. 3/4).
    	String oper1 = parse(operand1);
    	String operation = equation.substring(space + 1, space + 2);
    	//This would be the operator (e.g. - (subtract)).
    	String operand2 = equation.substring(space + 3);
    	//This would be the second fraction (e.g. 3_4/5).
        String oper2 = parse(operand2);
        //This will return the output of the the method "parse" from the input 
        //operand 2.
    	String who1 = oper1.substring(5, oper1.indexOf("numerator"));
    	int whole1 = Integer.parseInt(who1);
    	String num1 = oper1.substring(oper1.indexOf("numerator") + 9, oper1.indexOf("denominator"));
    	int numerator1 = Integer.parseInt(num1);
    	String denom1 = oper1.substring(oper1.indexOf("denominator") + 11);
    	int denominator1 = Integer.parseInt(denom1);
    	String who2 = oper2.substring(5, oper2.indexOf("numerator"));
    	int whole2 = Integer.parseInt(who2);
    	String num2 = oper2.substring(oper2.indexOf("numerator") + 9, oper2.indexOf("denominator"));
    	int numerator2 = Integer.parseInt(num2);
    	String denom2 = oper2.substring(oper2.indexOf("denominator") + 11);
    	int denominator2 = Integer.parseInt(denom2);
        //The code above is to take the long string of information from the 
    	//method "parse" and turn the individual information into integers.
        if (operation.equals("+")) {
        	return addition(whole1, numerator1, denominator1, whole2, numerator2, denominator2);
        } else if (operation.equals("-")) {
        	return subtraction(whole1, numerator1, denominator1, whole2, numerator2, denominator2);
        } else if (operation.equals("*")) {
        	return multiplication(whole1, numerator1, denominator1, whole2, numerator2, denominator2);
        } else {
        	return division(whole1, numerator1, denominator1, whole2, numerator2, denominator2);
        }
        //Depending on the type of operation, different methods will occur, so 
        //the correct answer is given.
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
    	return ("whole" + whole + "numerator" + numerator + "denominator" + denominator);
    	//This would return the whole number, the numerator, and the denominator 
    	//as a string to the method "parse."
    }
    
    public static String addition(int whole1, int numerator1, int denominator1, 
    		int whole2, int numerator2, int denominator2) {
    	//This method is for the operation "addition."
    	if (whole1 < 0 && whole2 < 0) {
    		//Calculations depend on whether or not the whole number is positive.
    		//Depending on the sign of the whole number, the method the answer is 
    		//calculated changes.
    		//This is if both the lst and 2nd fraction are < 0.
        	int mixed1 = whole1 * denominator1 - numerator1;
    		int mixed2 = whole2 * denominator2 - numerator2;
        	int realmixed1 = denominator2 * mixed1;
        	int realmixeddenom = denominator1 * denominator2;
        	int realmixed2 = denominator1* mixed2;
        	int answernum = realmixed1 + realmixed2;
        	return (answernum + "/" + realmixeddenom);
    	} else if (whole1 > 0 && whole2 < 0){
    		//This is if the 1st fraction is > 0 but the 2nd fraction is not.
    		int mixed1 = whole1 * denominator1 + numerator1;
    		int mixed2 = whole2 * denominator2 - numerator2;
        	int realmixed1 = denominator2 * mixed1;
        	int realmixeddenom = denominator1 * denominator2;
        	int realmixed2 = denominator1* mixed2;
        	int answernum = realmixed1 + realmixed2;
        	return (answernum + "/" + realmixeddenom);
    	} else if (whole2 > 0 && whole1 < 0) {
    		//This is if the 1st fraction is < 0 but the 2nd fraction is not.
    		int mixed2 = whole2 * denominator2 + numerator2;
        	int mixed1 = whole1 * denominator1 - numerator1;
        	int realmixed1 = denominator2 * mixed1;
        	int realmixeddenom = denominator1 * denominator2;
        	int realmixed2 = denominator1* mixed2;
        	int answernum = realmixed1 + realmixed2;
        	return (answernum + "/" + realmixeddenom);
    	} else {
    		//This is if both the 1st and 2nd fraction are > 0.
    		int mixed2 = whole2 * denominator2 + numerator2;
        	int mixed1 = whole1 * denominator1 + numerator1;
        	int realmixed1 = denominator2 * mixed1;
        	int realmixeddenom = denominator1 * denominator2;
        	int realmixed2 = denominator1* mixed2;
        	int answernum = realmixed1 + realmixed2;
        	return (answernum + "/" + realmixeddenom);
    	}
    }
    
    public static String subtraction(int whole1, int numerator1, int denominator1,
    		int whole2, int numerator2, int denominator2) {
    	//This method is for the operation "subtraction."
    	if (whole1 < 0 && whole2 < 0) {
    		//This is if both the lst and 2nd fraction are < 0.
        	int mixed1 = whole1 * denominator1 - numerator1;
    		int mixed2 = whole2 * denominator2 - numerator2;
        	int realmixed1 = denominator2 * mixed1;
        	int realmixeddenom = denominator1 * denominator2;
        	int realmixed2 = denominator1* mixed2;
        	int answernum = realmixed1 - realmixed2;
        	return (answernum + "/" + realmixeddenom);
    	} else if (whole1 > 0 && whole2 < 0){
    		//This is if the 1st fraction is > 0 but the 2nd fraction is not.
    		int mixed1 = whole1 * denominator1 + numerator1;
    		int mixed2 = whole2 * denominator2 - numerator2;
        	int realmixed1 = denominator2 * mixed1;
        	int realmixeddenom = denominator1 * denominator2;
        	int realmixed2 = denominator1* mixed2;
        	int answernum = realmixed1 - realmixed2;
        	return (answernum + "/" + realmixeddenom);
    	} else if (whole2 > 0 && whole1 < 0) {
    		//This is if the 1st fraction is < 0 but the 2nd fraction is not.
    		int mixed2 = whole2 * denominator2 + numerator2;
        	int mixed1 = whole1 * denominator1 - numerator1;
        	int realmixed1 = denominator2 * mixed1;
        	int realmixeddenom = denominator1 * denominator2;
        	int realmixed2 = denominator1* mixed2;
        	int answernum = realmixed1 - realmixed2;
        	return (answernum + "/" + realmixeddenom);
    	} else {
       		//This is if both the 1st and 2nd fraction are > 0.
    		int mixed2 = whole2 * denominator2 + numerator2;
        	int mixed1 = whole1 * denominator1 + numerator1;
        	int realmixed1 = denominator2 * mixed1;
        	int realmixeddenom = denominator1 * denominator2;
        	int realmixed2 = denominator1* mixed2;
        	int answernum = realmixed1 - realmixed2;
        	return (answernum + "/" + realmixeddenom);
    	}
    }
    
    public static String multiplication(int whole1, int numerator1, int denominator1, 
    		int whole2, int numerator2, int denominator2) {
    	//This is the method for the operation "multiplication."
    	if (whole1 < 0 && whole2 < 0) {
    		//This is if both the lst and 2nd fraction are < 0.
        	int mixed1 = whole1 * denominator1 - numerator1;
    		int mixed2 = whole2 * denominator2 - numerator2;
    		int answernum = mixed1 * mixed2;
        	int answerdenom = denominator1 * denominator2;
        	return (answernum + "/" + answerdenom);
    	} else if (whole1 > 0 && whole2 < 0){
    		//This is if the 1st fraction is > 0 but the 2nd fraction is not.
    		int mixed1 = whole1 * denominator1 + numerator1;
    		int mixed2 = whole2 * denominator2 - numerator2;
    		int answernum = mixed1 * mixed2;
        	int answerdenom = denominator1 * denominator2;
        	return (answernum + "/" + answerdenom);
    	} else if (whole2 > 0 && whole1 < 0) {
    		//This is if the 1st fraction is < 0 but the 2nd fraction is not.
    		int mixed2 = whole2 * denominator2 + numerator2;
        	int mixed1 = whole1 * denominator1 - numerator1;
        	int answernum = mixed1 * mixed2;
        	int answerdenom = denominator1 * denominator2;
        	return (answernum + "/" + answerdenom);
    	} else {
       		//This is if both the 1st and 2nd fraction are > 0.
    		int mixed2 = whole2 * denominator2 + numerator2;
        	int mixed1 = whole1 * denominator1 + numerator1;
        	int answernum = mixed1 * mixed2;
        	int answerdenom = denominator1 * denominator2;
        	return (answernum + "/" + answerdenom);
    	}
    }
    
    public static String division(int whole1, int numerator1, int denominator1, 
    		int whole2, int numerator2, int denominator2) {
    	//This is the method for the operation "division."
    	if (whole1 < 0 && whole2 < 0) {
    		//This is if both the lst and 2nd fraction are < 0.
        	int mixed1 = whole1 * denominator1 - numerator1;
    		int mixed2 = whole2 * denominator2 - numerator2;
        	int answernum = mixed1 * denominator2;
        	int answerdenom = mixed2 * denominator1;
        	return (answernum + "/" + answerdenom);
    	} else if (whole1 > 0 && whole2 < 0){
    		//This is if the 1st fraction is > 0 but the 2nd fraction is not.
    		int mixed1 = whole1 * denominator1 + numerator1;
    		int mixed2 = whole2 * denominator2 - numerator2;
        	int answernum = mixed1 * denominator2;
        	int answerdenom = mixed2 * denominator1;
        	return (answernum + "/" + answerdenom);
    	} else if (whole2 > 0 && whole1 < 0) {
    		//This is if the 1st fraction is < 0 but the 2nd fraction is not.
    		int mixed2 = whole2 * denominator2+ numerator2;
        	int mixed1 = whole1 * denominator1 - numerator1;
        	int answernum = mixed1 * denominator2;
        	int answerdenom = mixed2 * denominator1;
        	return (answernum + "/" + answerdenom);
    	} else {
       		//This is if both the 1st and 2nd fraction are > 0.
    		int mixed2 = whole2 * denominator2 + numerator2;
        	int mixed1 = whole1 * denominator1 + numerator1;
        	int answernum = mixed1 * denominator2;
        	int answerdenom = mixed2 * denominator1;
        	return (answernum + "/" + answerdenom);
    	}
    }
}