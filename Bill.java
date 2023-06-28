import java.util.List;

class Bill {
  private double total; // Total amount of the bill
  private double discount; // Discount amount applied to the bill
  private double netAmount; // Net payable amount after discount

  public Bill() {
    total = 0.0;
    discount = 0.0;
    netAmount = 0.0;
  }

  /**
   * Calculates the total amount, discount, and net payable amount based on the
   * items in the cart and the discount card.
   *
   * @param items         List of items in the cart
   * @param applyDiscount Boolean indicating whether to apply the discount
   * @param discountCard  Discount card type (e.g., "royalty")
   */
  public void calculateTotal(List<Item> items, boolean applyDiscount, String discountCard) {
    total = 0.0;

    // Calculate the total amount by summing up the prices of all items
    for (Item item : items) {
      total += item.getPrice();
    }

    // Apply discount if applicable
    discount = applyDiscount && discountCard.equals("royalty") ? total * 0.05 : 0.0;

    // Calculate the net payable amount after deducting the discount
    netAmount = total - discount;
  }

  /**
   * Returns the total amount of the bill.
   *
   * @return Total amount of the bill
   */
  public double getTotal() {
    return total;
  }

  /**
   * Returns the discount amount applied to the bill.
   *
   * @return Discount amount applied to the bill
   */
  public double getDiscount() {
    return discount;
  }

  /**
   * Returns the net payable amount after discount.
   *
   * @return Net payable amount after discount
   */
  public double getNetAmount() {
    return netAmount;
  }
}
