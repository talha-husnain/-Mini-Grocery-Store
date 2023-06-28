import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrocerySalesApp extends JFrame {
  // GUI components
  private JComboBox<String> categoryBox; // ComboBox to select product category
  private JComboBox<String> productBox; // ComboBox to select a product
  private JButton addToCartButton; // Button to add the selected product to the cart
  private JCheckBox discountCheckBox; // Checkbox to indicate if a discount should be applied
  private JTextField discountCardField; // TextField to enter the discount card number
  private JTextArea cartArea; // TextArea to display the items in the cart
  private JButton checkoutButton; // Button to initiate the checkout process
  private JPanel checkoutPanel; // Panel to display the checkout details
  private JLabel totalLabel; // Label to display the total amount
  private JLabel discountLabel; // Label to display the discount amount
  private JLabel netLabel; // Label to display the net payable amount
  private JButton confirmButton; // Button to confirm the purchase
  private JPanel cardPanel; // Panel to hold different card layouts
  private CardLayout cardLayout; // CardLayout to switch between different card panels

  // Data structures
  private Map<String, List<Product>> productsByCategory; // Map to store products categorized by their category
  private Bill bill; // Bill object to calculate total, discount, and net amount
  private List<Item> cartItems; // List to store items in the cart

  public GrocerySalesApp() {
    productsByCategory = new HashMap<>();
    bill = new Bill();
    cartItems = new ArrayList<>();

    // Add sample products
  addProduct("Meat", "Beef", 10.99);
addProduct("Meat", "Mutton", 8.99);
addProduct("Meat", "Chicken", 6.99);
addProduct("Meat", "Hen", 10.99);
addProduct("Meat", "Peacock", 8.99);
addProduct("Meat", "Check", 6.99);
addProduct("Meat", "Lamb", 9.99);
addProduct("Meat", "Pork", 7.99);
addProduct("Meat", "Turkey", 11.99);
addProduct("Meat", "Duck", 9.99);

addProduct("Vegetables", "Potato", 1.99);
addProduct("Vegetables", "Tomato", 2.99);
addProduct("Vegetables", "Carrot", 2.49);
addProduct("Vegetables", "Onion", 1.99);
addProduct("Vegetables", "Cucumber", 2.99);
addProduct("Vegetables", "Lettuce", 2.49);
addProduct("Vegetables", "Broccoli", 1.99);
addProduct("Vegetables", "Bell Pepper", 1.49);
addProduct("Vegetables", "Spinach", 2.99);
addProduct("Vegetables", "Zucchini", 2.49);

addProduct("Fruits", "Apple", 1.99);
addProduct("Fruits", "Mango", 2.99);
addProduct("Fruits", "Bananas", 2.49);
addProduct("Fruits", "Grapes", 1.99);
addProduct("Fruits", "Orange", 2.99);
addProduct("Fruits", "Pineapple", 2.49);
addProduct("Fruits", "Strawberries", 3.99);
addProduct("Fruits", "Watermelon", 4.99);
addProduct("Fruits", "Blueberries", 3.49);
addProduct("Fruits", "Kiwi", 2.49);

addProduct("Drinks", "Water", 1.99);
addProduct("Drinks", "Coca Cola", 2.99);
addProduct("Drinks", "Coffee", 2.99);
addProduct("Drinks", "Milk", 2.99);
addProduct("Drinks", "Tea", 2.99);
addProduct("Drinks", "Sting", 2.49);
addProduct("Drinks", "Orange Juice", 2.99);
addProduct("Drinks", "Lemonade", 2.49);
addProduct("Drinks", "Iced Tea", 2.49);
addProduct("Drinks", "Smoothie", 3.99);

addProduct("Sweets", "Chocolates", 1.99);
addProduct("Sweets", "Candy", 2.99);
addProduct("Sweets", "Toffee", 2.49);
addProduct("Sweets", "Ice Cream", 3.99);
addProduct("Sweets", "Cookies", 2.49);
addProduct("Sweets", "Cake", 4.99);
addProduct("Sweets", "Donuts", 2.49);
addProduct("Sweets", "Gummy Bears", 1.99);
addProduct("Sweets", "Caramel", 2.49);
addProduct("Sweets", "Marshmallows", 1.99);

addProduct("FastFood", "Burger", 5.99);
addProduct("FastFood", "Pizza", 8.99);
addProduct("FastFood", "French Fries", 3.49);
addProduct("FastFood", "Hot Dog", 4.99);
addProduct("FastFood", "Nachos", 6.99);
addProduct("FastFood", "Chicken Wings", 7.99);
addProduct("FastFood", "Tacos", 4.99);
addProduct("FastFood", "Sushi", 9.99);
addProduct("FastFood", "Burrito", 6.99);
addProduct("FastFood", "Quesadilla", 5.99);

    

    setTitle("Grocery Sales App");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    categoryBox = new JComboBox<>(productsByCategory.keySet().toArray(new String[0]));
    categoryBox.addActionListener(new CategoryListener());

    productBox = new JComboBox<>();
    productBox.addItem("Select a product");

    addToCartButton = new JButton("Add to Cart");
    addToCartButton.addActionListener(new AddToCartListener());

    discountCheckBox = new JCheckBox("5% discount");

    discountCardField = new JTextField(20);

    cartArea = new JTextArea(10, 30);
    JScrollPane scrollPane = new JScrollPane(cartArea);

    checkoutButton = new JButton("Checkout");
    checkoutButton.addActionListener(new CheckoutListener());

    checkoutPanel = new JPanel();
    totalLabel = new JLabel();
    discountLabel = new JLabel();
    netLabel = new JLabel();
    confirmButton = new JButton("Confirm Purchase");
    confirmButton.addActionListener(new ConfirmListener());

    checkoutPanel.setLayout(new GridLayout(5, 1));
    checkoutPanel.add(totalLabel);
    checkoutPanel.add(discountLabel);
    checkoutPanel.add(netLabel);
    checkoutPanel.add(confirmButton);

    cardPanel = new JPanel();
    cardLayout = new CardLayout();
    cardPanel.setLayout(cardLayout);

    JPanel addItemsPanel = new JPanel();
    addItemsPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 10, 10, 10);

    gbc.gridx = 0;
    gbc.gridy = 0;
    addItemsPanel.add(new JLabel("Category:"), gbc);

    gbc.gridx++;
    addItemsPanel.add(categoryBox, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    addItemsPanel.add(new JLabel("Product:"), gbc);

    gbc.gridx++;
    addItemsPanel.add(productBox, gbc);

    gbc.gridx++;
    gbc.gridy = 1;
    gbc.gridheight = 2;
    gbc.fill = GridBagConstraints.VERTICAL;
    addItemsPanel.add(addToCartButton, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.NONE;
    addItemsPanel.add(new JLabel("Discount:"), gbc);

    gbc.gridx++;
    addItemsPanel.add(discountCheckBox, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    addItemsPanel.add(new JLabel("Discount Card:"), gbc);

    gbc.gridx++;
    addItemsPanel.add(discountCardField, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    addItemsPanel.add(scrollPane, gbc);

    gbc.gridx = 1;
    gbc.gridy++;
    gbc.gridwidth = 1;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    gbc.weighty = 0.0;
    addItemsPanel.add(checkoutButton, gbc);

    cardPanel.add(addItemsPanel, "Add Items");
    cardPanel.add(checkoutPanel, "Checkout");

    add(cardPanel, BorderLayout.CENTER);

    pack();
    setVisible(true);
  }

  private void addProduct(String category, String name, double price) {
    List<Product> products = productsByCategory.getOrDefault(category, new ArrayList<>());
    products.add(new Product(name, price));
    productsByCategory.put(category, products);
  }

  private class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String selectedCategory = (String) categoryBox.getSelectedItem();
      List<Product> products = productsByCategory.getOrDefault(selectedCategory, new ArrayList<>());

      productBox.removeAllItems();
      productBox.addItem("Select a product");

      for (Product product : products) {
        productBox.addItem(product.getName());
      }
    }
  }

  private class AddToCartListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String selectedCategory = (String) categoryBox.getSelectedItem();
      String selectedProduct = (String) productBox.getSelectedItem();
      double price = getProductPrice(selectedCategory, selectedProduct);

      if (price != 0.0) {
        cartItems.add(new Item(selectedProduct, price));
        cartArea.append(selectedProduct + " ($" + price + ")\n");

        productBox.setSelectedIndex(0);
      }
    }
  }

  private class CheckoutListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      bill.calculateTotal(cartItems, discountCheckBox.isSelected(), discountCardField.getText());

      DecimalFormat df = new DecimalFormat("0.00");
      totalLabel.setText("Total: $" + df.format(bill.getTotal()));
      discountLabel.setText("Discount: $" + df.format(bill.getDiscount()));
      netLabel.setText("Net Payable: $" + df.format(bill.getNetAmount()));

      cardLayout.show(cardPanel, "Checkout");
    }
  }

  private class ConfirmListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      JOptionPane.showMessageDialog(GrocerySalesApp.this, "Purchase confirmed. Thank you!");
      reset();
    }
  }

  private double getProductPrice(String category, String productName) {
    List<Product> products = productsByCategory.get(category);
    if (products != null) {
      for (Product product : products) {
        if (product.getName().equals(productName)) {
          return product.getPrice();
        }
      }
    }
    return 0.0;
  }

  private void reset() {
    cartItems.clear();
    categoryBox.setSelectedIndex(0);
    productBox.removeAllItems();
    productBox.addItem("Select a product");
    discountCheckBox.setSelected(false);
    discountCardField.setText("");
    cartArea.setText("");
    totalLabel.setText("");
    discountLabel.setText("");
    netLabel.setText("");
    cardLayout.show(cardPanel, "Add Items");
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new GrocerySalesApp());
  }
}
