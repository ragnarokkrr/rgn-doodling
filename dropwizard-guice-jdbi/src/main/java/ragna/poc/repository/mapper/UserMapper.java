package ragna.poc.repository.mapper;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import ragna.poc.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ragnarokkrr on 12/21/17.
 */
public class UserMapper implements ResultSetMapper<User> {

    @Override
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        User user = new User();
        user.setId(r.getLong("id"));
        user.setVersion(r.getInt("version"));
        user.setName(r.getString("name"));
        return user;
    }
}