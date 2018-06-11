package worker;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entity.CarEntityBean;
import entity.DriverEntityBean;
import utilities.HibernateUtility;

public class DriverDAOIMPL {
	
	public DriverEntityBean getDriverDetails(int id) {
		DriverEntityBean driver;
		Session session = null;
		
		try {
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			session = sessionFactory.openSession();
			
			session.getTransaction().begin();
			driver = (DriverEntityBean) session.get(DriverEntityBean.class, id);
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return driver;
	}
	
	public List<DriverEntityBean> getDriversWithCarId(int id) {
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			session = sessionFactory.openSession();
			Criteria criteriaQuery =  session.createCriteria(DriverEntityBean.class).add(Restrictions.eq("carEntityBean.id", id));
	
			List<DriverEntityBean> list = criteriaQuery.list();

			return list;

		} catch (Exception exception) {

			throw exception;
		}
	}
	
	public void updateDriver(DriverEntityBean driver, int driverId) {
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			session = sessionFactory.openSession();
			
			Query query = session.createQuery("Update DriverEntityBean set Name=?, CarID=? where id=?"); 
			query.setParameter(0, driver.getDriverName());
			query.setParameter(1, driver.getCarDetails().getID());
			query.setParameter(2, driverId);

			session.beginTransaction();
			int k=query.executeUpdate();
			System.out.println("Updated rows: "+k);
			session.getTransaction().commit();							
			
		} catch (Exception exception) {

			throw exception;
		} 
	}
	
	public void deleteDriverByName(String name) {
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			session = sessionFactory.openSession();
			
			String q = "from DriverEntityBean where name like '" + name +"%'";
			Query query1 = session.createQuery(q);
			
			session.beginTransaction();
			Object driver = query1.uniqueResult();
			session.getTransaction().commit();
//			System.out.println(driver instanceof DriverEntityBean);
			
			if (driver == null) {
				System.out.println("The name returned 0 result.");
			} else {
				Query query = session.createQuery("delete from DriverEntityBean where name=?");
				query.setParameter(0, name);
				
				session.beginTransaction();
				int k = query.executeUpdate();
				System.out.println(k + " rows deleted.");
				session.getTransaction().commit();
			}							
			
		}
		catch (Exception exception) {
			throw exception;
		} 
	}
	
	public void saveDriverDetails(DriverEntityBean driver, int carId) {
		Session session = null;
		try {
			CarEntityBean car = new CarDAOIMPL().getCarDetails(carId); 
			driver.setCarDetails(car);
			
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			session = sessionFactory.openSession();
			
			session.getTransaction().begin();
			session.save(driver);
			session.getTransaction().commit();
			
			System.out.println("Details of driver\" " + driver.getDriverName() + "\" saved.");
			
		} catch (Exception exception) {

			throw exception;
		} 
	}

}
