package com.novo.personalproject.dao;

import com.novo.personalproject.model.entity.Gender;
import com.novo.personalproject.model.entity.Role;
import com.novo.personalproject.model.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setBirthDate(rs.getDate("birth_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setGender(Gender.valueOf(rs.getString("gender")));
        return user;
    }
}
