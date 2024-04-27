package DB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "CITY_COUNT")
@NamedQueries({
    @NamedQuery(name = "CityCount.findAll", query = "SELECT c FROM CityCount c"),
    @NamedQuery(name = "CityCount.findByCity", query = "SELECT c FROM CityCount c WHERE c.city = :city"),
    @NamedQuery(name = "CityCount.findByCount", query = "SELECT c FROM CityCount c WHERE c.count = :count")})
public class CityCount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CITY")
    private String city;
    @Basic(optional = false)
    @Column(name = "COUNT")
    private int count;

    public CityCount() {
    }

    public CityCount(String city) {
        this.city = city;
    }

    public CityCount(String city, int count) {
        this.city = city;
        this.count = count;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (city != null ? city.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CityCount)) {
            return false;
        }
        CityCount other = (CityCount) object;
        if ((this.city == null && other.city != null) || (this.city != null && !this.city.equals(other.city))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "weatherapp.db.CityCount[ city=" + city + " ]";
    }
    
}
