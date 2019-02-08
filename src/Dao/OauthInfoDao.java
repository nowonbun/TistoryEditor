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

	public OauthInfo getOauthInfo(String clientId, String clientSecret, String redirectUrl) {
		return transaction((em) -> {
			try {
				String qy = "SELECT o FROM OauthInfo o WHERE o.clientId = :clientId AND o.clientSecret = :clientSecret AND o.redirectUrl = :redirectUrl";
				Query query = em.createQuery(qy);
				query.setParameter("clientId", clientId);
				query.setParameter("clientSecret", clientSecret);
				query.setParameter("redirectUrl", redirectUrl);
				return (OauthInfo) query.getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
		});
	}
}
