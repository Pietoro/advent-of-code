package adventofcode.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Day2 {

    private static final Path input = Path.of("src/main/resources/adventofcode/day2input");

    public static void main(String[] args) {

        List<Integer> intcodeProgram = new ArrayList<>(loadAnIntcode(input));

        // --- Part One ---
        int noun = 12;
        int verb = 2;
        List<Integer> copyOfIntcodeProgram = new ArrayList<>(intcodeProgram);

        System.out.printf("The value at position 0 of the 1202 program: %d%n",
                            runIntcodeProgram(copyOfIntcodeProgram, noun, verb));


        // --- Part Two ---
        int output = 19690720;
        findVerbAndNoun(output, intcodeProgram);

    }

    private static int runIntcodeProgram(List<Integer> intcode, int noun, int verb) {
        int position = 0;
        intcode.set(1, noun);
        intcode.set(2, verb);
        while (intcode.get(position) != 99) {
            int positionOne = intcode.get(position + 1);
            int positionTwo = intcode.get(position + 2);
            int positionThree = intcode.get(position + 3);

            switch (intcode.get(position)) {
                case 1:
                    intcode.set(positionThree, (intcode.get(positionOne)) + (intcode.get(positionTwo)));
                    break;
                case 2:
                    intcode.set(positionThree, (intcode.get(positionOne)) * (intcode.get(positionTwo)));
                    break;
                default:
                    System.out.printf("Something went wrong - number %d is not a proper optcode", position);
                    break;
            }
            position += 4; // stepping forward 4 positions
        }
        return intcode.get(0);
    }

    private static void findVerbAndNoun(int output, List<Integer> intcode) {
        List<Integer> copyOfIntcodeProgram = new ArrayList<>(intcode);

        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {

                if (runIntcodeProgram(copyOfIntcodeProgram, noun, verb) == output) {
                    System.out.printf("The pair of inputs that produces the output 19690720:%n " +
                            "Noun - %d%n Verb - %d%n%n" +
                            "And the final answer is - %d%n",
                            noun, verb, (100 * noun + verb));
                    return;
                } else {
                    Collections.copy(copyOfIntcodeProgram, intcode);
                }
            }
        }
    }

    private static List<Integer> loadAnIntcode(Path input) {
        String inputLoader = null;
        try {
            inputLoader = Files.readString(input);
        } catch (IOException e) {
            System.out.println("Couldn't load file");
            e.printStackTrace();
        }

        String[] inputToArray = Objects.requireNonNull(inputLoader).split(",");

        List<Integer> intcodeProgram = new ArrayList<>();

        for (String element : inputToArray) {
            int arrayElement = Integer.parseInt(element);
            intcodeProgram.add(arrayElement);
        }
        return intcodeProgram;
    }
}