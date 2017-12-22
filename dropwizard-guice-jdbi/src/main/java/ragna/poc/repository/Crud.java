package ragna.poc.repository;

import ragna.poc.model.IdEntity;
import ru.vyarus.guicey.jdbi.tx.InTransaction;

import java.util.ConcurrentModificationException;

/**
 * Created by ragnarokkrr on 12/21/17.
 */
public abstract class Crud<T extends IdEntity> {

    @InTransaction
    public T save(final T entry) {
        // hibernate-like optimistic locking mechanism: provided entity must have the same version as in database
        if (entry.getId() == 0) {
            entry.setVersion(1);
            entry.setId(insert(entry));
        } else {
            final int ver = entry.getVersion();
            entry.setVersion(ver + 1);
            if (update(entry) == 0) {
                throw new ConcurrentModificationException(String.format(
                        "Concurrent modification for object %s %s version %s",
                        entry.getClass().getName(), entry.getId(), ver));
            }
        }
        return entry;
    }

    public abstract long insert(T entry);

    public abstract int update(T entry);
}