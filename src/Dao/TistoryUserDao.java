package Dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import Common.TransactionDao;
import Model.TistoryUser;

public class TistoryUserDao extends TransactionDao<TistoryUser> {
	protected TistoryUserDao() {
		super(TistoryUser.class);
	}

	@SuppressWarnings("unchecked")
	public List<TistoryUser> findAll() {
		return transaction((em) -> {
			try {
				Query query = em.createNamedQuery("TistoryUser.findAll", TistoryUser.class);
				return (List<TistoryUser>) query.getResultList();
			} catch (NoResultException e) {
				return null;
			}
		});
	}

	public TistoryUser getTistoryUser(String id, String userid) {
		return transaction((em) -> {
			try {
				String qy = "SELECT u FROM TistoryUser u WHERE u.id = :id AND u.userid = :userid AND u.isdeleted = false";
				Query query = em.createQuery(qy);
				query.setParameter("id", id);
				query.setParameter("userid", userid);
				return (TistoryUser) query.getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
		});
	}
}
