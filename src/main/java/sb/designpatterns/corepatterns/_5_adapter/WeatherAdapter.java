package sb.designpatterns.corepatterns._5_adapter;

public class WeatherAdapter {

    public int findTemperature(int zipCode) {
        String city = null;
        if (zipCode == 825314) {
            city = "Ramgarh";
        }
        WeatherFinder finder = new WeatherFinderImpl();
        int temperature = finder.find(city);
        return temperature;
    }
}
