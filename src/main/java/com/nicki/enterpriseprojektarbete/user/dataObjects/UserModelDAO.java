package com.nicki.enterpriseprojektarbete.user.dataObjects;

import com.nicki.enterpriseprojektarbete.user.UserModel;
import com.nicki.enterpriseprojektarbete.user.UserModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class UserModelDAO implements IUserModelDAO<UserModel>{

    private final UserModelRepo userModelRepo;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserModelDAO(UserModelRepo userModelRepo, JdbcTemplate jdbcTemplate) {
        this.userModelRepo = userModelRepo;
        this.jdbcTemplate = jdbcTemplate;

    }


    @Override
    public UserModel findUser(String username) {
        return userModelRepo.findByUsername(username);
    }

    @Override
    public void save(UserModel userModel) {
        userModelRepo.save(userModel);

    }

    @Override
    public void update(UserModel userModel) {

    }

    @Override
    public void delete(UserModel userModel) {

    }

    @Override
    public List<UserScoreDTO> findAllUserScores() {
        //String sql = "SELECT username, score FROM users";
        String sql = "SELECT username, score FROM users ORDER BY score DESC";
        List<UserScoreDTO> userScoreList = jdbcTemplate.query(sql, new RowMapper<UserScoreDTO>() {
            public UserScoreDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new UserScoreDTO(rs.getString("username"), rs.getInt("score"));
            }
        });
        return userScoreList;
    }
}
