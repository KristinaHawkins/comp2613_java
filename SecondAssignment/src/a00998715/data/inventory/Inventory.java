/**
 * Project: FirstAssignment
 * File: Inventory.java
 * Date: Feb 9, 2017
 * Time: 3:59:01 PM
 */
package a00998715.data.inventory;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class Inventory {

	private String motorcycleId;
	private String description;
	private String partNumber;
	private String price;
	private String quantity;

	public String getMotorcycleId() {
		return motorcycleId;
	}

	public void setMotorcycleId(String motorcycleId) {
		this.motorcycleId = motorcycleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Inventory getInventory() {
		return this;
	}

	public static class Builder {

		private String motorcycleId;
		private String description;
		private String partNumber;
		private String price;
		private String quantity;

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

		public Builder price(String val) {
			price = val;
			return this;
		}

		public Builder quantity(String val) {
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
		return String.format("%s %s %s %s %s", motorcycleId, description, partNumber, price, quantity);
	}

}
