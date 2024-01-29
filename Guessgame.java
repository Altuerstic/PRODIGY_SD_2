import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Guessgame extends JFrame {

    private JTextField guessField;
    private JButton guessButton;
    private JTextArea resultArea;
    private int lowerBound = 1;
    private int upperBound = 100;
    private int randomNumber;
    private int attempts = 0;

    public Guessgame() {
        // Set up the main frame
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Generate a random number
        Random random = new Random();
        randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

        // Create and place components in the frame
        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        // Make the frame visible
        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel promptLabel = new JLabel("Enter your guess:");
        promptLabel.setBounds(20, 20, 150, 25);
        panel.add(promptLabel);

        guessField = new JTextField();
        guessField.setBounds(180, 20, 150, 25);
        panel.add(guessField);

        guessButton = new JButton("Submit Guess");
        guessButton.setBounds(20, 60, 150, 25);
        panel.add(guessButton);

        resultArea = new JTextArea();
        resultArea.setBounds(20, 100, 350, 150);
        resultArea.setEditable(false);
        panel.add(resultArea);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });
    }

    private void processGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if (userGuess < randomNumber) {
                resultArea.append("Too low! Try again.\n");
            } else if (userGuess > randomNumber) {
                resultArea.append("Too high! Try again.\n");
            } else {
                resultArea.append("Congratulations! You guessed the number in " + attempts + " attempts.\n");
                guessButton.setEnabled(false); // Disable the button after successful guess
            }

            guessField.setText(""); // Clear the input field after each guess

        } catch (NumberFormatException ex) {
            resultArea.append("Invalid input. Please enter a valid number.\n");
        }
    }

    public static void main(String[] args) {
        new Guessgame();
    }
}
