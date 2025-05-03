package FileHandling._2_streamprocessing;

import java.io.*;

public class _1_ReadingFromFile {

    public static void main(String[] args) {

        try (InputStream is = _1_ReadingFromFile.class.getClassLoader().getResourceAsStream("data/1800.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
