package Dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import Common.TransactionDao;
import Model.Blog;
import Model.BlogStatistic;

public class BlogStatisticDao extends TransactionDao<BlogStatistic> {
	protected BlogStatisticDao() {
		super(BlogStatistic.class);
	}

	@SuppressWarnings("unchecked")
	public List<BlogStatistic> findAll() {
		return transaction((em) -> {
			try {
				Query query = em.createNamedQuery("BlogStatistic.findAll", Blog.class);
				return (List<BlogStatistic>) query.getResultList();
			} catch (NoResultException e) {
				return null;
			}
		});
	}

	public void deleteAll() {
		transaction((em) -> {
			String qy = "UPDATE BlogStatistic SET isdeleted = true";
			Query query = em.createQuery(qy);
			query.executeUpdate();
		});
	}
}
