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

	@SuppressWarnings("unchecked")
	public List<Attachment> selectAll() {
		return transaction((em) -> {
			Query query = em.createQuery("SELECT a FROM Attachment a where a.isdeleted = false order by a.idx desc");
			return (List<Attachment>) query.getResultList();
		});
	}

	public byte[] getFileData(int idx) {
		return transaction((em) -> {
			Query query = em.createQuery("SELECT a.data FROM Attachment a where a.idx=:idx");
			query.setParameter("idx", idx);
			return (byte[]) query.getSingleResult();
		});
	}

}
