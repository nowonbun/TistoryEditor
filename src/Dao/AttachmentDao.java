package Dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import Common.TransactionDao;
import Model.Attachment;

public class AttachmentDao extends TransactionDao<Attachment> {
	protected AttachmentDao() {
		super(Attachment.class);
	}

	@SuppressWarnings("unchecked")
	public List<Attachment> findAll() {
		return transaction((em) -> {
			try {
				Query query = em.createNamedQuery("Attachment.findAll", Attachment.class);
				return (List<Attachment>) query.getResultList();
			} catch (NoResultException e) {
				return null;
			}
		});
	}
}
