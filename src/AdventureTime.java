import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class AdventureTime {

    /**
     * This is the main method and it is where you will test your implementations for challengeOne, challengeTwo, etc.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int challengeOneAnswer = challengeOne("inputOneTwo.txt");
        int challengeTwoAnswer = challengeTwo("inputOneTwo.txt");
        int challengeThreeAnswer = challengeThree("inputThreeFour.txt");
        int challengeFourAnswer = challengeFour("inputThreeFour.txt");
        writeFileAllAnswers("AdventureTime.txt", challengeOneAnswer, challengeTwoAnswer, challengeThreeAnswer, challengeFourAnswer);
    }

    /**
     * TODO 1
     * <p>
     * Challenge 1
     *
     * @param fileName
     * @return Answer to Challenge 1
     * @throws IOException
     */
    public static int challengeOne(String fileName) throws IOException {
        int[] depthMeasures = readFile(fileName);
        int count = 0;
        for (int i = 1; i < depthMeasures.length; i++) {
            if (depthMeasures[i] > depthMeasures[i - 1]) {
                count++;
            }
        }
        return count;
    }

    /**
     * TODO 2
     * <p>
     * Challenge 2
     *
     * @param fileName
     * @return Answer to Challenge 2
     * @throws FileNotFoundException
     */
    public static int challengeTwo(String fileName) throws FileNotFoundException {
        int[] depthMeasures = readFile(fileName);
        int[] sums = new int[depthMeasures.length - 2];
        int index = 0;
        int count = 0;
        for (int i = 1; i < depthMeasures.length - 1; i++) {
            sums[index] = depthMeasures[i - 1] + depthMeasures[i] + depthMeasures[i + 1];
            index++;
        }
        for (int i = 1; i < sums.length; i++) {
            if (sums[i] > sums[i - 1]) {
                count++;
            }
        }
        //System.out.println(Arrays.toString(sums));
        return count;
    }

    /**
     * TODO 3
     * <p>
     * Challenge 3
     *
     * @param fileName
     * @return Answer to Challenge 3
     * @throws FileNotFoundException
     */
    public static int challengeThree(String fileName) throws IOException {
        String[] commands = new String(Files.readAllBytes(new File(fileName).toPath())).split("\n");
        int depth = 0;
        int horizontal = 0;
        for (int i = 0; i < commands.length; i++) {
            int distance = Integer.parseInt(commands[i].split(" ")[1].trim()); // I still don't understand why there is trailing whitespace here - but .trim() solves the problem
            switch (commands[i].split(" ")[0]) {
                case "forward":
                    horizontal += distance;
                    break;
                case "down":
                    depth += distance;
                    break;
                case "up":
                    depth -= distance;
                    break;
            }
        }
        return depth * horizontal;
    }

    /**
     * TODO 4
     * <p>
     * Challenge 4
     *
     * @param filename
     * @return Answer to Challenge 4
     * @throws FileNotFoundException
     */
    public static int challengeFour(String filename) throws FileNotFoundException, IOException {
        String[] commands = new String(Files.readAllBytes(new File(filename).toPath())).split("\n");
        int depth = 0;
        int horizontal = 0;
        int aim = 0;
        for (int i = 0; i < commands.length; i++) {
            int numX = Integer.parseInt(commands[i].split(" ")[1].trim());
            switch (commands[i].split(" ")[0]) {
                case "forward":
                    horizontal += numX;
                    depth += numX * aim;
                    break;
                case "down":
                    aim += numX;
                    break;
                case "up":
                    aim -= numX;
                    break;
            }
        }
        return depth * horizontal;
    }

    /**
     * This method will write the values passed as challengeOne, challengeTwo, challengeThree, and challengeFour to a text file.
     * Do not edit this method.
     */
    private static void writeFileAllAnswers(String outPutFilename, int challengeOne, int challengeTwo, int challengeThree, int challengeFour) throws IOException {
        File file = new File(outPutFilename);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("Challenge 1: " + "\t" + challengeOne + "\n");
        bufferedWriter.write("Challenge 2: " + "\t" + challengeTwo + "\n");
        bufferedWriter.write("Challenge 3: " + "\t" + challengeThree + "\n");
        bufferedWriter.write("Challenge 4: " + "\t" + challengeFour + "\n");
        bufferedWriter.close();
    }

    /**
     * This method will read the values in inputFilename into an array of integers, which it will return.
     * Do not edit this method.
     */
    private static int[] readFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int numberOfLinesInFile = countLinesInFile(inputFilename);
        int[] data = new int[numberOfLinesInFile];
        int index = 0;
        while (scanner.hasNextLine()) {
            data[index++] = scanner.nextInt();
        }
        scanner.close();
        return data;
    }

    /**
     * This method will count the number of lines in a text file, which it will return.
     * Do not edit this method.
     */
    private static int countLinesInFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        return lineCount;
    }

}