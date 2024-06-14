package Developer_Squad.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Developer_Squad.dto.User;

public class UserCrud {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("vinayaka");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public Developer_Squad.dto.User save(Developer_Squad.dto.User user) throws Exception {
		et.begin();
		em.persist(user);
		et.commit();
		return user;
	}

	public Developer_Squad.dto.User fetchByEmail(String email) throws Exception {
		Query query = em.createQuery("select a from User a where email=?1");
		query.setParameter(1, email);
		return (Developer_Squad.dto.User) query.getSingleResult();
	}

	public void update(Developer_Squad.dto.User user) {
		Developer_Squad.dto.User u =  em.find(Developer_Squad.dto.User.class, user.getId());
		if(u !=null) {
			et.begin();
			em.merge(user);
			et.commit();
		}
	}
}
