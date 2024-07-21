import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Calculator implements ActionListener{
	
	// declaration of instance variables
	JFrame frame; 
	JTextField textfield; 
	JButton[] numberButtons = new JButton[10]; 
	JButton[] functionButtons = new JButton[9]; 
	JButton addButton, subButton, mulButton, divButton; 
	JButton decButton, equButton, delButton, clrButton, negButton; 
	JPanel panel; 
	
	Font myFont = new Font("Monospaced", Font.BOLD, 30);

	double num1=0, num2=0, result=0;
	char operator; 
	
	
	// constructor of Calculator class instance
	Calculator() {
		
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // stop app when pressing "X" or exit
		frame.setSize(420, 550);                                 // set size of the window
		frame.setLayout(null);                                   // null layout manager (manually specify the position and size of each component within the frame)
        frame.setResizable(false);                               // Make the frame non-resizable
        
		textfield = new JTextField();            // create textfield object instance
		textfield.setBounds(50, 25, 300, 50);    // define bounds of textfield (x, y, width, height)
		textfield.setFont(myFont);               // set font of text inside textfield
		textfield.setEditable(false);            // block the editing of the textfield (only by typing on buttons the numbers must be added)
		
		// init button for operations
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("DEL");
		clrButton = new JButton("CLR");
		negButton = new JButton("(-)");
		
		// add buttons to array of functionButtons 
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;
		
		// loop through functionButtons and configure them
		for (int i=0; i<9; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);  // turn off focusability of button
		}
		
		// init button for numbers using for loop
		for (int i=0; i<10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));  // init button, set the value for it and populate numberButtons array
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);  // turn off focusability of button
		}
		
		// define boundaries for negative, delete and clear buttons (not part of grid array structure)
		negButton.setBounds(50, 430, 100, 50);
		delButton.setBounds(150, 430, 100, 50);
		clrButton.setBounds(250, 430, 100, 50);

        // create a JPanel (to hold the numberButtons and functionButtons)
		panel = new JPanel(); 
		panel.setBounds(50, 100, 300, 300); 
		panel.setLayout(new GridLayout(4, 4, 2, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		
		// add buttons of numberButtons to panel object 
		panel.add(numberButtons[1]); 
		panel.add(numberButtons[2]); 
		panel.add(numberButtons[3]); 
		panel.add(addButton); 
		panel.add(numberButtons[4]); 
		panel.add(numberButtons[5]); 
		panel.add(numberButtons[6]); 
		panel.add(subButton);
		panel.add(numberButtons[7]); 
		panel.add(numberButtons[8]); 
		panel.add(numberButtons[9]); 
		panel.add(mulButton);
		panel.add(decButton);
		panel.add(numberButtons[0]); 
		panel.add(equButton);
		panel.add(divButton);
		
		// add panel and buttons to the frame object
		frame.add(panel);
		frame.add(negButton);
		frame.add(delButton);
		frame.add(clrButton);
		frame.add(textfield);     // add the textfield object to frame
		frame.setVisible(true);   // make frame visible
	}
	
	
	public static void main(String[] args) {
		
		// create instance of calculator class 
		Calculator calc = new Calculator(); 
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<10; i++) {
			if(e.getSource() == numberButtons[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
			}
		}
		
		// add functionality for decButton
		if(e.getSource()==decButton) {
			textfield.setText(textfield.getText().concat("."));
		}
		
		// add functionality for addButton
		if(e.getSource()==addButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '+';
			textfield.setText("");
		}
		
		// add functionality for subButton
		if(e.getSource()==subButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '-';
			textfield.setText("");
		}
		
		// add functionality for mulButton
		if(e.getSource()==mulButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '*';
			textfield.setText("");
		}
		
		// add functionality for divButton
		if(e.getSource()==divButton) {
			num1 = Double.parseDouble(textfield.getText());
			operator = '/';
			textfield.setText("");
		}
		
		// add functionality to equButton
		if(e.getSource()==equButton) {
			num2 = Double.parseDouble(textfield.getText()); 
			
			switch(operator) {
			case '+':
				result = num1 + num2;
				break;
			case '-':
				result = num1 - num2;
				break;
			case '*':
				result = num1 * num2;
				break;
			case '/':
				result = num1 / num2;
				break;
			}
			textfield.setText(String.valueOf(result));
			num1 = result;
		}
		
		// add functionality to clrButton
		if(e.getSource()==clrButton) {
			textfield.setText("");
		}
		
		// add functionality to delButton
		if(e.getSource()==delButton) {
			String string = textfield.getText(); 
			textfield.setText("");
			
			// delete whole string, then display everything but the last char
			for(int i=0; i<string.length()-1; i++) {
				textfield.setText(textfield.getText()+ string.charAt(i));
			}
		}
		
		// add functionality to negButton
		if(e.getSource()==negButton) {
			double temp = Double.parseDouble(textfield.getText());
			temp*= -1;  // switch the sign by multiplication with -1
			textfield.setText(String.valueOf(temp));
		}	
	}
}
