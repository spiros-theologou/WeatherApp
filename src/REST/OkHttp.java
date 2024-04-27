package REST;

/**
 *
 * @author stheo
 */

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {
    
    // create new OkHttpClient
    private OkHttpClient client = new OkHttpClient.Builder().build();

    // returns a response string of the json object requested
    public String run(String url) {        
        Request request = new Request.Builder().url(url).build();
        
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }
        }
        catch (IOException e){
            System.err.println(e);
            
        }           
        return null;
    }
}
