package FileHandling._2_streamprocessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2_StreamingFromStdIn {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
