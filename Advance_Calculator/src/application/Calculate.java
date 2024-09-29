package application;

public class Calculate {

	public float calculateUnaryNumber(float number1,String operator){
		switch (operator) {
		case "FLR": //Flooring
            return (float) Math.floor(number1);	
        case "CEIL": //Ceiling
            return (float) Math.ceil(number1);
        case "INT": //Rounding off 
            return (float) Math.round(number1);
		case "√": //Square root
			return (float)Math.sqrt(number1);
		case "∛": //Cube root
            return (float)Math.cbrt(number1); 
		case "log x": 
	            return (float) Math.log10(number1);
	    case "log₂ x":
	            return (float) ((float) Math.log(number1) / Math.log(2));
		}
		return 0;
	}
	
	public float calculateBinaryNumber(float number1, float number2, String operator){
		switch (operator) {
		case "+": //Addition
			return number1 + number2;
		case "-": //Subtraction
			return number1 - number2;
		case "*": //Multiplication
			return number1 * number2;
		case "/": //Division
			if(number2==0)
				return 0;
			return number1 / number2;
		case "//":
		    if (number2 != 0) {
		        return (int) (number1 / number2);
		    } else {
		        throw new ArithmeticException("ERROR");
		    }
		case "%": //Modulus(Getting the remainder)
			return (int) number1 % number2;
		case "xʸ": //Exponent 
			return (float) Math.pow(number1, number2);
		}
		return 0;
	}
	public float calculateUnaryNumber(Float number1, String operator) {
		// TODO Auto-generated method stub
		return 0;
	}

}