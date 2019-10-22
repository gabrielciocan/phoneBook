package org.fasttrack.persistance;

import org.fasttrack.domain.PhoneBook;
import org.fasttrack.transfer.CreatePhoneBookRequest;
import org.fasttrack.transfer.UpdatePhoneBookRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookRepository {

    public void createPhoneBook(CreatePhoneBookRequest createPhoneBookRequest){
        String sql = "INSERT INTO phone_books (name) VALUES (?)";
        try(Connection connection = DataBaseConfiguration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,createPhoneBookRequest.getName());
            preparedStatement.executeUpdate();
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the creation of the phone book ! " + e.getMessage());
        }
    }

    public void updatePhoneBook(long id, UpdatePhoneBookRequest updatePhoneBookRequest){
        String sql = "UPDATE phone_books SET name=? WHERE phone_book_id=?";
        try(Connection connection = DataBaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,updatePhoneBookRequest.getName());
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the update process of the phone book ! "+ id + e.getMessage());
        }
    }

    public void deletePhoneBook(long id){
        String sql = "DELETE FROM phone_books WHERE phone_book_id = ?";
        try(Connection connection = DataBaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the delete process of the phone book ! "+ id + e.getMessage());
        }
    }
    public List<PhoneBook> readPhoneBook(){
        String sql = "SELECT * FROM phone_books";
        List<PhoneBook> phoneBooks = new ArrayList<>();
        try(Connection connection = DataBaseConfiguration.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                PhoneBook phoneBook = new PhoneBook();
                phoneBook.setPhoneBookId(resultSet.getLong("phone_book_id"));
                phoneBook.setName(resultSet.getString("name"));

                phoneBooks.add(phoneBook);
            }
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the read process of a phone book ! "+ e.getMessage());
        }
        return phoneBooks;
    }

}
