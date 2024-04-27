package REST;

/**
 *
 * @author stheo
 */
import java.util.HashMap;

public class Search {
    
    private Response response;
    private Request request;
    
    public Search(String cityName){
        this.request = new Request(cityName);
        this.response = new Response(request);
    }
    
    /* returns a map with the keys : 
        "weatherDesc", 
        "cityName", 
        "uvIndex",  
        "date", 
        "tempC", 
        "windspeedKmph" */
    public HashMap<String, String> getWeatherData(){
        return response.getWeatherMap();
    }
}
