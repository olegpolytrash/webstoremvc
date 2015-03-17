package com.webstoremvc.database;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.common.base.Optional;
import com.webstoremvc.model.DataBase;
import com.webstoremvc.utils.SimpleReference;

/**
 * Base class for all query classes to extend.
 * Defines functions for preparing the database for an operation and 
 * cleanup after the operation has been completed.
 */
public abstract class QueryBase {
	/**
	 * Reference to a session object. This reference is
	 * for passing the session object to the database for initialization and closing.
	 */
	private SimpleReference<Session> session;
	/**
	 * Reference to a transaction object. This reference is
	 * for passing the transaction object to the database for initialization and committing.
	 */
	private SimpleReference<Transaction> transaction;
	/**
	 * The database object.
	 */
	private DataBase database;
	
	/**
	 * Default constructor.
	 * Initializes all fields with default values.
	 */
	public QueryBase() {
		database = DataBase.getInstance();
		session = new SimpleReference<Session>();
		transaction = new SimpleReference<Transaction>();
	}
	
	/**
	 * Preparing the database for an operation.
	 * This function opens a session and begins a transaction. It is required to close 
	 * these object either using endDbAction() or by calling the close() for session and commit()
	 * for transaction. It is recommended to call this function in try{} block and 
	 * endDbAction() in finally{}.
	 * @see endDbAction
	 */
	public void beginDbAction() {
		database.beginDbAction(session, transaction);
	}

	/**
	 * Submit operation's result to the database and
	 * cleanup after an operation has been completed.
	 * This function closes the session and commits the transaction.
	 * For usage details see beginDbAction()
	 * @see beginDbAction
	 */
	public void endDbAction() {
		// let database submit the operation's result and do the necessary cleanups
		database.endDbAction(session, transaction);
		
		// cleanup of the fields
		session.setReference(null);
		transaction.setReference(null);
	}
	
	/**
	 * Returns an optional of the session object.
	 * If the session is not initialized, returns optional of null;
	 * @return an optional of the session object.
	 * @see Optional
	 */
	public Optional<Session> getSession() {
		Session sessionToReturn = session.getReference();
		return Optional.fromNullable(sessionToReturn);
	}
	
	/**
	 * Check if the session object is initialized
	 * @return true of initialized, false otherwise
	 */
	public boolean isSessionInitialized() {
		if (session.getReference() == null) {
			return false;
		}
		
		return true;
	}
}
