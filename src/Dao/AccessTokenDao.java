package Dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import Common.TransactionDao;
import Model.AccessToken;

public class AccessTokenDao extends TransactionDao<AccessToken> {
	protected AccessTokenDao() {
		super(AccessToken.class);
	}

	@SuppressWarnings("unchecked")
	public List<AccessToken> findAll() {
		return transaction((em) -> {
			try {
				Query query = em.createNamedQuery("AccessToken.findAll", AccessToken.class);
				return (List<AccessToken>) query.getResultList();
			} catch (NoResultException e) {
				return null;
			}
		});
	}
}
