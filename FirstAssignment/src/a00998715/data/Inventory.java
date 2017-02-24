/**
 * Project: FirstAssignment
 * File: Inventory.java
 * Date: Feb 9, 2017
 * Time: 3:59:01 PM
 */
package a00998715.data;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class Inventory {

	// <- change. Everything is wrong!
	// private static final String INVENTORY_HEADER = String.format(" %2s %-6s %-15s %-15s %-25s %-15s %-15s \n", "#.", "ID", "Make", "Model", "Year",
	// "Serial Number", "Mileage");
	// private static final String INVENTORY_FORMAT = " %2s %-6s %-15s %-15s %-25s %-15s %-15s \n";

	private String motorcycleId;
	private String description;
	private String partNumber;
	private double price;
	private int quantity;

	public String getMotorcycleId() {
		return motorcycleId;
	}

	@Deprecated
	public void setMotorcycleId(String motorcycleId) {
		this.motorcycleId = motorcycleId;
	}

	public String getDescription() {
		return description;
	}

	@Deprecated
	public void setDescription(String description) {
		this.description = description;
	}

	public String getPartNumber() {
		return partNumber;
	}

	@Deprecated
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public double getPrice() {
		return price;
	}

	@Deprecated
	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	@Deprecated
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static class Builder {

		private String motorcycleId;
		private String description;
		private String partNumber;
		private double price;
		private int quantity;

		public Builder(String motorcycleId) {
			this.motorcycleId = motorcycleId;
		}

		public Builder description(String val) {
			description = val;
			return this;
		}

		public Builder partNumber(String val) {
			partNumber = val;
			return this;
		}

		public Builder price(double d) {
			price = d;
			return this;
		}

		public Builder quantity(int val) {
			quantity = val;
			return this;
		}

		public Inventory build() {
			return new Inventory(this);
		}
	}

	private Inventory(Builder builder) {
		motorcycleId = builder.motorcycleId;
		description = builder.description;
		partNumber = builder.partNumber;
		price = builder.price;
		quantity = builder.quantity;
	}

	@Override
	public String toString() {
		return "Inventory [motorcycleId=" + motorcycleId + ", description=" + description + ", partNumber=" + partNumber + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}

}
