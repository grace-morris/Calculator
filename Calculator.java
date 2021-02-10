import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This program creates a functioning Calculator using Java GUI and Event Handling. It can add, subtract, multiply, divide, 
 * and calculate square roots.
 * @author Grace Morris
 *
 */
public class Calculator extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton numberButtons[] = new JButton[10];
	private String captions[] = {	
			"1","2","3",
			"4","5","6",
			"7","8","9",
			"0"
								};
	private JButton addButton = new JButton("+");
	private JButton subButton = new JButton("-");
	private JButton multButton = new JButton("*");
	private JButton divButton = new JButton("/");
	private JButton equalsButton = new JButton("=");
	private JButton clearButton = new JButton("AC");
	private JButton sqrtButton = new JButton("sqrt");
	private JButton backButton = new JButton("delete");
	
	private double a;
	private double b;
	private JPanel buttonPanel = new JPanel(new GridLayout(6, 3));
	private JTextField output = new JTextField(20);
	Container frame;
	
	/**
	 * This constructs the Calculator, creating the window, the button panel, and the layout, as well as adding an ActionListener 
	 * to every button
	 */
	public Calculator()
	{
		frame = getContentPane(); //puts the window on screen
		for (int i = 0; i < captions.length; i++) //loop to create buttons
		{
			numberButtons[i] = new JButton(captions[i]); //creates a new button for each number
			buttonPanel.add(numberButtons[i]); //adds the button to the panel
			numberButtons[i].addActionListener(this);
		}
		
		buttonPanel.add(addButton);
		addButton.addActionListener(this);
		
		buttonPanel.add(subButton);
		subButton.addActionListener(this);
		
		buttonPanel.add(multButton);
		multButton.addActionListener(this);
		
		buttonPanel.add(divButton);
		divButton.addActionListener(this);
		
		buttonPanel.add(sqrtButton);
		sqrtButton.addActionListener(this);
		
		buttonPanel.add(equalsButton);
		equalsButton.addActionListener(this);
		
		buttonPanel.add(clearButton);
		clearButton.addActionListener(this);
		
		buttonPanel.add(backButton);
		backButton.addActionListener(this);
		
		output.setEditable(false);
		
		frame.setLayout(new BorderLayout());
		frame.add(output,BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	/**
	 * This method performs an action based on the button that was pressed. It is an override from the ActionListener interface.
	 * @param e is the ActionEvent that took place (the button press)
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton readButton = (JButton) e.getSource();
		String previous = output.getText(); //previous = the current text in the text field
		String current = "";

		try
		{
			switch (readButton.getActionCommand()) //determines what button was pressed and what action to take.
			{
				case "0":
					current = "0";
					break;
				case "1":
					current = "1";
					break;
				case "2":
					current = "2";
					break;
				case "3":
					current = "3";
					break;
				case "4":
					current = "4";
					break;
				case "5":
					current = "5";
					break;
				case "6":
					current = "6";
					break;
				case "7":
					current = "7";
					break;
				case "8":
					current = "8";
					break;
				case "9":
					current = "9";
					break;
				case "+":
					current = "+";
					a = Integer.parseInt(output.getText());
					break;
				case "-":
					current = "-";
					a = Integer.parseInt(output.getText());
					break;
				case "*":
					current = "*";
					a = Integer.parseInt(output.getText());
					break;
				case "/":
					current = "/";
					a = Integer.parseInt(output.getText());
					break;
				case "AC":
					previous = "";
					break;
				case "sqrt":
					current = "sqrt";
					previous = "";
					break;
				case "delete":
					previous = previous.substring(0,previous.length()-1);
					break;
				case "=":
					String s = output.getText();
					String next = "";
					previous = "";
					for (int i = s.length()-1; i >= 0; i--) //go through the current output to determine what operation was just 
															//performed.
					{
						if (Character.isDigit(s.charAt(i))) //if the output is a number
						{
							next += s.charAt(i);
						}
						else if (s.charAt(i) == '+') //if the output is adding two numbers
						{
							current = next;
							b = Integer.parseInt(current);
							current = add((int)a, (int)b);
							break;
						}
						else if (s.charAt(i) == '-') //if the output is subtracting two numbers
						{
							current = next;
							b = Integer.parseInt(current);
							current = sub((int)a, (int)b);
							break;
						}
						else if (s.charAt(i) == '*') //if the output is multiplying two numbers
						{
							current = next;
							b = Integer.parseInt(current);
							current = multiply((int)a, (int)b);
							break;
						}
						else if (s.charAt(i) == '/') //if the output is dividing two numbers
						{
							current = next;
							b = Double.parseDouble(current);
							current = divide(a, b);
							break;
						}
						else if (s.charAt(i) == 't') //if the output is asking for sqrt
						{
							current = next;
							current = Double.toString(Math.sqrt(Double.parseDouble((current))));
							break;
						}
					}
			}
		}
		catch (NumberFormatException nfe) //if the user tries to do more than one operation before hitting equals
		{
			JOptionPane.showMessageDialog(
					null,
					"Please perform only one operation at a time.",
					"ERROR",
					JOptionPane.INFORMATION_MESSAGE); 
		}
		output.setText(previous + current);
	}
	
	/**
	 * This method adds two integers and returns their sum as a string.
	 * @param a the first number to be added
	 * @param b the second number to be added
	 * @return the sum a as a String
	 */
	private final String add(int a, int b)
	{
		
		return Integer.toString(a+b);
	}
	
	/**
	 * This method subtracts two integers and returns their difference as a string.
	 * @param a the first number to be subtracted
	 * @param b the second number to be subtracted
	 * @return the difference a as a String
	 */
	private final String sub(int a, int b)
	{
		return Integer.toString(a-b);
	}
	
	/**
	 * This method multiplies two integers and returns their product as a string.
	 * @param a the first number to be multiplied
	 * @param b the second number to be multiplied
	 * @return the product a as a String
	 */
	private final String multiply(int a, int b)
	{
		return Integer.toString(a*b);
	}
	
	/**
	 * This method divides two integers and returns their quotient as a string.
	 * @param a the first number to be divided
	 * @param b the second number to be divided
	 * @return the quotient a as a String
	 */
	private final String divide(double a, double b)
	{
		return Double.toString(a/b);
	}
	
	/**
	 * Main is used in this case only to create an instance of Calculator.
	 */
	public static void main(String [] args)
	{
		Calculator calc = new Calculator();
	}
}
