package Common.IF;

import javax.persistence.EntityManager;

public interface EntityManagerCallable <V> {
	V run(EntityManager em);
}