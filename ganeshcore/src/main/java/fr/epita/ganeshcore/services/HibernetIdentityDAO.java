
package fr.epita.ganeshcore.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import fr.epita.ganeshcore.datamodel.Identity;


/**
 * @author ganesh
 * HibernateDAO is handling all the database related queries
 * For both classes Users and Identities
 */
@Repository
public class HibernetIdentityDAO {
	@Inject
	private SessionFactory sessionFactory;
	
	
	/**
	 * @param identity
	 * It is used for save a identity
	 */

	public void save(Identity identity) {

		final Session session = sessionFactory.openSession();
		final Transaction tx = session.beginTransaction();
		session.save(identity);
		tx.commit();
        session.close();
	}
	
	/**
	 * @param identity
	 * update identity.displayName, identity.email, identity.birthDate
	 * By using identity.id
	 */
	public void update(Identity identity) {

		final Session session = sessionFactory.openSession();
		final Transaction tx = session.beginTransaction();
		System.out.println("into update");
		session.update(identity);
		tx.commit();
        session.close();
	}
	
	/**
	 * @param identity
	 * Delete identity
	 * By using identity.id
	 */
	public void delete(Identity identity) {

		final Session session = sessionFactory.openSession();
		final Transaction tx = session.beginTransaction();
		session.delete(identity);
		tx.commit();
        session.close();
	}
	
	/**
	 *  @param criteria
	 *  identity search
	 *  Search  identity by displayName and email
	 */

	public Collection<Identity> search(Identity criteria) {
		List<Identity> identitiesList = new ArrayList<>();
		final Session session = sessionFactory.openSession();
        identitiesList=session.createQuery("from Identity WHERE IDENTITY_DISPLAY_NAME='"+criteria.getDisplayName() +"' AND IDENTITY_EMAIL='"+criteria.getEmail() +"'",Identity.class).list();
		return identitiesList;
		
	}
	
	/**
	 * 
	 * @param criteria
	 *  identity searchAll
	 * Search all the identity
	 */
	public Collection<Identity> searchAll(Identity criteria) {
		List<Identity> identitiesList = new ArrayList<>();
		final Session session = sessionFactory.openSession();
		identitiesList = session.createQuery("from Identity", Identity.class).list();
        return identitiesList;
	}
	
	/**
	 * 
	 * @param id
	 * identity getById
	 * 
	 */
	public Identity getById(long id) {
		final Session session = sessionFactory.openSession();
		
		return session.get(Identity.class, id);
	}
	
}
