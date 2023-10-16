import java.io.*;
import java.util.*;

public class BubbleSort {

    public static int[] createRandomArray(int arrayLength) {
        int[] array = new int[arrayLength];
        Random random = new Random();
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(101);
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            for (int value : array) {
                writer.println(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] readFileToArray(String filename) {
        List<Integer> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (array[i - 1] > array[i]) {
                    int temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bubble Sort Generator");
        System.out.println("1. Generate a random array and store it in a file");
        System.out.println("2. Read and sort through the existing file, then store the sorted array in another file");
        System.out.print("Enter your choice, 1 or 2: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Enter the array length: ");
            int arrayLength = scanner.nextInt();
            int[] randomArray = createRandomArray(arrayLength);
            writeArrayToFile(randomArray, "random_array.txt");
            System.out.println("Random array has been generated and saved in 'random_array.txt'.");
        } else if (choice == 2) {
            System.out.print("Enter the input filename: ");
            String inputFilename = scanner.next();
            int[] unsortedArray = readFileToArray(inputFilename);
            bubbleSort(unsortedArray);
            System.out.print("Enter the output filename for the sorted array: ");
            String outputFilename = scanner.next();
            writeArrayToFile(unsortedArray, outputFilename);
            System.out.println("Array has been sorted and saved in '" + outputFilename + "'.");
        } else {
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }

        scanner.close();
    }
}