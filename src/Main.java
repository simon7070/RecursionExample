import javax.swing.*;
import java.awt.*;

public class Main {

    public static int num;
    public static int choice;

    public static void main(String[] args) {
        String[] options = {"Fibonacci", "Lucas", "Tribonacci"};

        JTextArea message = new JTextArea();
        message.setText("Group Members: \nAcosta \nBayquen \nLegaspi \nSantiago \n\nChoose one: ");
        message.setEditable(false);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(message);
        scrollPane.setPreferredSize(new Dimension(300, 150));

        choice = JOptionPane.showOptionDialog(null, scrollPane, "Recursions", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        String numInput = JOptionPane.showInputDialog("Enter the number of terms you want to generate: ");

        try {
            num = Integer.parseInt(numInput);
            if (num < 0) {
                JOptionPane.showMessageDialog(null, "Invalid input! Number of terms must be non-negative.");
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid integer.");
            System.exit(0);
        }

        String output = series();

        if (choice == 0) {
            JOptionPane.showMessageDialog(null, "Definition: In mathematics, the Fibonacci sequence is a sequence in which each number is the sum of the two preceding ones. \n" + "Fn = Fn-1 + Fn-2, with F0 = 0 and F1 = 1.\n" + options[choice] + " Series:\n" + output);
        } else if (choice == 1) {
            JOptionPane.showMessageDialog(null, "Definition: Lucas Numbers form a sequence of numbers in which each number is the sum of the two preceding ones, usually starting with 2 and 1 \n" + "Formula: Ln = Ln-1 + Ln-2, with L0 = 2 and L1 = 1.\n" + options[choice] + " Series:\n" + output);
        } else {
            JOptionPane.showMessageDialog(null, "Definition: Tribonacci Numbers form a sequence of numbers in which each number is the sum of the three preceding ones, usually starting with 0, 0, and 1. \n" + "Formula: Tn = Tn-1 + Tn-2 + Tn-3, with T0 = 0, T1 = 0, and T2 = 1.\n" + options[choice] + " Series:\n" + output);
        }
    }

    public static String series() {
        String output = "";
        for (int i = 0; i < num; i++) {
            output += series(choice, i) + "\n";
        }
        return output;
    }

    public static int series(int choice, int n) {
        if (n == 0) {
            switch (choice) {
                case 0:
                    return 0; // Fibonacci
                case 1:
                    return 2; // Lucas
                case 2:
                    return 1; // Tribonacci
            }
        } else if (n == 1) {
            return 1;
        } else if (n == 2 && choice == 2) { // for Tribonacci series only
            return 1;
        } else {
            switch (choice) {
                case 0:
                case 1:
                    return series(choice, n - 1) + series(choice, n - 2); // Fibonacci and Lucas
                case 2:
                    return series(choice, n - 1) + series(choice, n - 2) + series(choice, n - 3); // Tribonacci
                default:
                    return -1; // Error: invalid choice
            }
        }
        return choice;
    }
}