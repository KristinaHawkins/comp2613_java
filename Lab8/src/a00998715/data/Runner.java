/**
 * Project: Lab8
 * File: Runner.java
 * Date: Mar 2, 2017
 * Time: 10:25:30 PM
 */
package a00998715.data;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class Runner extends Thread implements Comparable<Runner> {

	private int lane;
	private int bibNumber;
	private String country;
	private String firstName;
	private String lastName;
	private double reaction;
	private double result = 0;

	public Runner(int lane, int bibNumber, String country, String firstName, String lastName, double reaction) {
		this.lane = lane;
		this.bibNumber = bibNumber;
		this.country = country;
		this.firstName = firstName;
		this.lastName = lastName;
		this.reaction = reaction;
	}

	public int getLane() {
		return lane;
	}

	public int getBibNumber() {
		return bibNumber;
	}

	public String getCountry() {
		return country;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public double getReaction() {
		return reaction;
	}

	public double getResult() {
		return result;
	}

	@Override
	public void run() {
		Instant startTime = Instant.now();
		int i = 0;
		long wait = 90L + ThreadLocalRandom.current().nextInt(22);
		while (i++ < 100) {
			try {
				Thread.sleep((long) (wait + reaction));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		Instant endTime = Instant.now();
		result = Duration.between(startTime, endTime).toMillis() / 1000.0;
	}

	@Override
	public int compareTo(Runner o) {
		return this.result > o.result ? 1 : -1;
	}

}
