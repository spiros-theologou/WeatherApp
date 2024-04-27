package DB;

import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import weatherapp.Main;
import java.util.Map;

/**
 *
 * @author alexa
 */
public class Connector {
    
    //-------------------------------------------------------------------------
    //New search: ADD city in CITY_COUNT, sets count to 1
    //If city already in table, count +1
    public static void searchCity(String city){
        EntityManager em = Main.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("CityCount.findByCity");
        query.setParameter("city", city);
        List resultList = query.getResultList();
        
        if (resultList.isEmpty()){
            CityCount c = new CityCount(city, 1);
            em.persist(c);
        }
        else {
            CityCount c=(CityCount) resultList.get(0) ; 
            int currentCount=c.getCount();            
            c.setCount(currentCount + 1);
            em.merge(c);
        }
        em.getTransaction().commit();
        em.close();         
    }
    //--------------------------------------------------------------------------
   
   //-------------------------------------------------------------------------
    //DELETE City from CityCount
        public static void deleteCityCount(String city){
        EntityManager em = Main.getEntityManager();
        em.getTransaction().begin();
        CityCount c = em.createNamedQuery("CityCount.findByCity",CityCount.class).
                setParameter("city", city).getSingleResult();
        em.remove(c);         
        em.getTransaction().commit();
        em.close();         
    }
    //--------------------------------------------------------------------------     
      
    //-------------------------------------------------------------------------
    //SAVE weather record in WEATHER_DATA if it doesn't already exist
    public static void saveRecord(String cityName, int temperature, int humidity, 
            int windspeed, int uvIndex, String weatherDesc, String weatherDate){
        EntityManager em = Main.getEntityManager();
        em.getTransaction().begin();
        
        List<WeatherData> wList=em.createQuery(
        "SELECT wd FROM WeatherData wd WHERE wd.cityName LIKE :cityName AND wd.weatherDate LIKE :weatherDate", 
        WeatherData.class).setParameter("cityName",cityName)
                .setParameter("weatherDate",weatherDate).getResultList();
        
        if (wList.isEmpty()){
            WeatherData w = new WeatherData();
            w.setCityName(cityName);
            w.setWeatherDate(weatherDate);
            w.setTemperature(temperature);
            w.setHumidity(humidity);
            w.setWindspeed(windspeed); 
            w.setUvIndex(uvIndex);
            w.setWeatherDesc(weatherDesc);
            em.persist(w);
        }
        else {
            System.out.println("The record for "+cityName+" "+weatherDate+" already exists.");
        }
        em.getTransaction().commit();
        em.close();         
    }
    //--------------------------------------------------------------------------
    
    //SAVE weather record in WEATHER_DATA if it doesn't already exist (map)
    public static void saveRecordMap(Map<String, String> map){
        EntityManager em = Main.getEntityManager();
        em.getTransaction().begin();

        List<WeatherData> wList=em.createQuery(
        "SELECT wd FROM WeatherData wd WHERE wd.cityName LIKE :cityName AND wd.weatherDate LIKE :weatherDate", 
        WeatherData.class).setParameter("cityName",map.get("cityName"))
                .setParameter("weatherDate",map.get("date")).getResultList();


        if (wList.isEmpty()){
            WeatherData w = new WeatherData();
            w.setCityName(map.get("cityName"));
            w.setWeatherDate(map.get("date"));
            w.setTemperature(Integer.parseInt(map.get("tempC")));
            w.setHumidity(Integer.parseInt(map.get("humidity")));
            w.setWindspeed(Integer.parseInt(map.get("windspeedKmph"))); 
            w.setUvIndex(Integer.parseInt(map.get("uvIndex")));
            w.setWeatherDesc(map.get("weatherDesc"));
            em.persist(w);
        }
        else {
            System.out.println("The record for "+map.get("cityName")+" "+map.get("date")+" already exists.");
        }
        em.getTransaction().commit();
        em.close();
    }
    //-------------------------------------------------------------------------
    
