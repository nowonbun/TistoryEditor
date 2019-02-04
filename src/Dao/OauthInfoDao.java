package Dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import Common.TransactionDao;
import Model.OauthInfo;

public class OauthInfoDao extends TransactionDao<OauthInfo> {
	protected OauthInfoDao() {
		super(OauthInfo.class);
	}

	@SuppressWarnings("unchecked")
	public List<OauthInfo> findAll() {
		return transaction((em) -> {
			try {
				Query query = em.createNamedQuery("OauthInfo.findAll", OauthInfo.class);
				return (List<OauthInfo>) query.getResultList();
			} catch (NoResultException e) {
				return null;
			}
		});
	}
}
