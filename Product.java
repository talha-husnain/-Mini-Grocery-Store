class Product {
  private String name; // Name of the product
  private double price; // Price of the product

  /**
   * Constructs a new Product object with the given name and price.
   *
   * @param name  Name of the product
   * @param price Price of the product
   */
  public Product(String name, double price) {
    this.name = name;
    this.price = price;
  }

  /**
   * Returns the name of the product.
   *
   * @return Name of the product
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the price of the product.
   *
   * @return Price of the product
   */
  public double getPrice() {
    return price;
  }
}
