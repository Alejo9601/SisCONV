package Model;

import Model.DTO.LandProperty;
import Model.DTO.Vehicle;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alejandro Juarez
 */
public class PropertyManager {

    public static enum vehicle_param {
        DOMAIN, MODEL, MANUFACTURER, TYPE, ID_VEHICLE
    };

    public static enum landProperty_param {
        APPLE, BATCH, DECREE, ADDRESS, ID_LANDPROPERTY
    };

    /**
     * Will save v new landProp on DB.
     *
     * @param vehicleMap
     * @return
     */
    public boolean newVehicle(EnumMap<vehicle_param, String> vehicleMap) {
        //Unpacking all the data.
        Vehicle vehicle = unpackVehicleMap(vehicleMap);
        //Opening Hibernate session.
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean flag = true; //Flag that indicates if the operation finished succesfully.
        try {
            transaction = session.beginTransaction();
            session.save(vehicle);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) { //If transaction didnt went well, we roll back any action en DB
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Excepcion dando de alta un nuevo vehiculo" + e);
            flag = false;
        } finally {
            session.close();
        }
        return flag;
    }

    /**
     * Gets all vehicles from DB.
     *
     * @return
     */
    public List<EnumMap<vehicle_param, String>> consultAllVehicles() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Vehicle> vehicleL = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM vehicle");
            consult.addEntity(Vehicle.class);
            vehicleL = consult.list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando todos los vehiculos" + e);
        } finally {
            session.close();
        }
        return vehiclesOnEnumList(vehicleL);
    }

    /**
     * Gets the landProperty for the specified id.
     *
     * @param idVehicle
     * @return
     */
    public EnumMap<vehicle_param, String> consultVehicle(Long idVehicle) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Vehicle vehicle = null;
        try {
            SQLQuery consulta = session.createSQLQuery(
                    "SELECT * FROM vehicle WHERE vehicle.id_vehicle = " + idVehicle);
            consulta.addEntity(Vehicle.class);
            vehicle = (Vehicle) consulta.uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el vehiculo" + e);
        } finally {
            session.close();
        }
        return vehicleOnEnumMap(vehicle);
    }

    /**
     * Gets the landProperty for the specified id.
     *
     * @param domain
     * @return
     */
    public EnumMap<vehicle_param, String> consultVehicle(String domain) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Vehicle vehicle = null;
        try {
            SQLQuery consulta = session.createSQLQuery(
                    "SELECT * FROM vehicle WHERE vehicle.domain = '" + domain + "'");
            consulta.addEntity(Vehicle.class);
            vehicle = (Vehicle) consulta.uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el vehiculo" + e);
        } finally {
            session.close();
        }
        return vehicleOnEnumMap(vehicle);
    }

    /**
     * Will extract landProp info from EnumMap.
     *
     * @param vehicleMap
     * @return
     */
    private Vehicle unpackVehicleMap(EnumMap<vehicle_param, String> vehicleMap) {
        Vehicle vehicle = new Vehicle(
                vehicleMap.get(vehicle_param.DOMAIN),
                vehicleMap.get(vehicle_param.TYPE),
                vehicleMap.get(vehicle_param.MANUFACTURER),
                vehicleMap.get(vehicle_param.MODEL));
        return vehicle;
    }

    /**
     * Will construct a list of enum maps, each one of them with information of
     * landProp.
     *
     * @return
     */
    private List<EnumMap<vehicle_param, String>> vehiclesOnEnumList(List<Vehicle> vehicleL) {
        if (vehicleL != null) {
            List<EnumMap<vehicle_param, String>> vehicleEL = new ArrayList<>();
            for (Vehicle v : vehicleL) {
                vehicleEL.add(vehicleOnEnumMap(v));
            }
            return vehicleEL;
        }
        return null;
    }

    /**
     * Will construct an EnumMap with the information of the vehicle.
     *
     * @param vehicle
     * @return
     */
    public EnumMap<vehicle_param, String> vehicleOnEnumMap(Vehicle vehicle) {
        if (vehicle != null) {
            EnumMap<vehicle_param, String> vehicleMap
                    = new EnumMap<>(vehicle_param.class);
            vehicleMap.put(
                    vehicle_param.DOMAIN,
                    vehicle.getDomain());
            vehicleMap.put(
                    vehicle_param.MODEL,
                    vehicle.getModel());
            vehicleMap.put(
                    vehicle_param.MANUFACTURER,
                    vehicle.getManufacturer());
            vehicleMap.put(
                    vehicle_param.TYPE,
                    vehicle.getType());
            vehicleMap.put(
                    vehicle_param.ID_VEHICLE,
                    Long.toString(vehicle.getIdVehicle()));
            return vehicleMap;
        }
        return null;
    }

    /**
     * Will save new land / property on DB.
     *
     * @param landPropMap
     * @return
     */
    public boolean newLandProperty(EnumMap<landProperty_param, String> landPropMap) {
        //Unpacking all the data.
        LandProperty landProp = unpackLandPropertyMap(landPropMap);
        //Opening Hibernate session.
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean flag = true; //Flag that indicates if the operation finished succesfully.
        try {
            transaction = session.beginTransaction();
            session.save(landProp);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) { //If transaction didnt went well, we roll back any action en DB
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(
                    null,
                    "Excepcion dando de alta un nuevo terreno / inmueble" + e);
            flag = false;
        } finally {
            session.close();
        }
        return flag;
    }

    /**
     * Gets all land / property from DB.
     *
     * @return
     */
    public List<EnumMap<landProperty_param, String>> consultAllLandProperties() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<LandProperty> landPropertiesL = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM land_property");
            consult.addEntity(LandProperty.class);
            landPropertiesL = consult.list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando todos los terrenos / inmuebles" + e);
        } finally {
            session.close();
        }
        return landPropertiesOnEnumList(landPropertiesL);
    }

    /**
     * Gets the land / property for the specified id.
     *
     * @param idLandProp
     * @return
     */
    public EnumMap<landProperty_param, String> consultLandProperty(Long idLandProp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        LandProperty landProperty = null;
        try {
            SQLQuery consulta = session.createSQLQuery(
                    "SELECT * FROM land_property WHERE land_property.id_property = " + idLandProp);
            consulta.addEntity(LandProperty.class);
            landProperty = (LandProperty) consulta.uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el terreno / inmueble" + e);
        } finally {
            session.close();
        }
        return landPropertyOnEnumMap(landProperty);
    }

    /**
     * Will extract all the data of a land / property from EnumMap.
     *
     * @param landPropMap
     * @return
     */
    private LandProperty unpackLandPropertyMap(EnumMap<landProperty_param, String> landPropMap) {
        LandProperty landProp = new LandProperty(
                landPropMap.get(landProperty_param.APPLE),
                landPropMap.get(landProperty_param.BATCH),
                landPropMap.get(landProperty_param.DECREE));
        return landProp;
    }

    /**
     * Will construct a list of enum maps, each one of them with information of
     * land / property.
     *
     * @return
     */
    private List<EnumMap<landProperty_param, String>> landPropertiesOnEnumList(List<LandProperty> landPropL) {
        if (landPropL != null) {
            List<EnumMap<landProperty_param, String>> landPropEL = new ArrayList<>();
            for (LandProperty lp : landPropL) {
                landPropEL.add(landPropertyOnEnumMap(lp));
            }
            return landPropEL;
        }
        return null;
    }

    /**
     * Will construct an EnumMap with the information of the land / property.
     *
     * @param landProperty
     * @return
     */
    public EnumMap<landProperty_param, String> landPropertyOnEnumMap(LandProperty landProperty) {
        if (landProperty != null) {
            EnumMap<landProperty_param, String> landPropMap
                    = new EnumMap<>(landProperty_param.class);
            landPropMap.put(
                    landProperty_param.ID_LANDPROPERTY,
                    Long.toString(landProperty.getIdProperty()));
            landPropMap.put(
                    landProperty_param.APPLE,
                    landProperty.getApple());
            landPropMap.put(
                    landProperty_param.BATCH,
                    landProperty.getBatch());
            landPropMap.put(
                    landProperty_param.DECREE,
                    landProperty.getAdjudicationDecree());
//            landPropMap.put(
//                    landProperty_param.ADDRESS,
//                    landProperty.);
            return landPropMap;
        }
        return null;
    }

}
