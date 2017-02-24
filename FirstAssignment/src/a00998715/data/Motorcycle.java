/**
 * Project: FirstAssignment
 * File: Motorcycle.java
 * Date: Feb 9, 2017
 * Time: 3:58:51 PM
 */
package a00998715.data;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class Motorcycle {

	private int id;
	private String make;
	private String model;
	private int year;
	private String serialNumber;
	private double mileage;
	private int customerId;

	public int getId() {
		return id;
	}

	@Deprecated
	public void setId(int id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	@Deprecated
	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	@Deprecated
	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	@Deprecated
	public void setYear(int year) {
		this.year = year;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	@Deprecated
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public double getMileage() {
		return mileage;
	}

	@Deprecated
	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public int getCustomerId() {
		return customerId;
	}

	@Deprecated
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public static class Builder {
		private int id;
		private String make;
		private String model;
		private int year;
		private String serialNumber;
		private int mileage;
		private int customerId;

		public Builder(int id, String serialNumber) {
			this.id = id;
			this.serialNumber = serialNumber;
		}

		public Builder make(String val) {
			make = val;
			return this;
		}

		public Builder model(String val) {
			model = val;
			return this;
		}

		public Builder year(int val) {
			year = val;
			return this;
		}

		public Builder serialNumber(String val) {
			serialNumber = val;
			return this;
		}

		public Builder mileage(int val) {
			mileage = val;
			return this;
		}

		public Builder customerId(int val) {
			customerId = val;
			return this;
		}

		public Motorcycle build() {
			return new Motorcycle(this);
		}

	}

	private Motorcycle(Builder builder) {
		id = builder.id;
		serialNumber = builder.serialNumber;
		make = builder.make;
		model = builder.model;
		year = builder.year;
		mileage = builder.mileage;
		customerId = builder.customerId;
	}

	@Override
	public String toString() {
		return "Motorcycle [id=" + id + ", make=" + make + ", model=" + model + ", year=" + year + ", serialNumber=" + serialNumber + ", mileage="
				+ mileage + ", customerId=" + customerId + "]";
	}

}
