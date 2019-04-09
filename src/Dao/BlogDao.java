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

	@SuppressWarnings("unchecked")
	public List<Blog> selectAll() {
		return transaction((em) -> {
			try {
				Query query = em.createQuery("SELECT b FROM Blog b where b.isdeleted = false order by b.blogid asc");
				return (List<Blog>) query.getResultList();
			} catch (NoResultException e) {
				return null;
			}
		});
	}

	public Blog selectByBlogId(String blogid) {
		return transaction((em) -> {
			try {
				Query query = em.createQuery("SELECT b.idx FROM Blog b where b.isdeleted = false and b.blogid = :blogid order by b.blogid asc");
				query.setParameter("blogid", blogid);
				return (Blog) query.getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
		});
	}

	public void deleteAll() {
		transaction((em) -> {
			String qy = "UPDATE Blog SET isdeleted = true";
			Query query = em.createQuery(qy);
			query.executeUpdate();
		});
	}
}
