package Dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import Common.TransactionDao;
import Model.Blog;

public class BlogDao extends TransactionDao<Blog> {
	protected BlogDao() {
		super(Blog.class);
	}

	@SuppressWarnings("unchecked")
	public List<Blog> findAll() {
		return transaction((em) -> {
			try {
				Query query = em.createNamedQuery("Blog.findAll", Blog.class);
				return (List<Blog>) query.getResultList();
			} catch (NoResultException e) {
				return null;
			}
		});
	}
}
