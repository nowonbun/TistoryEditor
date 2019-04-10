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

	@SuppressWarnings("unchecked")
	public List<Category> selectAll() {
		return transaction((em) -> {
			try {
				Query query = em.createQuery("SELECT c FROM Category c WHERE c.isdeleted = false order by c.categoryId asc");
				return (List<Category>) query.getResultList();
			} catch (NoResultException e) {
				return null;
			}
		});
	}

	public Category getCategoryByCategoryId(String id) {
		return transaction((em) -> {
			try {
				Query query = em.createQuery("SELECT c FROM Category c WHERE c.isdeleted = false and c.categoryId = :categoryId");
				query.setParameter("categoryId", id);
				return (Category) query.getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
		});
	}

	public Category getCategoryByIdx(int idx) {
		return transaction((em) -> {
			try {
				Query query = em.createQuery("SELECT c FROM Category c WHERE c.isdeleted = false and c.idx = :idx");
				query.setParameter("idx", idx);
				return (Category) query.getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
		});
	}

	public void deleteAll() {
		transaction((em) -> {
			String qy = "UPDATE Category SET isdeleted = true";
			Query query = em.createQuery(qy);
			query.executeUpdate();
		});
	}
}
