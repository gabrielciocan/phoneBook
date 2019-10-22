package org.fasttrack.persistance;

import org.fasttrack.domain.Contact;
import org.fasttrack.transfer.CreateContactRequest;
import org.fasttrack.transfer.UpdateContactRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    public void createContact(CreateContactRequest createContactRequest, long phoneBookId){
        String sql = "INSERT INTO contacts (first_name, last_name, phone_number, phone_book_id) VALUES (?, ?, ?, ?)";
        try(Connection connection = DataBaseConfiguration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,createContactRequest.getFirstName());
            preparedStatement.setString(2,createContactRequest.getLastName());
            preparedStatement.setString(3,createContactRequest.getPhoneNumber());
            preparedStatement.setLong(4,phoneBookId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the creation of a contact ! " + e.getMessage());
        }
    }

    public void updateContact(long id, UpdateContactRequest updateContactRequest){
        String sql = "UPDATE contacts SET first_name=?, last_name=?, phone_number=?, phone_book_id=? WHERE contact_id = ?";
        String prepSql = "UPDATE contacts SET ";
        boolean fist_name = false;
        boolean last_name = false;
        boolean phone_number = false;
        boolean phone_book_id = false;
        try(Connection connection = DataBaseConfiguration.getConnection();
        ){
            PreparedStatement preparedStatement;
            int parameters = 1;
            if(updateContactRequest.getFirstName() != null){
                parameters ++;
                prepSql = prepSql + "first_name=?";
                fist_name = true;
            }
            if(updateContactRequest.getLastName() != null){
                if(parameters != 1)
                    prepSql = prepSql + ", last_name = ?";
                else
                    prepSql = prepSql + "last_name = ?";
                parameters ++;
                last_name = true;
            }
            if(updateContactRequest.getPhoneNumber() != null){
                if(parameters != 1)
                    prepSql = prepSql +", phone_number = ?";
                else
                    prepSql = prepSql +"phone_number = ?";
                parameters ++;
                phone_number = true;
            }
            if(!(updateContactRequest.getPhoneBookId() <= 0)){
                if(parameters != 1)
                    prepSql = prepSql +", phone_book_id = ?";
                else
                    prepSql = prepSql +"phone_book_id = ?";
                parameters++;
                phone_book_id = true;
            }
            prepSql = prepSql + " WHERE contact_id = ?";
            preparedStatement = connection.prepareStatement(prepSql);
            for(int i = 1; i != parameters; i++){
                if(fist_name){
                    preparedStatement.setString(i,updateContactRequest.getFirstName());
                    fist_name = false;
                    continue;
                }
                if(last_name){
                    preparedStatement.setString(i,updateContactRequest.getLastName());
                    last_name = false;
                    continue;
                }
                if(phone_number){
                    preparedStatement.setString(i,updateContactRequest.getPhoneNumber());
                    phone_number = false;
                    continue;
                }
                if(phone_book_id){
                    preparedStatement.setLong(i,updateContactRequest.getPhoneBookId());
                    phone_book_id = false;
                }
            }
            preparedStatement.setLong(parameters,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the updating process of a contact ! "+id + e.getMessage());
        }
    }
    public void deleteContact(long id){
        String sql = "DELETE FROM contacts WHERE contact_id=?";
        try(Connection connection = DataBaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the delete process of a contact ! "+id + e.getMessage());
        }
    }
    public List<Contact> readContact(){
        String sql = "SELECT * FROM contacts";
        List<Contact> contactList = new ArrayList<>();

        try(Connection connection = DataBaseConfiguration.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Contact contact = new Contact();
                contact.setContactId(resultSet.getLong("contact_id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setPhoneNumber(resultSet.getString("phone_number"));
                contact.setPhoneBookId(resultSet.getLong("phone_book_id"));

                contactList.add(contact);
            }
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the read process of a contact ! "+ e.getMessage());
        }
        return contactList;
    }
    public List<Contact> readContactsFromPhoneBook(long phoneBookId){
        String sql = "SELECT * FROM contacts WHERE phone_book_id = ?";
        List<Contact> contactList = new ArrayList<>();

        try(Connection connection = DataBaseConfiguration.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,phoneBookId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Contact contact = new Contact();
                contact.setContactId(resultSet.getLong("contact_id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setPhoneNumber(resultSet.getString("phone_number"));
                contact.setPhoneBookId(resultSet.getLong("phone_book_id"));

                contactList.add(contact);
            }
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the read process of a contact ! "+ e.getMessage());
        }
        return contactList;
    }
    public void deleteContactsByFirstName(String syntax){
        String sql = "DELETE FROM contacts WHERE first_name LIKE ?";
        try(Connection connection = DataBaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,syntax);
            preparedStatement.executeUpdate();
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the delete process of multiple contacts ! "+ e.getMessage());
        }
    }
}
