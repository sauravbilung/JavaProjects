package sb.designpatterns.corepatterns._4_templateMethodPattern;

public class CSVDataRenderer extends DataRenderer {

    @Override
    public String readData() {
        return "CSV Data";
    }

    @Override
    public String processData(String data) {
        return "Processed "+data;
    }
}
