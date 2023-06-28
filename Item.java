class Item {
  private String name; // Name of the item
  private double price; // Price of the item

  /**
   * Constructs a new Item object with the given name and price.
   *
   * @param name  Name of the item
   * @param price Price of the item
   */
  public Item(String name, double price) {
    this.name = name;
    this.price = price;
  }

  /**
   * Returns the name of the item.
   *
   * @return Name of the item
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the price of the item.
   *
   * @return Price of the item
   */
  public double getPrice() {
    return price;
  }
}
