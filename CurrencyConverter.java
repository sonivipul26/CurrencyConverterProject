import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverter extends JFrame implements ActionListener {

    String[] currencies = {
        "United States (USD)", "India (INR)", "European Union (EUR)", "United Kingdom (GBP)",
        "Japan (JPY)", "Australia (AUD)", "Canada (CAD)", "Switzerland (CHF)",
        "China (CNY)", "Singapore (SGD)", "New Zealand (NZD)", "South Africa (ZAR)"
    };

    JComboBox<String> fromCurrency, toCurrency;
    JTextField amountField, resultField;
    JButton convertButton;
    JLabel resultLabel;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(650, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 0, 0, 100)); 
        JLabel titleLabel = new JLabel("Currency Converter");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        backgroundPanel.add(titlePanel, BorderLayout.NORTH);

       
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(255, 255, 255, 180)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(amountLabel, gbc);

        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1; gbc.gridy = 0;
        centerPanel.add(amountField, gbc);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(fromLabel, gbc);

        fromCurrency = new JComboBox<>(currencies);
        fromCurrency.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1; gbc.gridy = 1;
        centerPanel.add(fromCurrency, gbc);

        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 2;
        centerPanel.add(toLabel, gbc);

        toCurrency = new JComboBox<>(currencies);
        toCurrency.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1; gbc.gridy = 2;
        centerPanel.add(toCurrency, gbc);

        convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Arial", Font.BOLD, 20));
        convertButton.setBackground(new Color(50, 150, 255));
        convertButton.setForeground(Color.WHITE);
        convertButton.addActionListener(this);
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        centerPanel.add(convertButton, gbc);

        resultLabel = new JLabel("Result:");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 1;
        centerPanel.add(resultLabel, gbc);

        resultField = new JTextField();
        resultField.setFont(new Font("Arial", Font.PLAIN, 20));
        resultField.setEditable(false);
        gbc.gridx = 1; gbc.gridy = 4;
        centerPanel.add(resultField, gbc);

        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = extractCurrencyCode((String) fromCurrency.getSelectedItem());
            String to = extractCurrencyCode((String) toCurrency.getSelectedItem());
            double result = convertCurrency(amount, from, to);
            resultField.setText(String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
        }
    }

    private String extractCurrencyCode(String selection) {
        int start = selection.indexOf('(');
        int end = selection.indexOf(')');
        if (start != -1 && end != -1) {
            return selection.substring(start + 1, end);
        }
        return selection;
    }

    public double convertCurrency(double amount, String from, String to) {
        double inrRate = 1, usdRate = 82, eurRate = 89, gbpRate = 104, jpyRate = 0.57;
        double audRate = 55, cadRate = 60, chfRate = 93, cnyRate = 11;
        double sgdRate = 61, nzdRate = 50, zarRate = 4.5;

        double amountInINR = 0;

        switch (from) {
            case "USD": amountInINR = amount * usdRate; break;
            case "EUR": amountInINR = amount * eurRate; break;
            case "GBP": amountInINR = amount * gbpRate; break;
            case "JPY": amountInINR = amount * jpyRate; break;
            case "AUD": amountInINR = amount * audRate; break;
            case "CAD": amountInINR = amount * cadRate; break;
            case "CHF": amountInINR = amount * chfRate; break;
            case "CNY": amountInINR = amount * cnyRate; break;
            case "SGD": amountInINR = amount * sgdRate; break;
            case "NZD": amountInINR = amount * nzdRate; break;
            case "ZAR": amountInINR = amount * zarRate; break;
            case "INR": amountInINR = amount; break;
        }

        switch (to) {
            case "USD": return amountInINR / usdRate;
            case "EUR": return amountInINR / eurRate;
            case "GBP": return amountInINR / gbpRate;
            case "JPY": return amountInINR / jpyRate;
            case "AUD": return amountInINR / audRate;
            case "CAD": return amountInINR / cadRate;
            case "CHF": return amountInINR / chfRate;
            case "CNY": return amountInINR / cnyRate;
            case "SGD": return amountInINR / sgdRate;
            case "NZD": return amountInINR / nzdRate;
            case "ZAR": return amountInINR / zarRate;
            case "INR": return amountInINR;
        }

        return 0;
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}


class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel() {
        
        backgroundImage = new ImageIcon("GettyImages-520187152-9dad65ce2ea04ac09701efa9618873b0.jpg").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
