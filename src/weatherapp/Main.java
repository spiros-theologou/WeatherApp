
package weatherapp;

/**
 *
 * @author stheo
 */

import Windows.Menu;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    

    //--------------------------------------------------------------------------
    // obtaining an EntityManager instance
    private static EntityManagerFactory entityManagerFactory;    
    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = 
                Persistence.createEntityManagerFactory("weatherAppPU");
        }
        return entityManagerFactory.createEntityManager();
    }
    //--------------------------------------------------------------------------
    
    public static void main(String[] args) {
        
        Menu.showMenu();
    }
}