    //EDIT weather latest record in WEATHER_DATA (null for args that won't be changed)
    public static void editRecord(String cityName, Integer temperature, Integer humidity, 
            Integer windspeed, Integer uvIndex, String weatherDesc){
        
        Objects.requireNonNull(cityName, "City cannot be null");        
        
        EntityManager em = Main.getEntityManager();
        em.getTransaction().begin();
        
        WeatherData w = em.createQuery(
        "SELECT wd FROM WeatherData wd WHERE wd.cityName LIKE :cityName ORDER BY wd.weatherDate DESC",
        WeatherData.class)
        .setParameter("cityName", cityName)
        .setMaxResults(1)
        .getSingleResult();

        if (temperature!=null){
            w.setTemperature(temperature);
        }
        if (humidity!=null) {
            w.setHumidity(humidity);
        }
        if (windspeed!=null) {
            w.setWindspeed(windspeed);
        }
        if (uvIndex!=null) {
            w.setUvIndex(uvIndex);
        }
        if (weatherDesc!=null) {
            w.setWeatherDesc(weatherDesc);
        }
        em.merge(w);
        em.getTransaction().commit();
        em.close();         
    }
    //--------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    //DELETE all weather records in WEATHER_DATA for a city
    public static void deleteRecord(String cityName){   
        EntityManager em = Main.getEntityManager();
        em.getTransaction().begin();
        
        List <WeatherData> wList = em.createQuery(
        "SELECT wd FROM WeatherData wd WHERE wd.cityName LIKE :cityName",
        WeatherData.class)
        .setParameter("cityName", cityName)
        .getResultList();
        
        for (WeatherData w: wList){
            em.remove(w);
        }
        em.getTransaction().commit();
        em.close();         
    }
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    //RETRIEVE the last entry for a specific city
    public static WeatherData retrieveLastEntry(String cityName){
        
        Objects.requireNonNull(cityName, "City cannot be null");        
        
        EntityManager em = Main.getEntityManager();
        em.getTransaction().begin();
        
        WeatherData w = em.createQuery(
        "SELECT wd FROM WeatherData wd WHERE wd.cityName LIKE :cityName ORDER BY wd.weatherDate DESC",
        WeatherData.class)
        .setParameter("cityName", cityName)
        .setMaxResults(1)
        .getSingleResult();
        
        em.getTransaction().commit();
        em.close(); 
        return w;
    }
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    //RETREIVE all cities in WEATHER_DATA
    public static List<String> retreiveCities() {
        EntityManager em = Main.getEntityManager();
        em.getTransaction().begin();
        
        List <String> cList=em.createQuery(
            "SELECT DISTINCT wd.cityName FROM WeatherData wd",String.class)
                .getResultList();
        for (String c:cList){
            System.out.println(c);
        }
        return cList;
    }
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    //RETREIVE all dates for a specific city in WEATHER_DATA
    public static List<String> retreiveDates(String cityName) {
        EntityManager em = Main.getEntityManager();
        em.getTransaction().begin();
        
        List <String> cList=em.createQuery(
            "SELECT wd.weatherDate FROM WeatherData wd WHERE wd.cityName LIKE :cityName",
                String.class)
                .setParameter("cityName", cityName)
                .getResultList();
        for (String c:cList){
            System.out.println(c);
        }
        return cList;
    }
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    //RETREIVE all cities in CITY_COUNT in descending order(counts)
    public static List<Object[]> retreiveCounts() {
        EntityManager em = Main.getEntityManager();
        em.getTransaction().begin();
        
        List <Object[]> cList=em.createQuery(
            "SELECT c.city,c.count FROM CityCount c ORDER BY c.count DESC",Object[].class)
                .getResultList();       
               
        return cList; //Return records as objects        
    }
    //--------------------------------------------------------------------------
    
    
}
