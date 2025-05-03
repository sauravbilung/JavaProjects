package FileHandling._2_streamprocessing;

import java.io.*;

/**
 * Character stream or token by token stream
 * <p>
 * Lower-level processing — useful when we want fine control (e.g., parsing HTML, large JSON, etc.)
 */
public class _3_CharacterStream {

    public static void main(String[] args) {
        // Properly resolve home directory
        String filePath = System.getProperty("user.home") + "/IdeaProjects/LetsGoJava/src/main/resources/data/1800.csv";
        try (InputStream is = new FileInputStream(filePath); BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            int ch;
            while ((ch = reader.read()) != -1) {
                char character = (char) ch;
                System.out.print(character);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
