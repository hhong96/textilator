package edu.gatech.seclass.textilator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Timeout(value = 1, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class MyMainTest {
    // Place all  of your tests in this class, optionally using MainTest.java as an example
    private final String usageStr =
    "Usage: textilator [ -s number | -x substring | -c case | -e num | -a | -p prefix ] FILE"
        + System.lineSeparator();

    @TempDir
    Path tempDirectory;

    @RegisterExtension
    OutputCapture capture = new OutputCapture();

    /*
    * Test Utilities
    */

    private Path createFile(String contents) {
        return createFile(contents, "sample.txt");
    }

    private Path createFile(String contents, String fileName) {
        Path file = tempDirectory.resolve(fileName);

        try {
            Files.write(file, contents.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return file;
    }

    private String getFileContent(Path file) {
        try {
            return Files.readString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Frame #: 1
    @Test
    public void textilatorTest1() {
        String input = "";

        Path inputFile = createFile(input);
        String[] args = {inputFile.toString()};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }

    // Frame #: 2
    @Test
    public void textilatorTest2() {
        String input = "something";

        Path inputFile = createFile(input);
        String[] args = {"notexist.txt"};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }


    // Frame #: 3
    @Test
    public void textilatorTest3() {
        String input = "This is a normal text file." + System.lineSeparator()
                + "Perhaps too normal..." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-s", "-x", inputFile.toString()};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }

    // Frame #: 4
    @Test
    public void textilatorTest4() {
        String input = "This is a normal text file." + System.lineSeparator()
                + "Perhaps too normal..." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-e", "-a", inputFile.toString()};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }

    // Frame #: 5
    @Test
    public void textilatorTest5() {
        String input = "This is a normal text file." + System.lineSeparator()
                + "Perhaps too normal..." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-s", "4", inputFile.toString()};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }

    // Frame #: 6
    @Test
    public void textilatorTest6() {
        String input = "This is a normal text file." + System.lineSeparator()
                + "Perhaps too normal..." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", inputFile.toString()};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }

    // Frame #: 7
    @Test
    public void textilatorTest7() {
        String input = "This is a normal text file." + System.lineSeparator()
                + "Perhaps too normal..." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-c", "something", inputFile.toString()};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }

    // Frame #: 8
    @Test
    public void textilatorTest8() {
        String input = "This is a normal text file." + System.lineSeparator()
                + "Perhaps too normal..." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-e", "35", inputFile.toString()};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }

    // Frame #: 9
    @Test
    public void textilatorTest9() {
        String input = "This is a normal text file." + System.lineSeparator()
                + "Perhaps too normal..." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-a", "something", inputFile.toString()};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }

    // Frame #: 10
    @Test
    public void textilatorTest10() {
        String input = "This is a normal text file." + System.lineSeparator()
                + "Perhaps too normal..." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-p", inputFile.toString()};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }

    // Frame #: 11
    @Test
    public void textilatorTest11() {
        String input = "This is a normal text file." + System.lineSeparator()
                + "Perhaps too normal..." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"", inputFile.toString()};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }

    // Frame #: 12
    @Test
    public void textilatorTest12() {
        String input = "This is a list of items that start with the \"c\":" + System.lineSeparator()
                + "Krabby patty" + System.lineSeparator()
                + "Chocolate chip cookie" + System.lineSeparator()
                + "Pineapple" + System.lineSeparator()
                + "Computer" + System.lineSeparator();
        String expected = "-This is a list of items that start with the \"c\":" + System.lineSeparator()
                + "-Krabby patty" + System.lineSeparator()
                + "-Chocolate chip cookie" + System.lineSeparator()
                + "-Pineapple" + System.lineSeparator()
                + "-Computer" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 13
    @Test
    public void textilatorTest13() {
        String input = "This is fine." + System.lineSeparator();
        String expected = "84 104 105 115 32 105 115 32 102 105 110 101 46 " + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-a", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 14
    @Test
    public void textilatorTest14() {
        String input = "This is fine." + System.lineSeparator();
        String expected = "-84 104 105 115 32 105 115 32 102 105 110 101 46 " + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-a", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 15
    @Test
    public void textilatorTest15() {
        String input = "Zoo" + System.lineSeparator();
        String expected = "App" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-e", "1", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 16
    @Test
    public void textilatorTest16() {
        String input = "Zoo" + System.lineSeparator();
        String expected = "-App" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-e", "1", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 17
    @Test
    public void textilatorTest17() {
        String input = "Zoo" + System.lineSeparator();
        String expected = "ZOO" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-c", "upper", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 18
    @Test
    public void textilatorTest18() {
        String input = "Zoo" + System.lineSeparator();
        String expected = "zoo" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-c", "lower", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 19
    @Test
    public void textilatorTest19() {
        String input = "Zoo" + System.lineSeparator();
        String expected = "-ZOO" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-c", "upper", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 20
    @Test
    public void textilatorTest20() {
        String input = "Zoo" + System.lineSeparator();
        String expected = "-zoo" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-c", "lower", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 21
    @Test
    public void textilatorTest21() {
        String input = "테스트 test" + System.lineSeparator();
        String expected = "테스트32 84 69 83 84 " + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-c", "upper", "-a", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 22
    @Test
    public void textilatorTest22() {
        String input = "테스트 test" + System.lineSeparator();
        String expected = "테스트32 116 101 115 116 " + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-c", "lower", "-a", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 23
    @Test
    public void textilatorTest23() {
        String input = "테스트 test" + System.lineSeparator();
        String expected = "-테스트32 84 69 83 84 " + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-c", "upper", "-a", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 24
    @Test
    public void textilatorTest24() {
        String input = "테스트 test" + System.lineSeparator();
        String expected = "-테스트32 116 101 115 116 " + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-c", "lower", "-a", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 25
    @Test
    public void textilatorTest25() {
        String input = "Zoo" + System.lineSeparator();
        String expected = "APP" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-c", "upper", "-e", "1", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 26
    @Test
    public void textilatorTest26() {
        String input = "Zoo" + System.lineSeparator();
        String expected = "app" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-c", "lower", "-e", "1", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 27
    @Test
    public void textilatorTest27() {
        String input = "Zoo" + System.lineSeparator();
        String expected = "-APP" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-c", "upper", "-e", "1", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 28
    @Test
    public void textilatorTest28() {
        String input = "Zoo" + System.lineSeparator();
        String expected = "-app" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-c", "lower", "-e", "1", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 29
    @Test
    public void textilatorTest29() {
        String input = "This list is really important and should NOT get deleted." + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "This list is really important and should NOT get deleted." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 30
    @Test
    public void textilatorTest30() {
        String input = "This list is really important and should NOT get deleted." + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "-This list is really important and should NOT get deleted." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 31
    @Test
    public void textilatorTest31() {
        String input = "test" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "116 101 115 116 " + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-a", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 32
    @Test
    public void textilatorTest32() {
        String input = "test" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "-116 101 115 116 " + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-a", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 33
    @Test
    public void textilatorTest33() {
        String input = "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "App" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-e", "1", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 34
    @Test
    public void textilatorTest34() {
        String input = "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "-App" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-e", "1", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 35
    @Test
    public void textilatorTest35() {
        String input = "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "ZOO" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-c", "upper", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 36
    @Test
    public void textilatorTest36() {
        String input = "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "zoo" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-c", "lower", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 37
    @Test
    public void textilatorTest37() {
        String input = "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "-ZOO" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-c", "upper", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 38
    @Test
    public void textilatorTest38() {
        String input = "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "-zoo" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-c", "lower", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 39
    @Test
    public void textilatorTest39() {
        String input = "test" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "84 69 83 84 " + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-c", "upper", "-a", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 40
    @Test
    public void textilatorTest40() {
        String input = "test" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "116 101 115 116 " + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-c", "lower", "-a", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 41
    @Test
    public void textilatorTest41() {
        String input = "test" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "-84 69 83 84 " + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-c", "upper", "-a", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 42
    @Test
    public void textilatorTest42() {
        String input = "test" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "-116 101 115 116 " + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-c", "lower", "-a", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 43
    @Test
    public void textilatorTest43() {
        String input = "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "APP" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-c", "upper", "-e", "1", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 44
    @Test
    public void textilatorTest44() {
        String input = "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "app" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-c", "lower", "-e", "1", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 45
    @Test
    public void textilatorTest45() {
        String input = "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "-APP" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-c", "upper", "-e", "1", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 46
    @Test
    public void textilatorTest46() {
        String input = "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "-app" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-c", "lower", "-e", "1", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 47
    @Test
    public void textilatorTest47() {
        String input = "This is a list of items that start with the \"c\":" + System.lineSeparator()
                + "Chocolate chip cookie" + System.lineSeparator()
                + "Computer" + System.lineSeparator();
        String expected = "Chocolate chip cookie" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "1", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 48
    @Test
    public void textilatorTest48() {
        String input = "This is a list of items that start with the \"c\":" + System.lineSeparator()
                + "Chocolate chip cookie" + System.lineSeparator()
                + "Computer" + System.lineSeparator();
        String expected = "This is a list of items that start with the \"c\":" + System.lineSeparator()
                + "Computer" + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "0", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 49
    @Test
    public void textilatorTest49() {
        String input = "This is a list of items that start with the \"c\":" + System.lineSeparator()
                + "Chocolate chip cookie" + System.lineSeparator()
                + "Computer" + System.lineSeparator();
        String expected = "-Chocolate chip cookie" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "1", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 50
    @Test
    public void textilatorTest50() {
        String input = "This is a list of items that start with the \"c\":" + System.lineSeparator()
                + "Chocolate chip cookie" + System.lineSeparator()
                + "Computer" + System.lineSeparator();
        String expected = "-This is a list of items that start with the \"c\":" + System.lineSeparator()
                + "-Computer" + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "0", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 51
    @Test
    public void textilatorTest51() {
        String input = "test" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator();
        String expected = "116 101 115 116 " + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "0", "-a", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 52
    @Test
    public void textilatorTest52() {
        String input =  "* watermelon" + System.lineSeparator()
                + "test" + System.lineSeparator();
        String expected = "116 101 115 116 " + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "1", "-a", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 53
    @Test
    public void textilatorTest53() {
        String input =  "* watermelon" + System.lineSeparator()
                + "test" + System.lineSeparator();
        String expected = "-116 101 115 116 " + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "1", "-a", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 54
    @Test
    public void textilatorTest54() {
        String input = "test" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator();
        String expected = "-116 101 115 116 " + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "0", "-a", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 55
    @Test
    public void textilatorTest55() {
        String input =  "* watermelon" + System.lineSeparator()
                + "Zoo" + System.lineSeparator();
        String expected = "App" + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "1", "-e", "1", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 56
    @Test
    public void textilatorTest56() {
        String input =  "Zoo" + System.lineSeparator()
                    + "* watermelon" + System.lineSeparator();
        String expected = "App" + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "0", "-e", "1", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 57
    @Test
    public void textilatorTest57() {
        String input =  "* watermelon" + System.lineSeparator()
                + "Zoo" + System.lineSeparator();
        String expected = "-App" + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "1", "-e", "1", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 58
    @Test
    public void textilatorTest58() {
        String input =  "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator();
        String expected = "-App" + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "0", "-e", "1", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 59
    @Test
    public void textilatorTest59() {
        String input =  "* watermelon" + System.lineSeparator()
                + "Zoo" + System.lineSeparator();
        String expected = "ZOO" + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "1", "-c", "upper", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 60
    @Test
    public void textilatorTest60() {
        String input =  "* watermelon" + System.lineSeparator()
                + "Zoo" + System.lineSeparator();
        String expected = "zoo" + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "1", "-c", "lower", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 61
    @Test
    public void textilatorTest61() {
        String input =  "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator();
        String expected = "ZOO" + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "0", "-c", "upper", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 62
    @Test
    public void textilatorTest62() {
        String input =  "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator();
        String expected = "zoo" + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "0", "-c", "lower", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 63
    @Test
    public void textilatorTest63() {
        String input =  "* watermelon" + System.lineSeparator()
                + "Zoo" + System.lineSeparator();
        String expected = "-ZOO" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "1", "-c", "upper", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 64
    @Test
    public void textilatorTest64() {
        String input =  "* watermelon" + System.lineSeparator()
                + "Zoo" + System.lineSeparator();
        String expected = "-zoo" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "1", "-c", "lower", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 65
    @Test
    public void textilatorTest65() {
        String input =  "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator();
        String expected = "-ZOO" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "0", "-c", "upper", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 66
    @Test
    public void textilatorTest66() {
        String input =  "Zoo" + System.lineSeparator()
                + "* watermelon" + System.lineSeparator();
        String expected = "-zoo" + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "0", "-c", "lower", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 67
    @Test
    public void textilatorTest67() {
        String input =  "* watermelon" + System.lineSeparator() +
                "테스트 test" + System.lineSeparator();
        String expected = "테스트32 84 69 83 84 " + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "1", "-c", "upper", "-a", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 68
    @Test
    public void textilatorTest68() {
        String input =  "* watermelon" + System.lineSeparator() +
                "test" + System.lineSeparator();
    String expected = "116 101 115 116 " + System.lineSeparator();

    Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "1", "-c", "lower", "-a", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 69
    @Test
    public void textilatorTest69() {
        String input =   "test" + System.lineSeparator() +
                 "* watermelon" + System.lineSeparator();
        String expected = "84 69 83 84 " + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "0", "-c", "upper", "-a", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 70
    @Test
    public void textilatorTest70() {
        String input =   "test" + System.lineSeparator() +
                "* watermelon" + System.lineSeparator();
        String expected = "116 101 115 116 " + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "0", "-c", "lower", "-a", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 71
    @Test
    public void textilatorTest71() {
        String input =  "* watermelon" + System.lineSeparator() +
                "테스트 test" + System.lineSeparator();
        String expected = "-테스트32 84 69 83 84 " + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "1", "-c", "upper", "-a", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 72
    @Test
    public void textilatorTest72() {
        String input =  "* watermelon" + System.lineSeparator() +
                "test" + System.lineSeparator();
        String expected = "-116 101 115 116 " + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "1", "-c", "lower", "-a", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 73
    @Test
    public void textilatorTest73() {
        String input =   "test" + System.lineSeparator() +
                "* watermelon" + System.lineSeparator();
        String expected = "-84 69 83 84 " + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "0", "-c", "upper", "-a", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 74
    @Test
    public void textilatorTest74() {
        String input =   "test" + System.lineSeparator() +
                "* watermelon" + System.lineSeparator();
        String expected = "-116 101 115 116 " + System.lineSeparator();

        Path inputFile = createFile(input);
//        even
        String[] args = {"-s", "0", "-c", "lower", "-a", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 75
    @Test
    public void textilatorTest75() {
        String input =  "* watermelon" + System.lineSeparator() +
                "Zoo" + System.lineSeparator();
        String expected = "APP" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "1", "-c", "upper", "-e", "1", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 76
    @Test
    public void textilatorTest76() {
        String input =  "* watermelon" + System.lineSeparator() +
                "Zoo" + System.lineSeparator();
        String expected = "app" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "1", "-c", "lower", "-e", "1", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 77
    @Test
    public void textilatorTest77() {
        String input =  "Zoo" + System.lineSeparator() +
                "* watermelon" + System.lineSeparator();
        String expected = "APP" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "0", "-c", "upper", "-e", "1", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 78
    @Test
    public void textilatorTest78() {
        String input =  "Zoo" + System.lineSeparator() +
                "* watermelon" + System.lineSeparator();
        String expected = "app" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "0", "-c", "lower", "-e", "1", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 79
    @Test
    public void textilatorTest79() {
        String input =  "* watermelon" + System.lineSeparator() +
                "Zoo" + System.lineSeparator();
        String expected = "-APP" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "1", "-c", "upper", "-e", "1", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 80
    @Test
    public void textilatorTest80() {
        String input =  "* watermelon" + System.lineSeparator() +
                "Zoo" + System.lineSeparator();
        String expected = "-app" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "1", "-c", "lower", "-e", "1", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 81
    @Test
    public void textilatorTest81() {
        String input =  "Zoo" + System.lineSeparator() +
                "* watermelon" + System.lineSeparator();
        String expected = "-APP" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "0", "-c", "upper", "-e", "1", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }

    // Frame #: 82
    @Test
    public void textilatorTest82() {
        String input =  "Zoo" + System.lineSeparator() +
                "* watermelon" + System.lineSeparator();
        String expected = "-app" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-s", "0", "-c", "lower", "-e", "1", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }


    // Frame #: 84
    @Test
    public void textilatorTest84() {
        String input = "This is a normal text file." + System.lineSeparator()
                + "Perhaps too normal..." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-a", "-e", inputFile.toString()};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }
    // Frame #: 85
    @Test
    public void textilatorTest85() {
        String input = "This is a normal text file." + System.lineSeparator()
                + "Perhaps too normal..." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-s", "A", inputFile.toString()};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }
    // Frame #: 86
    @Test
    public void textilatorTest86() {
        String input = "This is a normal text file." + System.lineSeparator()
                + "Perhaps too normal..." + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-s", "*", inputFile.toString()};
        Main.main(args);

        Assertions.assertTrue(capture.stdout().isEmpty());
        Assertions.assertEquals(usageStr, capture.stderr());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }
    // Frame #: 87
    @Test
    public void textilatorTest87() {
        String input =  "* watermelon" + System.lineSeparator() +
                "Zoo" + System.lineSeparator();
        String expected =  "* watermelon" + System.lineSeparator() +
                "Zoo" + System.lineSeparator();

        Path inputFile = createFile(input);
//        odd
        String[] args = {"-x", "10", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));

    }

    // Frame #: 30
    @Test
    public void textilatorTest88() {
        String input = "This list is really important and should NOT get deleted." + System.lineSeparator()
                + "watermelon" + System.lineSeparator()
                + "* sunflower" + System.lineSeparator()
                + "* community center" + System.lineSeparator()
                + "* pelican town" + System.lineSeparator();
        String expected = "-This list is really important and should NOT get deleted." + System.lineSeparator()
                + "-watermelon" + System.lineSeparator();

        Path inputFile = createFile(input);
        String[] args = {"-x", "*", "-p", "-", inputFile.toString()};
        Main.main(args);

        Assertions.assertEquals(expected, capture.stdout());
        Assertions.assertTrue(capture.stderr().isEmpty());
        Assertions.assertEquals(input, getFileContent(inputFile));
    }
}