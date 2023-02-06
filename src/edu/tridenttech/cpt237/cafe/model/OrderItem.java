package edu.tridenttech.cpt237.cafe.model;

public class OrderItem {
	private MenuItem item;
	private int numSold;
	private double priceEach;

	public OrderItem(MenuItem item, int numSold, double price) {
		this.item = item;
		this.numSold = numSold;
		this.priceEach = price;
	}

	public String getItemName() {
		return item.getName();
	}

	public int getNumSold() {
		return numSold;
	}

	public double getPriceEach() {
		return priceEach;
	}

	public double getCost() {
		return priceEach * numSold;
	}

	@Override
	public String toString() {
		return String.format("LineItem [item=%s, numSold=%d, priceEach=%.2f, total=%.2f]",
				              getItemName(), numSold, priceEach, numSold * priceEach);
	}

}
