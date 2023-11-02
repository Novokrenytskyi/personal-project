package com.novo.personalproject.model.dao;

import com.novo.personalproject.model.entity.User;
import com.novo.personalproject.model.entity.Gender;
import com.novo.personalproject.model.entity.Role;
import com.novo.personalproject.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_SQL = """
            SELECT id,
            first_name,
            last_name,
            age, 
            role,
            gender
            FROM users;
            """;
    private static final String FIND_BY_ID_SQL = """
            SELECT id,
            first_name,
            last_name,
            age, 
            role,
            gender
            FROM users
            WHERE id = ?;
            """;

    private static final String SAVE_SQL = """
            INSERT INTO users(
            first_name, 
            last_name, 
            gender, 
            age, 
            role
            )
            VALUES (?, ?, ?, ?, ?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE users
            SET first_name = ?,
            last_name = ?,
            gender = ?,
            age = ?,
            role = ?
            WHERE id = ?;          
            """;

    private static final String DELETE_SQL = """
            DELETE FROM users
            WHERE id = ?;
            """;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public List<User> findAll() {
//        try (var connection = ConnectionManager.open();
//             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
//            var resultSet = statement.executeQuery();
//            ArrayList<User> users = new ArrayList<>();
//            while (resultSet.next()) {
//                users.add(build(resultSet));
//            }
//            return users;
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//    }

    public List<User> findAll() {
        System.out.println();
        List<User> users = jdbcTemplate.query(FIND_ALL_SQL, new UserMapper());
        return users;
    }

//    public Optional<User> findById(Long id) {
//        try (var connection = ConnectionManager.open();
//             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
//            statement.setLong(1, id);
//            var resultSet = statement.executeQuery();
//            User result = null;
//            if (resultSet.next()) {
//                result = new User(resultSet.getLong("id"),
//                        resultSet.getString("first_name"),
//                        resultSet.getString("last_name"),
//                        Gender.valueOf(resultSet.getString("gender")),
//                        resultSet.getInt("age"),
//                        Role.valueOf(resultSet.getString("role"))
//                );
//
//            }
//            return Optional.ofNullable(result);
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//    }

    public Optional<User> findById(Long id) {
        return jdbcTemplate.query(FIND_BY_ID_SQL, new Object[]{id}, new UserMapper())
                .stream()
                .findFirst();
    }

//    public User save(User user) {
//        try (var connection = ConnectionManager.open();
//             var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
//            statement.setString(1, user.getFirstName());
//            statement.setString(2, user.getLastName());
//            statement.setString(3, user.getGender().name());
//            statement.setInt(4, user.getAge());
//            statement.setString(5, user.getRole().name());
//            statement.executeUpdate();
//
//            var generatedKey = statement.getGeneratedKeys();
//            if (generatedKey.next()) {
//                user.setId(generatedKey.getLong("id"));
//            }
//            return user;
//
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//    }

    public int save(User user) {
       return jdbcTemplate.update(SAVE_SQL, user.getFirstName(),
                user.getLastName(),
                user.getGender().name(),
                user.getAge(),
                user.getRole().name());
    }

//    public void update(User user) {
//        try (var connection = ConnectionManager.open();
//             var statement = connection.prepareStatement(UPDATE_SQL)) {
//            statement.setString(1, user.getFirstName());
//            statement.setString(2, user.getLastName());
//            statement.setString(3, user.getGender().name());
//            statement.setInt(4, user.getAge());
//            statement.setString(5, user.getRole().name());
//            statement.setLong(6, user.getId());
//
//            statement.executeUpdate();
//
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//    }

    public int update(User user) {
        return jdbcTemplate.update(UPDATE_SQL, user.getFirstName(),
                user.getLastName(),
                user.getGender().name(),
                user.getAge(),
                user.getRole().name());
    }

//    public boolean delete(Long id) {
//        try (var connection = ConnectionManager.open();
//             var statement = connection.prepareStatement(DELETE_SQL)) {
//            statement.setLong(1, id);
//
//            return statement.executeUpdate() > 0;
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//    }

    public void delete(Long id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

//    private User build(ResultSet resultSet) throws SQLException {
//        return new User(resultSet.getLong("id"),
//                resultSet.getString("first_name"),
//                resultSet.getString("last_name"),
//                Gender.valueOf(resultSet.getString("gender")),
//                resultSet.getInt("age"),
//                Role.valueOf(resultSet.getString("role")));
//    }

}
