package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        String mode = "enc";
        int key = 0;
        String data = "";
        String fileIn = "";
        String fileOut = "";
        String algorithm = "shift";
        String output;

        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-mode")) mode = args[i + 1];
            if (args[i].equals("-key")) key = Integer.parseInt(args[i + 1]);
            if (args[i].equals("-data")) data = args[i + 1];
            if (args[i].equals("-in")) fileIn = args[i + 1];
            if (args[i].equals("-out")) fileOut = args[i + 1];
            if (args[i].equals("-alg")) algorithm = args[i + 1];
        }

        if (data.isEmpty() && !fileIn.isEmpty()){
            try {
                data = new String (Files.readAllBytes(Paths.get(fileIn)));
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        switch (mode) {
            case "enc":
                Cipher resultEnc = new Cipher();
                resultEnc.setAlgorithm(algorithm);
                output = resultEnc.encode(data, key);
                break;
            case "dec":
                Cipher resultDec = new Cipher();
                resultDec.setAlgorithm(algorithm);
                output = resultDec.decode(data, key);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }

        if (fileIn.isEmpty()){
            System.out.println(output);
        } else {
            File result = new File(fileOut);
            try (FileWriter writer = new FileWriter(result)) {
                writer.write(output);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage() );
            }
        }
    }
}