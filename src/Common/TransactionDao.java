package Common;

import java.io.Serializable;

public class TransactionDao<T extends Serializable> extends AbstractDao<T> {

	protected TransactionDao(Class<T> clazz) {
		super(clazz);
	}
}
