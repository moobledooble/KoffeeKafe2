package edu.tridenttech.cpt237.cafe.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Cafe {

	private ArrayList<MenuItem> menuItems = new ArrayList<>();
	private String[] itemTypes = {"Bakery", "Beverage", "Sandwich"};
	private ArrayList<Order> placedOrderList = new ArrayList<>();
	private ArrayList<Order> pendingOrderList = new ArrayList<>();

	/**
	 * Initializes the Cafe object.  Loads the cafe from the provided
	 * configuration file.
	 * @param configPath Path to the configuration file
	 * @throws FileNotFoundException
	 */
	public Cafe(String configPath) throws FileNotFoundException {
		loadItems(configPath);
	}

	private void loadItems(String configPath) throws FileNotFoundException {
		Scanner input = new Scanner(new File(configPath));
		while (input.hasNext()) {
			String line = input.nextLine();
			String[] fields = line.split(",");
			char typeChar = fields[0].toUpperCase().charAt(0);
			String name = fields[1];
			String type;
			MenuItem item;
			double price = Double.parseDouble(fields[2]);
			switch (typeChar) {
				case 'D': {
					type = "Beverage";
				} break;
				case 'S': {
					type = "Sandwich";
				} break;
				case 'B': {
					type = "Bakery";
				} break;
				default: {
					System.err.printf("Unknown type: %c%n", typeChar);
					continue;
				}
			}
			item =  new MenuItem(type, name, price);
			menuItems.add(item);
		}
		input.close();
	}
	
	public List<Order> getOrderList() {
		return Collections.unmodifiableList(placedOrderList);
	}

	/**
	 * Adds a line to the specified order.
	 * @param orderId The id of the order to which this item is to be added.
	 * @param itemName The name of the item, from the SalesItem
	 * @param numOrdered The number of this type items ordered.
	 */
	public void addLineItem(int orderId, String itemName, int numOrdered) {
		Order order = findPendingOrder(orderId);
		MenuItem item = findItemByName(itemName);
		if (order != null && item != null) {
			order.addItem(item, numOrdered);
		}
	}
	
	private MenuItem findItemByName(String itemName) {
		for (MenuItem item : menuItems) {
			if (itemName.equalsIgnoreCase(item.getName())) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Get the categories of items offered by the cafe.
	 * @return The categories as an array of strings.
	 */
	public List<String> getMenuItemTypes()  {
		return new ArrayList<String>(Arrays.asList(itemTypes));
	}
	
	/**
	 * Get all of the items on the menu.
	 * @return A List containing all of the items.
	 */
	public List<MenuItem> getAllMenuItems() {
		return Collections.unmodifiableList(menuItems);
	}
	
	/**
	 * Get the items on the menu limited to a particular category.
	 * @param type The category, e.g. Bakery.
	 * @return A list of items matching the category.
	 */
	public List<MenuItem> getMenuItemsByType(String type) {
		List<MenuItem> items = new ArrayList<>();
		for (MenuItem item : menuItems) {
			if (type.equalsIgnoreCase(item.getType())) {
				items.add(item);
			}
		}
		return items;
	}

	public MenuItem getMenuItemByName(String itemName) {
		return findItemByName(itemName);
	}

	/**
	 * Find the order corresponding to the provided id and return this Order
	 * to the caller.
	 * @param id The id of the order being requested.
	 * @return The Order matching the id, or null if the order does not exist.
	 */
	public Order getOrderById(int id) {
		return findOrderById(placedOrderList, id);
	}
	
	/**
	 * Creates a new Order to hold items added by the user.  The returned order
	 * id is to be used when adding to the order, placing the order or
	 * canceling the order.
	 * @return The order id of the newly created order.
	 */
	public int startOrder() {
		Order order = new Order();
		pendingOrderList.add(order);
		return order.getOrderId();
	}
	
	/**
	 * Confirm that the order is being placed.
	 * @param id The id of the order being placed.
	 */
	public void placeOrder(int id) {
		Order order = findPendingOrder(id);
		if (order != null) {
			placedOrderList.add(order);
		}
	}

	/**
	 * Cancel the order.
	 * @param id The id of the order being canceled.
	 */
	public void cancelOrder(int id) {
		Order order = findPendingOrder(id);
		if (order != null) {
			pendingOrderList.remove(order);
		}
	}
	
	private Order findOrderById(ArrayList<Order> list, int id) {
		return list.stream()
				   .filter(e -> e.getOrderId() == id)
				   .findAny()
				   .orElse(null);
	}

	public Order findPendingOrder(int id) {
		return findOrderById(pendingOrderList, id);
	}
	
	public Order findPlacedOrder(int id) {
		return findOrderById(placedOrderList, id);
	}
	public void displayOrders() {
		for (Order order : placedOrderList) {
			System.out.printf("Order # %d%n", order.getOrderId());
			for (OrderItem item : order.getOrderedItems()) {
				System.out.println(item);
			}
		}
	}
}
