import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuessTheNumberGame extends JFrame implements ActionListener {
    private JLabel titleLabel, guessLabel, resultLabel;
    private JTextField guessField;
    private JButton submitButton, newGameButton;
    private int answer;
    private int min = 1;
    private int max = 100;
    private int guessCount = 0;
    private boolean gameOver = false;

    public GuessTheNumberGame() {
        // Set up the window
        setTitle("Guess the Number Game");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set up the UI components
        titleLabel = new JLabel("I'm thinking of a number between " + min + " and " + max + ".");
        guessLabel = new JLabel("Your guess:");
        resultLabel = new JLabel("");
        guessField = new JTextField(10);
        submitButton = new JButton("Submit");
        newGameButton = new JButton("New Game");
        
        // Set up the layout
        setLayout(new GridLayout(4, 2));
        add(titleLabel);
        add(new JLabel(""));
        add(guessLabel);
        add(guessField);
        add(submitButton);
        add(newGameButton);
        add(resultLabel);
        add(new JLabel(""));
        
        // Set up the listeners
        submitButton.addActionListener(this);
        newGameButton.addActionListener(this);
        
        // Generate a new answer
        generateAnswer();
    }
    
    // Generate a new answer
    private void generateAnswer() {
        answer = (int) (Math.random() * (max - min + 1)) + min;
        guessCount = 0;
        gameOver = false;
        titleLabel.setText("I'm thinking of a number between " + min + " and " + max + ".");
        guessField.setText("");
        resultLabel.setText("");
    }
    
    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton && !gameOver) {
            // Get the user's guess
            String guessStr = guessField.getText();
            if (guessStr.equals("")) {
                resultLabel.setText("Please enter a number.");
                return;
            }
            int guess = Integer.parseInt(guessStr);
            
            // Check the user's guess
            guessCount++;
            if (guess < answer) {
                resultLabel.setText("Too low.");
            } else if (guess > answer) {
                resultLabel.setText("Too high.");
            } else {
                resultLabel.setText("You win in " + guessCount + " guesses!");
                gameOver = true;
            }
            
            // Make the AI guess
            if (!gameOver) {
                int aiGuess = (min + max) / 2;
                guessCount++;
                if (aiGuess < answer) {
                    resultLabel.setText("Your guess is " + guess + ". AI's guess is too low.");
                    min = aiGuess + 1;
                } else if (aiGuess > answer) {
                    resultLabel.setText("Your guess is " + guess + ". AI's guess is too high.");
                    max = aiGuess - 1;
                } else {
                    resultLabel.setText("You win in " + guessCount + " guesses!");
                    gameOver = true;
                }
                
                // Update the title with the new range
                titleLabel.setText("I'm thinking of a number between " + min + " and " + max + ".");
            }
        } else if (e.getSource() == newGameButton) {
            // Generate a new answer
            generateAnswer();
        }
        }
        public static void main(String[] args) {
            GuessTheNumberGame game = new GuessTheNumberGame();
            game.setVisible(true);
        }}
        