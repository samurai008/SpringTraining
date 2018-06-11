package ui;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mysql.jdbc.Driver;

import java.util.List;
import entity.CarEntityBean;
import entity.DriverEntityBean;
import utilities.HibernateUtility;
import worker.CarDAOIMPL;
import worker.DriverDAOIMPL;

public class App {
	static DriverDAOIMPL result = new DriverDAOIMPL();

	public static void main(String[] args) {
		
		try {
////		-----------------------
////			Create a new driver
////		-----------------------
//			DriverEntityBean driver = new DriverEntityBean();
//			driver.setDriverId(0);
//			driver.setDriverName("Michael Yearn");
////			Create a new car
//			CarEntityBean car = new CarEntityBean();
//			CarDAOIMPL createCar = new CarDAOIMPL();
//			car = createCar.getCarDetails(2);
//			driver.setCarDetails(car);
//			saveDriver(driver, car.getID());
			
////		-----------------------
//			Read a single driver
////		-----------------------
//			DriverEntityBean driver = result.getDriverDetails(7);
//			CarEntityBean car = driver.getCarDetails();
//			System.out.println("Driver Name: " + driver.getDriverName());
//			System.out.println("Driver's Car: "  + car.getCompany() + " " + car.getName() + ", age=" + car.getAge());
			
////		-----------------------
//			Get list of drivers driving a particular car
////		-----------------------
//			List<DriverEntityBean> drivers = result.getDriversWithCarId(1);
//			System.out.println("Name\tCar");
//			System.out.println("----\t---");
//			for (DriverEntityBean driver: drivers) {
//				CarEntityBean car = driver.getCarDetails();
//				System.out.println(driver.getDriverName() + "\t" + car.getCompany() + " " + car.getName());
//			}
			
////		-----------------------
//			Update a driver
////		-----------------------
//			DriverEntityBean driver = new DriverEntityBean();
//			driver.setDriverName("Cookie");
////			Create a new car
//			CarEntityBean car = new CarEntityBean();
//			car.setName("Cruiser");
//			car.setCompany("Toyota");
//			car.setAge(12);
//			CarDAOIMPL createCar = new CarDAOIMPL();
//			int carId = createCar.addCar(car);
//			
//			driver.setCarDetails(car);
////			Update driver with id=7
//			updateDriver(driver, 7);
			
////		-----------------------
//			Delete a driver by name
////		-----------------------
//			deleteDriver("Xi");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtility.closeSessionFactory();
		}
		
	}
	
	public static void showDriverDetails(int id) {
		DriverEntityBean driver;
//	    Get individual driver details
		driver = result.getDriverDetails(id);
		CarEntityBean car = driver.getCarDetails();
		
		System.out.println(driver.getDriverId() + " => " + driver.getDriverName());
		System.out.println("Driver " + driver.getDriverName() + " drives " + car.getCompany() + ", " + car.getName() + ".");
	}
	
	public static void driverDetailsByCarId(int id) {
//		Get a list of driver details by car id
		List<DriverEntityBean> drivers = result.getDriversWithCarId(id);
					
		for (DriverEntityBean driver: drivers) {
			System.out.println("Driver Name: " + driver.getDriverName());
			System.out.println("Driver's Car: " + driver.getCarDetails().getName());
		}
	}
	
	public static void updateDriver(DriverEntityBean driver, int driverId) {
//		Update a driver
		result.updateDriver(driver, driverId);
	}
	
	public static void deleteDriver(String name) {
//		Delete driver by name
//		Throws exception when name returns more than one row
		result.deleteDriverByName(name);
	}
	
	public static void saveDriver(DriverEntityBean driver, int carId) {
//		Save driver details with car id
		result.saveDriverDetails(driver, carId);
	}
	
	

}
