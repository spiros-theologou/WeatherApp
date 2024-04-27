package DB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "WEATHER_DATA")
@NamedQueries({
    @NamedQuery(name = "WeatherData.findAll", query = "SELECT w FROM WeatherData w"),
    @NamedQuery(name = "WeatherData.findByWeatherId", query = "SELECT w FROM WeatherData w WHERE w.weatherId = :weatherId"),
    @NamedQuery(name = "WeatherData.findByWeatherDate", query = "SELECT w FROM WeatherData w WHERE w.weatherDate = :weatherDate"),
    @NamedQuery(name = "WeatherData.findByCityName", query = "SELECT w FROM WeatherData w WHERE w.cityName = :cityName"),
    @NamedQuery(name = "WeatherData.findByTemperature", query = "SELECT w FROM WeatherData w WHERE w.temperature = :temperature"),
    @NamedQuery(name = "WeatherData.findByHumidity", query = "SELECT w FROM WeatherData w WHERE w.humidity = :humidity"),
    @NamedQuery(name = "WeatherData.findByWindspeed", query = "SELECT w FROM WeatherData w WHERE w.windspeed = :windspeed"),
    @NamedQuery(name = "WeatherData.findByUvIndex", query = "SELECT w FROM WeatherData w WHERE w.uvIndex = :uvIndex"),
    @NamedQuery(name = "WeatherData.findByWeatherDesc", query = "SELECT w FROM WeatherData w WHERE w.weatherDesc = :weatherDesc")})
public class WeatherData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WEATHER_ID")
    private Integer weatherId;
    @Basic(optional = false)
    @Column(name = "WEATHER_DATE")
    private String weatherDate;
    @Basic(optional = false)
    @Column(name = "CITY_NAME")
    private String cityName;
    @Basic(optional = false)
    @Column(name = "TEMPERATURE")
    private int temperature;
    @Basic(optional = false)
    @Column(name = "HUMIDITY")
    private int humidity;
    @Basic(optional = false)
    @Column(name = "WINDSPEED")
    private int windspeed;
    @Basic(optional = false)
    @Column(name = "UV_INDEX")
    private int uvIndex;
    @Basic(optional = false)
    @Column(name = "WEATHER_DESC")
    private String weatherDesc;

    public WeatherData() {
    }

    public WeatherData(Integer weatherId) {
        this.weatherId = weatherId;
    }

    public WeatherData(Integer weatherId, String weatherDate, String cityName, int temperature, int humidity, int windspeed, int uvIndex, String weatherDesc) {
        this.weatherId = weatherId;
        this.weatherDate = weatherDate;
        this.cityName = cityName;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windspeed = windspeed;
        this.uvIndex = uvIndex;
        this.weatherDesc = weatherDesc;
    }

    public Integer getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }

    public String getWeatherDate() {
        return weatherDate;
    }

    public void setWeatherDate(String weatherDate) {
        this.weatherDate = weatherDate;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(int windspeed) {
        this.windspeed = windspeed;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (weatherId != null ? weatherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WeatherData)) {
            return false;
        }
        WeatherData other = (WeatherData) object;
        if ((this.weatherId == null && other.weatherId != null) || (this.weatherId != null && !this.weatherId.equals(other.weatherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pli24.weatherapp.db.WeatherData[ weatherId=" + weatherId + " ]";
    }
    
}
