package REST;

/**
 *
 * @author stheo
 */
public class UrlForRest {
    
     // start and end of url requests
    private static final String URL_START = "https://wttr.in/";
    private static final String URL_END = "?format=j1";
    
    // given a city name, returns a formatted url i.e. "https://wttr.in/Patras/format=j1";
    public static String createUrl(String cityName){
        return URL_START + cityName + URL_END;
    }
}
