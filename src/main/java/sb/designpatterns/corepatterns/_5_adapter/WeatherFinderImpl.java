package sb.designpatterns.corepatterns._5_adapter;

public class WeatherFinderImpl implements WeatherFinder{
    @Override
    public int find(String city) {
        return 30;
    }
}
