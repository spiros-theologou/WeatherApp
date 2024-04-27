package REST;
/*

  ======================
  +THIS IS A TEST CLASS+
  ======================


package REST;

/**
 *
 * @author stheo
*/
import java.util.HashMap;

public class TestMain {

    public static void main(String[] args) {
      HashMap<String, String> weatherMap  = new Search("Ιωάννινα").getWeatherData();
      if(!weatherMap.isEmpty()) 
        System.out.println(weatherMap);
    }
}

