import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Main {
    private static int choice;
    private static int num;

    public static void main(String[] args) {
        redo();

        while (true) {
            if (choice >= 0) { // Check if user selected a valid option

                String numInput = JOptionPane.showInputDialog("Enter the number of terms you want to generate: ");
                if (numInput == null) { // Check if user clicked Cancel
                    redo();
                    continue;
                }
                try {
                    num = Integer.parseInt(numInput);
                    if (num < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid input! Number of terms must be non-negative.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid integer.");
                    continue;
                }

                String output = series();

                String seriesName = "";
                String seriesFormula = "";
                String seriesDefinition = "";
                switch (choice) {
                    case 0:
                        seriesName = "Fibonacci";
                        seriesFormula = "Fn = Fn-1 + Fn-2, with F0 = 0 and F1 = 1.";
                        seriesDefinition = "In mathematics, the Fibonacci sequence is a sequence in which each number is the sum of the two preceding ones. \n";
                        break;
                    case 1:
                        seriesName = "Lucas";
                        seriesFormula = "Formula: Ln = Ln-1 + Ln-2, with L0 = 2 and L1 = 1.";
                        seriesDefinition = "Lucas Numbers form a sequence of numbers in which each number is the sum of the two preceding ones, usually starting with 2 and 1 \n";
                        break;
                    case 2:
                        seriesName = "Tribonacci";
                        seriesFormula = "Formula: Tn = Tn-1 + Tn-2 + Tn-3, with T0 = 0, T1 = 0, and T2 = 1.";
                        seriesDefinition = "Tribonacci Numbers form a sequence of numbers in which each number is the sum of the three preceding ones, usually starting with 0, 0, and 1. \n";
                        break;
                }


                // create a custom JLabel with the desired font
                JLabel label = new JLabel("<html><div style='font-size:14px'>" + "Definition: " + seriesDefinition + "<br>" + "Formula: " + seriesFormula + "<br><br>" + seriesName + " Series: " + output + "<br><br>Do you want to generate another series?" + "</div></html>");
                label.setFont(new Font("Arial", Font.PLAIN, 20));

                int option = JOptionPane.showOptionDialog(null, label, "Recursions", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                if (option == JOptionPane.YES_OPTION) {
                    redo();
                } else {
                    break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No series selected.");
                redo();
            }
        }
    }

    public static void redo() {
        String[] options = {"Fibonacci", "Lucas", "Tribonacci"};

        JTextArea message = new JTextArea();
        message.setText("Group Members: \n\nAcosta \nBayquen \nLegaspi \nSantiago \n\nChoose a sequence to generate: ");
        message.setEditable(false);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setFont(new Font("Arial", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(message);
        scrollPane.setPreferredSize(new Dimension(600, 300));

        choice = JOptionPane.showOptionDialog(null, scrollPane, "Recursions", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }

    public static String series() {
        DecimalFormat formatter = new DecimalFormat("#,###");
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < num; i++) {
            output.append(formatter.format(series(choice, i)));
            if (i < num - 1) {
                output.append(", ");
            }
        }
        return output.toString();
    }

    public static int series(int choice, int n) {
        if (n == 0) {
            switch (choice) {
                case 0:
                    return 0; // Fibonacci
                case 1:
                    return 2; // Lucas
                case 2:
                    return 0; // Tribonacci
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

