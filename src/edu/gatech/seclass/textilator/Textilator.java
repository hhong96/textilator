package edu.gatech.seclass.textilator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Textilator implements TextilatorInterface {
    private String filepath;
    private Case CaseUpper;
    private int shift;
    private String exclSubstr;
    private LineParity skipLines;
    private boolean isAscii;
    private String prefix;

    @Override
    public void reset() {
        filepath = null;
        shift = 0;
        exclSubstr = null;
        CaseUpper = null;
        skipLines = null;
        isAscii = false;
        prefix = null;
    }

    @Override
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void setLineToSkip(LineParity skipLines) {
        this.skipLines = skipLines;
    }

    @Override
    public void setExcludeString(String exclSubstr) {
        this.exclSubstr = exclSubstr;
    }

    @Override
    public void setLetterCase(Case CaseUpper) {
        this.CaseUpper = CaseUpper;
    }

    @Override
    public void setCipherText(int shift) {
        this.shift = shift;
    }

    @Override
    public void setEncodeLines(boolean isAscii) {
        this.isAscii = isAscii;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void textilator() throws TextilatorException {
        if (filepath == null) {
            throw new TextilatorException("Filepath not set.");
        }
        File file = new File(filepath);
        if (!file.exists()) {
            throw new TextilatorException("File not found.");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (shouldSkipLine(lineNumber)) {
                    lineNumber++;
                    continue;
                }
                if (exclSubstr != null && line.contains(exclSubstr)) {
                    lineNumber++;
                    continue;
                }
                if (CaseUpper != null) {
                    line = applyLetterCase(line);
                }
                if (shift != 0) {
                    line = applyCipherText(line);
                }
                if (isAscii) {
                    line = encodeAscii(line);
                }
                if (prefix != null) {
                    line = prefix + line;
                }
                System.out.println(line);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            throw new TextilatorException("File not found.");
        } catch (IOException e) {
            throw new TextilatorException("Error reading file.");
        }
    }

    private boolean shouldSkipLine(int lineNumber) {
        if (skipLines == null) {
            return false;
        }
        if (skipLines == LineParity.even && lineNumber % 2 == 0) {
            return true;
        }
        if (skipLines == LineParity.odd && lineNumber % 2 == 1) {
            return true;
        }
        return false;
    }

    private String applyLetterCase(String line) {
        StringBuilder builder = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (CaseUpper == Case.upper) {
                builder.append(Character.toUpperCase(c));
            } else if (CaseUpper == Case.lower)   {
                builder.append(Character.toLowerCase(c));
            }
            else {
                builder.append(c);
            }
        }
        return String.valueOf(builder);
    }

    private String applyCipherText(String line) {
        StringBuilder sb = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (Character.isLetter(c) && c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') { // check if the character is an English letter
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int newPos = (c - base + shift) % 26;
                if (newPos < 0) {
                    newPos += 26;
                }
                c = (char) (base + newPos);
            }
            sb.append(c);
        }
        return String.valueOf(sb);
    }

    private String encodeAscii(String line) {
        StringBuilder sb = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c >= 32 && c <= 126) { // check if the character is printable ASCII
                sb.append((int) c).append(" ");
            } else {
                sb.append(c);
            }
        }
        return String.valueOf(sb);
    }
}