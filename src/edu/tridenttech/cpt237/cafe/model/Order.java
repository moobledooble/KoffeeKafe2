package edu.tridenttech.cpt237.cafe.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
	private static int nextOrderId = 1001;
	private ArrayList<OrderItem> orderedItems = new ArrayList<>();
	private final int orderId;

	Order() {
		orderId = nextOrderId++;
	}
	
	void addItem(MenuItem item, int numPurchased) {
		orderedItems.add(new OrderItem(item, numPurchased, item.getBaseCost()));
	}
	
	public double getTotalCost() {
		double cost = 0;
		cost += getCostByList(orderedItems);
		return cost;
	}
	
	private double getCostByList(ArrayList<OrderItem> list) {
		double cost = 0;
		for (OrderItem item : list) {
			cost += item.getCost();
		}
		return cost;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public List<OrderItem> getOrderedItems() {
		return Collections.unmodifiableList(orderedItems);
	}
}
