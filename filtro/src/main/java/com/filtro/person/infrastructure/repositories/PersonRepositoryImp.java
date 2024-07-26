package com.filtro.person.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.text.html.Option;

import com.filtro.config.DBConnection;
import com.filtro.person.domain.models.Person;
import com.filtro.person.domain.ports.out.PersonRepository;

public class PersonRepositoryImp implements PersonRepository{
    private final DBConnection dbConnection;

    public PersonRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Optional<Person> savePerson(Person person) {
        String query = "INSERT INTO persons(name,lastname,idcity,address,age,email,idgender)VALUES(?,?,?,?,?,?,?)";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getLastname());
            preparedStatement.setInt(3, person.getIdCity());
            preparedStatement.setString(4, person.getAddress());
            preparedStatement.setInt(5, person.getAge());
            preparedStatement.setString(6, person.getEmail());
            preparedStatement.setInt(7, person.getIdgender());
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return Optional.of(person);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("fallo al agregar persona a db "+e.getMessage(),e);
        }
    }

    @Override
    public List<String> findPersonBySkill(int idSkill) {
        String query = "SELECT P.id,P.name,P.lastname FROM persons P INNER JOIN persons_skill PK ON P.id = PK.iperson WHERE PK.idskill = ?";
        List<String> personList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement  = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, idSkill);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                personList.add(resultSet.getString("P.id")+" "+resultSet.getString("P.name")+" "+resultSet.getString("P.lastname"));
            }
            return personList;
            
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("failed trying to find person "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Person> updatePerson(Person person) {
        String query = "UPDATE persons SET name = ?,lastname=?,idcity=?,address=?,age=?,email=?,idgender=? WHERE id =?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getLastname());
            preparedStatement.setInt(3, person.getIdCity());
            preparedStatement.setString(4,person.getAddress());
            preparedStatement.setInt(5, person.getAge());
            preparedStatement.setString(6,person.getEmail());
            preparedStatement.setInt(7,person.getIdgender());
            preparedStatement.setInt(8, person.getId());
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return Optional.of(person);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed trying to update person "+e.getMessage(),e);
        }
    }

    @Override
    public boolean deletePerson(int id) {
        String query = "DELETE FROM persons WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, id);
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to trying to delete "+e.getMessage(),e);
        }
    }

    @Override
    public List<String> getCities() {
        String query = "SELECT id,name FROM city";
        List<String> cityList = new ArrayList<>(); 
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                cityList.add(resultSet.getInt("id")+" "+resultSet.getString("name"));
            }
            return cityList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed trying to recovery cities "+e.getMessage(),e);
        }
    }

    @Override
    public List<String> getGender() {
        String query = "SELECT id,name FROM gender";
        List<String> genderList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                genderList.add(resultSet.getInt("id")+" "+resultSet.getString("name"));
            }
            return genderList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed trying to recovery genders "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Person> getPersonById(int id) {
        String query = "SELECT id,name,lastname,idcity,address,age,email,idgender FROM persons WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Person person = new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("lastname"),
                    resultSet.getInt("idcity"),
                    resultSet.getString("address"),
                    resultSet.getInt("age"),
                    resultSet.getString("email"),
                    resultSet.getInt("idgender")
                );
                return Optional.of(person);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error al buscar persona "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Person> updatePersonSkill(Person person, int idSkill) {
        String query = "INSERT INTO persons_skill(iperson,idskill) VALUES (?,?)";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setInt(2, idSkill);
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff ==1 ){
                return Optional.of(person);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("failed to add person skill "+e.getMessage(),e);
        }
    }
    
}
