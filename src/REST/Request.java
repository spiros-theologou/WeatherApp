package REST;

/**
 *
 * @author stheo
 */
public class Request {
    private String cityName;
    
    // request builder
    public Request(String cityName){ 
        this.cityName = cityName;
        System.out.println("Creating request for '" + this.cityName + "'");
    }
    
    // returns a json with the given city's weather data
    public String jsonRequest(){
        if (this.cityName.equals(""))
            return null;
        OkHttp callHttp = new OkHttp();
        String url = UrlForRest.createUrl(this.cityName);
        String json = callHttp.run(url);
        return json;
    }
    
}
