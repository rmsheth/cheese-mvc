package org.launchcode;

import org.launchcode.models.Cheese;
import org.launchcode.models.CheeseData;
import org.launchcode.models.CheeseType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * If we add `implements CommandLineRunner` and allow IntelliJ to implement the method,
 * we can run a bit of code when our application starts. In this case, we create a cheese
 * and add to our CheeseData, that way we don't have to keep adding in cheese each time we restart
 */
@SpringBootApplication
public class CheeseMvcApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CheeseMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cheese cheese = new Cheese("Cheddar", "It's Bettah");
		cheese.setType(CheeseType.HARD);
		cheese.setRating(3);
		CheeseData.add(cheese);
	}
}
