package ragna.poc.repository;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import ragna.poc.model.User;
import ragna.poc.repository.mapper.bind.UserBind;
import ragna.poc.service.RandomNameGenerator;
import ru.vyarus.guicey.jdbi.installer.repository.JdbiRepository;
import ru.vyarus.guicey.jdbi.tx.InTransaction;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ragnarokkrr on 12/21/17.
 */
@JdbiRepository
@InTransaction
public abstract class UserRepository extends Crud<User> {

    // have to use field injection because class is still used by dbi (which is no aware of guice) for proxy creation
    @Inject
    private RandomNameGenerator generator;

    // sample of hybrid method in repository, using injected service
    public User createRandomUser() {
        final User user = new User();
        user.setName(generator.generateName());
        save(user);
        return user;
    }

    @Override
    @SqlUpdate("insert into users (name, version) values (:name, :version)")
    @GetGeneratedKeys
    public abstract long insert(@UserBind User entry);

    @SqlUpdate("update users set version=:version, name=:name where id=:id and version=:version - 1")
    @Override
    public abstract int update(@UserBind User entry);

    @SqlQuery("select * from users")
    public abstract List<User> findAll();

    @SqlQuery("select * from users where name = :name")
    public abstract User findByName(@Bind("name") String name);
}
