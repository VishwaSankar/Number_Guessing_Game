package oasis;


	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.Random;

	public class NumberGuessingGameGUI extends JFrame {
	    private int lowerBound = 1;
	    private int upperBound = 100;
	    private int randomNumber;
	    private int numberOfTries = 0;

	    private JLabel titleLabel;
	    private JLabel promptLabel;
	    private JTextField guessField;
	    private JButton guessButton;
	    private JTextArea resultArea;

	    public NumberGuessingGameGUI() {
	        setTitle("Number Guessing Game");
	        setSize(400, 200);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new FlowLayout());

	        titleLabel = new JLabel("Welcome to the Number Guessing Game!");
	        promptLabel = new JLabel("Guess a number between " + lowerBound + " and " + upperBound + ":");
	        guessField = new JTextField(10);
	        guessButton = new JButton("Guess");
	        resultArea = new JTextArea(5, 30);
	        resultArea.setEditable(false);

	        add(titleLabel);
	        add(promptLabel);
	        add(guessField);
	        add(guessButton);
	        add(resultArea);

	        initializeGame();

	        guessButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                checkGuess();
	            }
	        });
	    }

	    private void initializeGame() {
	        Random random = new Random();
	        randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
	        numberOfTries = 0;
	        resultArea.setText("");
	        guessField.setText("");
	        guessButton.setEnabled(true);
	    }

	    private void checkGuess() {
	        try {
	            int userGuess = Integer.parseInt(guessField.getText());
	            numberOfTries++;

	            if (userGuess < lowerBound || userGuess > upperBound) {
	                resultArea.append("Please enter a number between " + lowerBound + " and " + upperBound + ".\n");
	            } else if (userGuess < randomNumber) {
	                resultArea.append("Try higher. Tries: " + numberOfTries + "\n");
	            } else if (userGuess > randomNumber) {
	                resultArea.append("Try lower. Tries: " + numberOfTries + "\n");
	            } else {
	                resultArea.append("Congratulations! You've guessed the correct number: " + randomNumber + "\n");
	                resultArea.append("It took you " + numberOfTries + " tries.\n");
	                guessButton.setEnabled(false);
	            }
	        } catch (NumberFormatException ex) {
	            resultArea.append("Please enter a valid number.\n");
	        }

	        guessField.setText("");
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new NumberGuessingGameGUI().setVisible(true);
	            }
	        });
	    }
	}


