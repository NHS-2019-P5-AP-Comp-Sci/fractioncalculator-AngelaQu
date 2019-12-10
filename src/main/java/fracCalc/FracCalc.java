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
    		equation = userInput.nextLine();
    		//Every answer will appear on a new line.
    	}
    	userInput.close();
    }

    //Takes the input given my the user and then parses it into the first fraction,
    //the operation, and the second fraction.
    //It then finds the whole number, numerator, and denominator of each fraction.
    public static String produceAnswer(String equation) {
    	int space = equation.indexOf(" ");
    	
    	String operand1 = equation.substring(0, space);
    	//This would save the first fraction (e.g. 3/4).
    	
    	String oper1 = parse(operand1);
        //This will save the output of the the method "parse" from the input 
        //operand1 into a line containing the whole number, numerator, and 
        //denominator of the fraction.
    	
    	String operation = equation.substring(space + 1, space + 2);
    	//This would save the operator (e.g. - (subtract)).
    	
    	String operand2 = equation.substring(space + 3);
    	//This would save the second fraction (e.g. 3_4/5).
        
    	String oper2 = parse(operand2);
        //This will save the output of the the method "parse" from the input 
        //operand2 into a line containing the whole number, numerator, and 
        //denominator of the fraction.
    	
    	String who1 = oper1.substring(5, oper1.indexOf("numerator"));
    	int whole1 = Integer.parseInt(who1);
    	String num1 = oper1.substring(oper1.indexOf("numerator") + 9, oper1.indexOf("denominator"));
    	int numerator1 = Integer.parseInt(num1);
    	String denom1 = oper1.substring(oper1.indexOf("denominator") + 11);
    	int denominator1 = Integer.parseInt(denom1);
    	numerator1 = improperNum(whole1, numerator1, denominator1);
    	String who2 = oper2.substring(5, oper2.indexOf("numerator"));
    	int whole2 = Integer.parseInt(who2);
    	String num2 = oper2.substring(oper2.indexOf("numerator") + 9, oper2.indexOf("denominator"));
    	int numerator2 = Integer.parseInt(num2);
    	String denom2 = oper2.substring(oper2.indexOf("denominator") + 11);
    	int denominator2 = Integer.parseInt(denom2);
    	numerator2 = improperNum(whole2, numerator2, denominator2);
        //The code above takes the long string of information from the 
    	//method "parse" and turns the individual pieces of information into 
    	//integers.
       
    	if (operation.equals("+")) {
        	return addition(numerator1, denominator1, numerator2, denominator2);
        } else if (operation.equals("-")) {
        	return subtraction(numerator1, denominator1, numerator2, denominator2);
        } else if (operation.equals("*")) {
        	return multiplication(numerator1, denominator1, numerator2, denominator2);
        } else {
        	return division(numerator1, denominator1, numerator2, denominator2);
        }
        //Depending on the type of operation, different methods will occur, so 
        //the correct answer is given.
    }
    
    //This accepts a String that is a fraction and parses it into the whole number,
    //the numerator, and the denominator.
    public static String parse (String fraction) {
    	String whole;
    	String numerator;
    	String denominator;
    	int underscore = fraction.indexOf("_");
    	int divide = fraction.indexOf("/");
    	if (underscore == -1) {
    		if (divide == -1) {
        		whole = (fraction);
    		}else 
        		whole = "0";
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
    		denominator = "1";
    	}
    	return ("whole" + whole + "numerator" + numerator + "denominator" + denominator);
    	//This would return the whole number, the numerator, and the denominator 
    	//as a String to the method "parse."
    }
    
    //Accepts the whole number, the numerator, and the denominator to then 
    //calculates the improper function's numerator. It then returns the value to 
    //the method "produceAnswer."
    public static int improperNum(int whole, int numerator, int denominator) {
    	int convertednum = 0;
    	if (whole > 0) {
    		convertednum = whole * denominator + numerator;
    	} else if (whole < 0) {
    		convertednum = whole * denominator - numerator;
    	} else {
    		convertednum = numerator;
    	}
    	return convertednum;
    }
    
    //The method for when the operator "+" is called thus adding the fractions.
    public static String addition(int numerator1, int denominator1, int numerator2, int denominator2) {
    	int numerator = ((numerator1 * denominator2) + (numerator2 * denominator1));
    	int denominator = denominator1 * denominator2;
    	return simplify(numerator, denominator);
    }
    
  //The method for when the operator "-" is called thus subtracting the fractions.
    public static String subtraction(int numerator1, int denominator1, int numerator2, int denominator2) {
    	int numerator = ((numerator1 * denominator2) - (numerator2 * denominator1));
    	int denominator = denominator1 * denominator2;
    	return simplify(numerator, denominator);
    }
    
  //The method for when the operator "*" is called thus multiplying the fractions.
    public static String multiplication(int numerator1, int denominator1, int numerator2, int denominator2) {
    	int numerator = numerator1 * numerator2;
    	int denominator = denominator1 * denominator2;
    	return simplify(numerator, denominator);
    }
    
  //The method for when the operator "/" is called thus dividing the fractions.
    public static String division(int numerator1, int denominator1, int numerator2, int denominator2) {
    	int numerator = numerator1 * denominator2;
    	int denominator = denominator1 * numerator2;
    	return simplify(numerator, denominator);
    }
    
    //Simplifies the given improper fraction into the most simplified mixed fraction
    //and then returns it as a String.
    public static String simplify(int numerator, int denominator) {
    	int whole = numerator/denominator;
    	int notmixednum = numerator%denominator;
    	if (notmixednum == 0) {
    		return (whole + "");
    	}else {
    	   	int gcf = gcf(notmixednum, denominator);
        	int truenum = notmixednum/gcf;
        	int truedenom = denominator/gcf;
        	if (whole == 0) {
        		if (numerator == 0) {
        			return "0";
        		}
        		else if ((truenum < 0 && truedenom > 0) || (truenum > 0 && truedenom < 0)){
        			return("-" + Math.abs(truenum) + "/" + Math.abs(truedenom));
        		}
        		else {
        			return(truenum + "/" + truedenom);
        		}
        	}else if (truenum == 0) {
        		return(whole + "");
        	}else if (whole != 0 && truenum < 0) {
        		truenum = Math.abs(truenum);
        		truedenom = Math.abs(truedenom);
        		return (whole + "_" + truenum + "/" + truedenom);
        	}else if (whole != 0 && truenum > 0 && truedenom < 0) {
        		return (whole + "_" + truenum + "/" + Math.abs(truedenom));
        	}
        	else{ 
        		return (whole + "_" + truenum + "/" + truedenom);
        	}
    	}
    }
    
    //Find the greatest common factor(gcf) from the numerator and denominator
    //and returns it to the method "simplify."
    public static int gcf(int notmixednum, int denominator) {
    	int gcf = 1;
    	for (int i = 1; i <= Math.abs(notmixednum); i++) {
    		if (notmixednum%i == 0 && denominator%i == 0) {
    			gcf = i;
    		}
    	}
    	return gcf;
    }
}