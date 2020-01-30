package adventofcode.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {

    private static final Path input = Path.of("src/main/resources/adventofcode/day1input");

    public static void main(String[] args) throws IOException {

        Stream<String> inputValues = Files.lines(input);
        List<String> listOfModuleMass = inputValues.collect(Collectors.toList());

        System.out.println(listOfModuleMass.get(1));

        int totalFuelRequired = 0;

        for (String mass : listOfModuleMass) {
            int fuelRequiredByModule = calculateFuel(Integer.parseInt(mass));
            int fuelRequiredByFuel = calculateFuel(fuelRequiredByModule);
            System.out.println(mass + " - " + fuelRequiredByModule + " - " + fuelRequiredByFuel);
            totalFuelRequired += fuelRequiredByModule;
        }

        System.out.println("Total fuel required: " + totalFuelRequired);

    }

    private static int calculateFuel(int mass) {
        // mass/3 -> round down -> -2 = fuel
        int fuel = (mass/3)-2;
        if (fuel <= 0) return 0;
        else return fuel + calculateFuel(fuel);
    }

}
