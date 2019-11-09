package prakhar.repo;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import prakhar.model.Entity;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class AbstractRepo<E extends Entity> extends AbstractDAO<E> {
    public AbstractRepo(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<E> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public void delete(Entity entity) {
        if (entity == null) {
            return;
        }
        currentSession().delete(entity);
        currentSession().flush(); // this is needed because Hibernate does not execute delete statements until 1) commit 2) query 3) flush
    }

    public void delete(List<? extends Entity> entities) {
        if (entities == null) {
            return;
        }
        if (entities.size() == 0) {
            return;
        }
        entities.forEach(currentSession()::delete);
        currentSession().flush(); // this is needed because Hibernate does not execute delete statements until 1) commit 2) query 3) flush
    }

    public <T extends Entity> T save(T entity) {
        currentSession().saveOrUpdate(requireNonNull(entity));
        return entity;
    }

    public void flush() {
        currentSession().flush();
    }
}
