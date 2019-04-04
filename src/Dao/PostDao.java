package Dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import Common.TransactionDao;
import Model.Blog;
import Model.Category;
import Model.Post;

public class PostDao extends TransactionDao<Post> {
	protected PostDao() {
		super(Post.class);
	}

	@SuppressWarnings("unchecked")
	public List<Post> findAll() {
		return transaction((em) -> {
			try {
				Query query = em.createNamedQuery("Post.findAll", Post.class);
				return (List<Post>) query.getResultList();
			} catch (NoResultException e) {
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Post> selectByBlog(Blog blog, int start, int count) {
		return transaction((em) -> {
			Query query = em.createQuery("SELECT p FROM Post p where p.isdeleted = false and p.blog = :blog order by p.date desc");
			query.setParameter("blog", blog);
			query.setFirstResult(start);
			query.setMaxResults(count);
			return (List<Post>) query.getResultList();
		});
	}

	@SuppressWarnings("unchecked")
	public List<Post> selectByCategory(Category category, int start, int count) {
		return transaction((em) -> {
			Query query = em.createQuery("SELECT p FROM Post p where p.isdeleted = false and p.category = :category order by p.date desc");
			query.setParameter("category", category);
			query.setFirstResult(start);
			query.setMaxResults(count);
			return (List<Post>) query.getResultList();
		});
	}

	@SuppressWarnings("unchecked")
	public List<Post> selectByCategoryId(String categoryId, int start, int count) {
		return transaction((em) -> {
			Query query = em.createQuery("SELECT p FROM Post p where p.isdeleted = false and p.categoryId = :categoryId order by p.date desc");
			query.setParameter("categoryId", categoryId);
			query.setFirstResult(start);
			query.setMaxResults(count);
			return (List<Post>) query.getResultList();
		});
	}

	public void deleteAll() {
		transaction((em) -> {
			String qy = "UPDATE Post SET isdeleted = true";
			Query query = em.createQuery(qy);
			query.executeUpdate();
		});
	}
}
