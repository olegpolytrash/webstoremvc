package com.webstoremvc.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.webstoremvc.utils.SimpleReference;

/**
 * Singleton.
 * Class for interacting with database
 */
public class DataBase {
	/**
	 * Singleton instance.
	 */
	private static DataBase dataBaseInstance = new DataBase();

	/**
	 * Constructor
	 */
	private DataBase() {
		openDataBaseConnection();
	}

	/**
	 * get the singleton instance
	 * @return singleton instance
	 */
	public static DataBase getInstance() {
		return dataBaseInstance;
	}

	/**
	 * Session factory.
	 */
	private static SessionFactory factory; 

	/**
	 * Open connection with database.
	 */
	public void openDataBaseConnection() {
		try{			
			Configuration configuration = new Configuration();
			configuration.configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
					configuration.getProperties()).build();

			factory = new Configuration().configure().buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}

	/**
	 * Helper method for managing database sessions and transactions. 
	 * Takes references of types session and transaction and stores ready-to-use object in them. 
	 * This method is useful for elimination of repeating standard operations with session and 
	 * transaction objects. After the operation with the database is done, call endDbAction or close 
	 * the corresponding objects manually.
	 * @param session reference of type session.
	 * @param tx reference of type transaction.
	 * @see endDbAction
	 * @see SimpleReference
	 */
	public void beginDbAction(SimpleReference<Session> session, SimpleReference<Transaction> tx) {
		session.setReference(factory.openSession());
		tx.setReference(session.getReference().beginTransaction());
	}

	/**
	 * Helper method for managing database sessions and transactions. 
	 * Takes references of types session and transaction and closes them.
	 * @param session reference to a session object.
	 * @param tx reference to a transaction object.
	 * @see beginDbAction
	 */
	public void endDbAction(SimpleReference<Session> session, SimpleReference<Transaction> transaction) {
		Session sessionObj = session.getReference();
		Transaction transactionObj = transaction.getReference();
		
		transactionObj.commit();
		sessionObj.close();
	}


}