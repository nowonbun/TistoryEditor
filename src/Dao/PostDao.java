package Dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import Common.TransactionDao;
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

	public void deleteAll() {
		transaction((em) -> {
			String qy = "UPDATE Post SET isdeleted = true";
			Query query = em.createQuery(qy);
			query.executeUpdate();
		});
	}
}
