package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AdvanceCalculatorController {

	@FXML
	private AnchorPane AnchorPane;

	@FXML
	private Label resultField;

	@FXML
	private Label variable;

	@FXML
	private Button Quadratic;

	private float number1 = 0;
	private float number2 = 0;

	private String operator = "";

	private boolean start = true;

	private Calculate calculate = new Calculate();

	// Getting the output
	@FXML
	public void processNumber(ActionEvent event) {
		if (start) {
			resultField.setText("");
			start = false;
		}
		String value = ((Button) event.getSource()).getText();
		resultField.setText(resultField.getText() + value);
	}

	// Making the number negative
	@FXML
	public void toggleSign(ActionEvent event) {
		String currentText = resultField.getText();

		if (!currentText.isEmpty() && !currentText.equals("-")) {
			if (currentText.startsWith("-")) {
				resultField.setText(currentText.substring(1));
			} else {
				resultField.setText("-" + currentText);
			}
		}
	}

	public void processUnaryOperator(ActionEvent event) {
	    String value = ((Button) event.getSource()).getText();
	    if (!operator.isEmpty()) {
	        return;
	    }

	    operator = value;
	    number1 = Float.parseFloat(resultField.getText());
	    resultField.setText("");

	    float output = calculate.calculateUnaryNumber(number1, operator);

	    // Check if the result is an integer (no decimal part)
	    if (output == Math.floor(output)) {
	        resultField.setText(String.valueOf((int) output)); // Cast to int to avoid .0
	    } else {
	        resultField.setText(String.valueOf(output)); // Keep the floating point if necessary
	    }

	    operator = "";
	}

	// Process binary operations
	public void processBinaryOperator(ActionEvent event) {
	    String value = ((Button) event.getSource()).getText();
	    
	    if (!value.equals("=")) {
	        operator = value;
	        number1 = Float.parseFloat(resultField.getText());
	        resultField.setText("");
	    } else {
	        number2 = Float.parseFloat(resultField.getText());
	        float output = calculate.calculateBinaryNumber(number1, number2, operator);
	        
	        // Check if the result is an integer (no decimal part)
	        if (output == Math.floor(output)) {
	            resultField.setText(String.valueOf((int) output)); // Display integer
	        } else {
	            resultField.setText(String.valueOf(output)); // Display floating-point result
	        }
	        
	        operator = "";
	    }
	}

	
	//Factorial
	@FXML
    private void calculateFactorial(ActionEvent event) {
        try {
            int number = Integer.parseInt(resultField.getText());
            if (number < 0) {
            	resultField.setText("Error");
                return;
            }

            long factorial = 1;
            for (int i = 1; i <= number; i++) {
                factorial *= i;
            }

            resultField.setText("" + factorial);
        } catch (NumberFormatException e) {
        	resultField.setText("Error");
        }
	}
	

	// Clear all input
	public void ClearFunction(ActionEvent event) {
		operator = "";
		start = true;
		resultField.setText("");
	}

	// Delete the last character from input
	@FXML
	void delete(ActionEvent event) {
		String text = resultField.getText();
		if (!text.isEmpty()) {
			text = text.substring(0, text.length() - 1);
			resultField.setText(text);
		}
	}

// Summation, Double Summation, Product Notation, Double Product Notation, Quadratic Equation
	private float A = 0;
	private float B = 0;
	private float C = 0;
	private float D = 0;

	@FXML
	private void handleSetA(ActionEvent event) {
		setNumberFromInput((num) -> A = num);
	}

	@FXML
	private void handleSetB(ActionEvent event) {
		setNumberFromInput((num) -> B = num);
	}

	@FXML
	private void handleSetC(ActionEvent event) {
		setNumberFromInput((num) -> C = num);
	}

	@FXML
	private void handleSetD(ActionEvent event) {
		setNumberFromInput((num) -> D = num);
	}

	@FXML
	private void handleSummation() {
		float result = A + B;
		resultField.setText("" + result);
	}

	@FXML
	private void handleDoubleSummation() {
		float result = (A + B) + (C + D);
		resultField.setText("" + result);
	}

	@FXML
	private void handleProductNotation() {
		float result = A * B;
		resultField.setText("" + result);
	}

	@FXML
	private void handleDoubleProductNotation() {
		float result = (A * B) * (C * D);
		resultField.setText("" + result);
	}

	@FXML
	private void handleFactorialSum() {
		float result = factorial((int) A) + factorial((int) B);
		resultField.setText("" + result);
	}

	@FXML
	private void handleFactorialDivision() {
		if (B == 0) {
			resultField.setText("Error");
		} else {
			float result = factorial((int) A) / factorial((int) B);
			resultField.setText("" + result);
		}
	}

	private void setNumberFromInput(java.util.function.Consumer<Float> numberSetter) {
		try {
			float number = Float.parseFloat(resultField.getText());
			numberSetter.accept(number);
			resultField.setText(""); // Clear field after setting number
			updateVariableField();
		} catch (NumberFormatException ex) {
			resultField.setText("Invalid input");
		}
	}

	private void updateVariableField() {
		variable.setText("A&X: " + A + " B&Y: " + B + " C&Z: " + C + " D: " + D);
	}

	private float factorial(int num) {
		if (num <= 1) {
			return 1;
		}
		return num * factorial(num - 1);
	}

	@FXML
	private void handlePowerOperation() {
		int result = (int) Math.pow(A, Math.pow(B, C)); // A^(B^C)
		resultField.setText("" + result);
	}

	@FXML
	private void handleQuadraticEquation() {
		// Solves the quadratic equation using a, b, and c
		if (A == 0) {
			resultField.setText("(a cannot be 0).");
			return;
		}

		float discriminant = B * B - 4 * A * C;
		if (discriminant > 0) {
			// Two real and distinct roots
			float root1 = (-B + (float) Math.sqrt(discriminant)) / (2 * A);
			float root2 = (-B - (float) Math.sqrt(discriminant)) / (2 * A);
			resultField.setText("" + root1 + "," + root2);
		} else if (discriminant == 0) {
			// One real root
			float root = -B / (2 * A);
			resultField.setText("" + root);
		} else {
			// Complex roots
			float realPart = -B / (2 * A);
			float imaginaryPart = (float) Math.sqrt(-discriminant) / (2 * A);
			resultField.setText("" + realPart + " ± " + imaginaryPart + "i");
		}
	}
}
