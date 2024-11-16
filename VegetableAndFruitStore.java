import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VegetableAndFruitStore {
    private JFrame frame;
    private JTextField sellItemField;
    private JTextField quantityField;
    private JTextArea stockArea;
    private JTextArea salesArea;
    private Map<String, Integer> stock;
    private ArrayList<String> sales;

    public VegetableAndFruitStore() {
        stock = new HashMap<>();
        sales = new ArrayList<>();

        // Create GUI components
        frame = new JFrame("Vegetable and Fruit Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create buttons
        JButton sellButton = new JButton("S - Sell Items");
        JButton printStockButton = new JButton("P - Print Stock");
        JButton reportSalesButton = new JButton("R - Report Sales");
        JButton exitButton = new JButton("E - Exit");

        // Add buttons to main panel
        mainPanel.add(sellButton);
        mainPanel.add(printStockButton);
        mainPanel.add(reportSalesButton);
        mainPanel.add(exitButton);

        // Create sell item panel
        JPanel sellItemPanel = new JPanel();
        sellItemPanel.setLayout(new FlowLayout());
        JLabel sellItemLabel = new JLabel("Enter item to sell:");
        sellItemField = new JTextField(10);
        JLabel quantityLabel = new JLabel("Enter quantity:");
        quantityField = new JTextField(5);
        sellItemPanel.add(sellItemLabel);
        sellItemPanel.add(sellItemField);
        sellItemPanel.add(quantityLabel);
        sellItemPanel.add(quantityField);

        // Create stock area
        stockArea = new JTextArea(10, 20);
        stockArea.setEditable(false);

        // Create sales area
        salesArea = new JTextArea(10, 20);
        salesArea.setEditable(false);

        // Add components to frame
        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(sellItemPanel, BorderLayout.CENTER);
        frame.add(new JScrollPane(stockArea), BorderLayout.EAST);
        frame.add(new JScrollPane(salesArea), BorderLayout.SOUTH);

        // Add action listeners to buttons
        sellButton.addActionListener(new SellItemListener());
        printStockButton.addActionListener(new PrintStockListener());
        reportSalesButton.addActionListener(new ReportSalesListener());
        exitButton.addActionListener(new ExitListener());

        // Initialize stock
        stock.put("Apples", 10);
        stock.put("Bananas", 20);
        stock.put("Carrots", 30);
        stock.put("Cabbage", 30);
        stock.put("Tomato", 40);
        stock.put("Potato", 60);
        stock.put("Onion", 20);
        stock.put("Cucumber", 5);
        stock.put("Lettuce", 10);
        stock.put("Zucchini", 15);
        // Set frame visible
        frame.pack();
        frame.setVisible(true);
    }

    private class SellItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String item = sellItemField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            if (stock.containsKey(item)) {
                int currentQuantity = stock.get(item);
                if (currentQuantity >= quantity) {
                    stock.put(item, currentQuantity - quantity);
                    sales.add(item + " x " + quantity);
                    salesArea.setText("");
                    for (String sale : sales) {
                        salesArea.append(sale + "\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Not enough stock!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Item not found!");
            }
        }
    }

    private class PrintStockListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            stockArea.setText("");
            for (Map.Entry<String, Integer> entry : stock.entrySet()) {
                stockArea.append(entry.getKey() + " - " + entry.getValue() + "\n");
            }
        }
    }

    private class ReportSalesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            salesArea.setText("");
            for (String sale : sales) {
                salesArea.append(sale + "\n");
            }
        }
    }

    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VegetableAndFruitStore();
            }
        });
    }
}