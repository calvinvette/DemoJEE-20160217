package com.weasley.data;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Singleton
public class CustomerDAO {
	@PersistenceContext
	private static EntityManagerFactory entityManagerFactory;

	@PersistenceUnit
	private EntityManager entityManager;

	public static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("DemoWeasley");
		}
		return entityManagerFactory;
	}

	public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		CustomerDAO.entityManagerFactory = entityManagerFactory;
	}

	public EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = getEntityManagerFactory().createEntityManager();
		}
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Customer findById(Long customerId) {
		return getEntityManager().find(Customer.class, customerId);
	}

	public List<Customer> findAll() {
		return getEntityManager()
				.createNamedQuery(Customer.FIND_ALL, Customer.class)
				.getResultList();
	}
	
	public List<Customer> findByLastName(String lastName) {
		return getEntityManager()
				.createNamedQuery(Customer.FIND_BY_LAST_NAME, Customer.class)
				.setParameter("lastName", lastName)
				.getResultList();
	}

	public Customer insert(Customer customer) {
		System.out.println("INSERTING CUSTOMER: " + customer);
		customer.setCustomerId(null);
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(customer);
		getEntityManager().getTransaction().commit();
		return customer;
	}

	public Customer delete(Customer customer) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(customer);
		getEntityManager().remove(customer);
		getEntityManager().getTransaction().commit();
		return customer;
	}

	public Customer update(Customer customer) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(customer);
		getEntityManager().getTransaction().commit();		
		return customer;
	}

}
