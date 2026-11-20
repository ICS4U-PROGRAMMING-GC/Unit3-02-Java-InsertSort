import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Reads lines of space-separated integers from an input file, sorts each line
 * using insertion sort, and writes the sorted lines to an output file.
 *
 * @author  Carel Gumuyire
 * @version 1.0
 * @since   2026-11-20
 */
public final class InsertionSort {

    /**
     * Private constructor to prevent instantiation.
     *
     * @throws IllegalStateException Utility class
     */
    private InsertionSort() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Sorts an array of integers in ascending order using insertion sort.
     *
     * @param array The array of integers to sort.
     * @return The sorted array of integers.
     */
    public static int[] insertionSort(final int[] array) {
        for (int index = 1; index < array.length; index++) {
            final int current = array[index];
            int position = index - 1;

            while (position >= 0 && array[position] > current) {
                array[position + 1] = array[position];
                position--;
            }
            array[position + 1] = current;
        }
        return array;
    }

    /**
     * Reads space-separated lines from input.txt, sorts each line,
     * and writes the results to output.txt.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(final String[] args) {
        final File inputFile = new File("input.txt");

        try (Scanner scanner = new Scanner(inputFile);
             FileWriter writer = new FileWriter("output.txt")) {

            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                final String[] parts = line.split("\\s+");
                final int[] numbers = new int[parts.length];

                for (int index = 0; index < parts.length; index++) {
                    numbers[index] = Integer.parseInt(parts[index]);
                }

                final int[] sortedNumbers = insertionSort(numbers);

                for (int index = 0; index < sortedNumbers.length; index++) {
                    writer.write(String.valueOf(sortedNumbers[index]));
                    if (index < sortedNumbers.length - 1) {
                        writer.write(" ");
                    }
                }
                writer.write(System.lineSeparator());
            }
            System.out.println("Sorting complete. Check output.txt.");

        } catch (IOException | NumberFormatException error) {
            System.out.println("Error processing file: " + error.getMessage());
        }
    }
}
