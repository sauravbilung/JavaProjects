package sb.designpatterns.corepatterns._5_adapter;

public class WeatherUI {
    public static void main(String[] args) {
       WeatherUI ui = new WeatherUI();
       ui.showTemperature(825314);
    }

    public void showTemperature(int zipCode){
       WeatherAdapter weatherAdapter = new WeatherAdapter();
       System.out.println(weatherAdapter.findTemperature(zipCode));
    }
}
