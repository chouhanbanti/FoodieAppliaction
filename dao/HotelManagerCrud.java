package Developer_Squad.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Developer_Squad.dto.HotelManager;

public class HotelManagerCrud {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("vinayaka");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public void save(Developer_Squad.dto.HotelManager hotelManager) throws Exception {
		et.begin();
		em.persist(hotelManager);
		et.commit();
	}

	public Developer_Squad.dto.HotelManager fetchByEmail(String email) throws Exception {
		Query query = em.createQuery("select a from HotelManager a where email=?1");
		query.setParameter(1, email);
		return (Developer_Squad.dto.HotelManager) query.getSingleResult();
	}
	public Developer_Squad.dto.HotelManager update(Developer_Squad.dto.HotelManager hotelManager) {
		Developer_Squad.dto.HotelManager m = em.find(Developer_Squad.dto.HotelManager.class, hotelManager.getId());
		if(m !=null) {
			et.begin();
			em.merge(hotelManager);
			et.commit();
			return hotelManager;
		}
		return null;
	}
}
