package com.filtro.skill.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.filtro.config.DBConnection;
import com.filtro.skill.domain.models.Skill;
import com.filtro.skill.domain.ports.out.SkillRepository;

public class SkillRepositoryImp implements SkillRepository{
    private final DBConnection dbConnection;

    public SkillRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Optional<Skill> saveNewSkill(Skill skill) {
        String query = "INSERT INTO skill(name)VALUES(?)";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, skill.getName());
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return Optional.of(skill);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error al guardar nueva skill "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Skill> findSkillByName(Skill skill) {
        String query = "SELECT id,name FROM skill WHERE name = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, skill.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(new Skill(resultSet.getInt("int"),resultSet.getString("name")));
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error al buscar skill "+e.getMessage(),e);
        }
    }
    
}
