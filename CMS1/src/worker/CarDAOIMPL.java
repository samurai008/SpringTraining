package worker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.CarEntityBean;
import utilities.HibernateUtility;

public class CarDAOIMPL {
	
	public int addCar(CarEntityBean car) throws Exception {
		int carId;
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			session = sessionFactory.openSession();
			
			session.getTransaction().begin();
			session.save(car);
			carId = car.getID();
			session.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return carId;
	}
	
	public CarEntityBean getCarDetails(int id) {
		CarEntityBean car;
		Session session = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			session = sessionFactory.openSession();
			
			session.getTransaction().begin();
			car = (CarEntityBean) session.get(CarEntityBean.class, id);
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return car;
	}
	
	
	
}
