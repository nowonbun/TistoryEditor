package Dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import Common.TransactionDao;
import Model.Content;

public class ContentDao extends TransactionDao<Content> {
	protected ContentDao() {
		super(Content.class);
	}

	@SuppressWarnings("unchecked")
	public List<Content> findAll() {
		return transaction((em) -> {
			try {
				Query query = em.createNamedQuery("Content.findAll", Content.class);
				return (List<Content>) query.getResultList();
			} catch (NoResultException e) {
				return null;
			}
		});
	}
}
