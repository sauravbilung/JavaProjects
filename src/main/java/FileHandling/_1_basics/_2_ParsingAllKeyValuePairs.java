package FileHandling._1_basics;

public class _2_ParsingAllKeyValuePairs {

    public static void main(String[] args) {
        String data = "name:John,age:30,location:NYC";

        int start = 0;

        while (start < data.length()) {
            int colon = data.indexOf(":", start);
            int comma = data.indexOf(",", start);

            String key = data.substring(start, colon);
            String value = comma == -1 ? data.substring(colon + 1) : data.substring(colon + 1, comma);

            System.out.printf("\n Key = %s, value = %s", key, value);
            start = comma == -1 ? data.length() : comma + 1;
        }
    }
}
