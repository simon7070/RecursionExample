import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RecursionExample extends JFrame implements ActionListener {
    private JTextField inputField;
    private JTextArea outputArea;

    public RecursionExample() {
        setTitle("Factorial Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // create input field
        inputField = new JTextField();
        inputField.addActionListener(this);

        // create output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        // add components to frame
        add(inputField, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText();
        try {
            int number = Integer.parseInt(input);
            int result = factorial(number);
            outputArea.append("Factorial of " + number + " is " + result + "\n");
        } catch (NumberFormatException ex) {
            outputArea.append("Invalid input. Please enter a valid integer.\n");
        }
        inputField.setText("");
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static void main(String[] args) {
        RecursionExample example = new RecursionExample();
    }
}
