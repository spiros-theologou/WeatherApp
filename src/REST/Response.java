package REST;

/**
 *
 * @author stheo
 */

import com.google.gson.*; 
import java.util.Map;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.List;



public class Response {
    
    private String json;
    private HashMap<String, String> weatherMap = new HashMap<String, String>();
    
    // builder
    public Response(Request request){
        this.json = request.jsonRequest();
        try{
            this.generateMap();
        }
        catch(NullPointerException e){
            System.err.println("Failed to generate weather map!");
        }
        
    }
    

    public void setJson(String json){ this.json = json;}
    
    public String getJson() { return this.json;}
    
    
    // generates a weatherMap for the Response object {date, weatherDesc, cityName, windspeedKmph,humidity, uvIndex, tempC }
    private void generateMap(){
       Gson gson = new Gson(); 
       Type mapType = new TypeToken<Map<String, List<Map<String, Object>>>>() {}.getType();
       Map<String, List<Map<String, Object>>> map = gson.fromJson(this.json, mapType);
       
        //cityName
        this.weatherMap.put("cityName", map.get("nearest_area").get(0).get("areaName").toString().replace("[{value=", "").replace("}]",""));
       
        //date(YY-MM-DD)
        this.weatherMap.put("date",
               map.get("current_condition").get(0).get("localObsDateTime").toString().substring(0, 10)); 
       
        //tempC
        this.weatherMap.put("tempC",
               map.get("current_condition").get(0).get("temp_C").toString());
       
        //humidity
        this.weatherMap.put("humidity",
               map.get("current_condition").get(0).get("humidity").toString());
       
        //uvIndex
        this.weatherMap.put("uvIndex",
               map.get("current_condition").get(0).get("uvIndex").toString());
       
        //windspeedKmph
        this.weatherMap.put("windspeedKmph",
               map.get("current_condition").get(0).get("windspeedKmph").toString());
       
        //weatherDesc
        this.weatherMap.put("weatherDesc",
               map.get("current_condition").get(0).get("weatherDesc").toString().replace("[{value=", "").replace("}]",""));
        
        
        System.out.println("Map Generated!");
    }
    
    
    public HashMap<String, String> getWeatherMap(){ return this.weatherMap; }
}
