package FileHandling._1_basics;

public class _1_ExtractNames {
    public static void main(String[] args) {
        String data = "name:John,age:30,location:NYC";

        int nameStart = data.indexOf("name") + "name:".length();
        int nameEnd = data.indexOf(",", nameStart);

        String extractedName = nameEnd == -1 ? data.substring(nameStart) : data.substring(nameStart, nameEnd);

        System.out.printf("Extracted name : %s", extractedName);
    }
}
