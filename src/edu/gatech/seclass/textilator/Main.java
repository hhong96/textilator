package edu.gatech.seclass.textilator;


import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Empty Main class for compiling Individual Project
    // During Deliverable 1 and Deliverable 2, DO NOT ALTER THIS CLASS or implement it

    private static void usage() {
        System.err.println("Usage: textilator [ -s number | -x substring | -c case | -e num | -a | -p prefix ] FILE");
    }

    public static void main(String[] args) {

        String fileName = null;
        boolean isCase = false; // -c
        boolean CaseUpper = false;
        boolean isShift = false; // -e
        int shift = -1;
        boolean isExclSubstr = false; // -x
        String exclSubstr = null;
        boolean isSkip = false; // -s
        int skipLines = -1;
        boolean isAscii = false; // -a
        boolean isPrefix = false; // -p
        String prefix = "";


         // Parse command line options
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.equals("-c")) {

                if (i + 1 < args.length) {
                    i++;
                    isCase = true;

                    if (args[i].equals("upper")) {
                        CaseUpper = true;
                    } else if (!args[i].equals("lower")) {
                        usage();
//                        System.err.println("should be upper or lower");
                        return;
                    }
                } else {
//                    Specifying option -c with a parameter outside of the allowed values ("upper", "lower") shall result in an error.
                    usage();
//                    System.err.println("no argument for -c");
                    return;
                }

            } else if (arg.equals("-e")) {

                if (i + 1 < args.length) {
                    i++;
                    isShift = true;

                    try {
                        shift = Integer.parseInt(args[i]);
                    } catch (NumberFormatException e) {
                        usage();
                        return;
                    }
                    if (shift < -25 || shift > 25) {
//                      Specifying option -e with a non-integer parameter or a parameter out of range shall result in an error.
                        usage();
                        return;
                    }
                } else {
                    usage();
//                    System.err.println("no argument for -e");
                    return;
                }

            } else if (arg.equals("-x")) {

                if (i + 1 < args.length) {
                    i++;
                    isExclSubstr = true;
                    exclSubstr = args[i];

                    if (exclSubstr == "") {
                        System.out.print("");
                    }
                } else {
                    usage();
//                    System.err.println("no argument for -x");
                    return;
                }
            } else if (arg.equals("-s")) {

                if (i + 1 < args.length) {
                    i++;
                    isSkip = true;

                    try {
                        skipLines = Integer.parseInt(args[i]);
                    } catch (NumberFormatException e) {
                        usage();
//                        System.err.println("-s argument should be in number format");
                        return;
                    }
                    if (skipLines < 0 || skipLines > 1) {
//                        - Specifying option -s with a parameter outside of the allowed values (0, 1) shall result in an error.
                        usage();
//                        System.err.println("-s argument should be 0 or 1");
                        return;
                    }
                } else {
                    usage();
//                    System.err.println("no argument for -s");
                    return;
                }
            } else if (arg.equals("-a")) {
                isAscii = true;

            } else if (arg.equals("-p")) {

                if (i + 1 < args.length) {
                    i++;
                    isPrefix = true;
                    prefix = args[i];

                    if (prefix.contains(".txt")) {
                        usage();
                        return;
                    }

                } else {
                    usage();
//                    System.err.println("no argument for -p");
                    return;
                }
                if (prefix.isEmpty()) {
//                  Specifying option -p with an empty string as the prefix parameter shall result in an error.
                    usage();
//                    System.err.println("argument for -p can't be empty");
                    return;
                }
            } else if (arg.contains(".txt")) {
                // Last argument is always the filename
                fileName = args[i];
            }
            else {
                usage();
                return;
            }
        }

        // Check for errors in options
        if (isExclSubstr && isSkip) {
//            Specifying options -s and -x simultaneously shall result in an error.
            usage();
//            System.err.println("-s and -x can't be used together");
            return;
        }
        if (isShift && isAscii) {
//            specifying options -e and -a simultaneously shall result in an error.
            usage();
//            System.err.println("-e and -a can't be used together");
            return;
        }

        // Read input file
        StringBuffer lines = new StringBuffer();
        try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
            Scanner scanner = new Scanner(inputStream);
            int numLines = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.append(line + System.lineSeparator());
                numLines++;
            }
            if (lines.length() == 0) {
                System.out.print("");
                return;
            }
        } catch (IOException e) {
            usage();
            return;
        }
        catch (NullPointerException e) {
            usage();
            return;
        }

//        [-s <number> or -x <substring>] -> [-c <case>] -> [-e <shift_amount> or -a] -> [-p <prefix>]
        String outputString = lines.toString();

        if (isSkip) {
            String[] arrayString = outputString.split(System.lineSeparator());
            String skipString = "";

            if (skipLines == 1) { // odd
                for (int i = 1; i < arrayString.length; i += 2) {
                    skipString += arrayString[i] + System.lineSeparator();
                }
            }
            else { // even
                for (int i = 0; i < arrayString.length; i += 2) {
                    skipString += arrayString[i] + System.lineSeparator();
                }
            }
            outputString = skipString;
        }

        if (isExclSubstr) {
            String[] arrayString = outputString.split(System.lineSeparator());
            String substrString = "";

            for (int i = 0; i < arrayString.length; i++) {
                if (!arrayString[i].contains(exclSubstr)) {
                    substrString += arrayString[i] + System.lineSeparator();
                }
            }
            outputString = substrString;
        }

        if (isCase) {
            String[] arrayString = outputString.split(System.lineSeparator());
            String caseString = "";
            for (int i = 0; i < arrayString.length; i++) {
                StringBuilder result = new StringBuilder();

                for (char c : arrayString[i].toCharArray()) {
                    if (Character.isLetter(c) && c >= 32 && c <= 126) { // check if the character is an English letter
                        if (CaseUpper) {
                            c = toUpperCase(c);
                        } else if (!CaseUpper) {
                            c = toLowerCase(c);
                        }
                    }
                    result.append(c);
                }
                caseString += result + System.lineSeparator();
            }
            outputString = caseString;
        }

        if (isShift) {
            String[] arrayString = outputString.split(System.lineSeparator());
            String shiftString = "";

            for (int i = 0; i < arrayString.length; i++) {
                StringBuilder result = new StringBuilder();

                for (char c : arrayString[i].toCharArray()) {
                    if (Character.isLetter(c) && c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') { // check if the character is an English letter
                        char base = Character.isUpperCase(c) ? 'A' : 'a';
                        int newPos = (c - base + shift) % 26;
                        if (newPos < 0) {
                            newPos += 26;
                        }
                        c = (char) (base + newPos);
                    }
                    result.append(c);
                }
                shiftString += result + System.lineSeparator();
            }
            outputString = shiftString;
        }

        if (isAscii) {
            String[] arrayString = outputString.split(System.lineSeparator());
            String asciiString = "";

            for (int i = 0; i < arrayString.length; i++) {
                StringBuilder result = new StringBuilder();

                for (char c : arrayString[i].toCharArray()) {
                    if (c >= 32 && c <= 126) { // check if the character is printable ASCII
                        result.append((int) c).append(" ");
                    }
                    else {
                        result.append(c);
                    }
                }
                asciiString += result + System.lineSeparator();
            }
            outputString = asciiString;
        }

        if (isPrefix) {
            String[] arrayString = outputString.split(System.lineSeparator());
            String prefixString = "";

            for (int i = 0; i < arrayString.length; i ++) {
                prefixString += prefix + arrayString[i] + System.lineSeparator();
            }
            outputString = prefixString;
        }

        System.out.print(outputString);
    }

}

