package Dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import Common.TransactionDao;
import Model.Category;

public class CategoryDao extends TransactionDao<Category> {
	protected CategoryDao() {
		super(Category.class);
	}

	@SuppressWarnings("unchecked")
	public List<Category> findAll() {
		return transaction((em) -> {
			try {
				Query query = em.createNamedQuery("Category.findAll", Category.class);
				return (List<Category>) query.getResultList();
			} catch (NoResultException e) {
				return null;
			}
		});
	}
}
