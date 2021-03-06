package org.fasttrack.persistance;

import org.fasttrack.domain.Contact;
import org.fasttrack.domain.PhoneBook;
import org.fasttrack.transfer.CreateContactRequest;
import org.fasttrack.transfer.UpdateContactRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    public void createContact(CreateContactRequest createContactRequest){
        String sql = "INSERT INTO contacts (first_name, last_name, phone_number, phone_book_id) VALUES (?, ?, ?, ?)";
        try(Connection connection = DataBaseConfiguration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,createContactRequest.getFirstName());
            preparedStatement.setString(2,createContactRequest.getLastName());
            preparedStatement.setString(3,createContactRequest.getPhoneNumber());
            preparedStatement.setLong(4,createContactRequest.getPhoneBookId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the creation of a contact ! " + e.getMessage());
        }
    }

    public void updateContact(long id, UpdateContactRequest updateContactRequest){
        String sql = "UPDATE contacts SET first_name=?, last_name=?, phone_number=?, phone_book_id=? WHERE contact_id = ?";
        String prepSql = "UPDATE contacts SET ";
        boolean firstName = false;
        boolean lastName = false;
        boolean phoneNumber = false;
        boolean phoneBookId = false;
        try(Connection connection = DataBaseConfiguration.getConnection();
        ){
            PreparedStatement preparedStatement;
            int parameters = 1;
            if(updateContactRequest.getFirstName() != null){
                parameters ++;
                prepSql = prepSql + "first_name=?";
                firstName = true;
            }
            if(updateContactRequest.getLastName() != null){
                if(parameters != 1)
                    prepSql = prepSql + ", last_name = ?";
                else
                    prepSql = prepSql + "last_name = ?";
                parameters ++;
                lastName = true;
            }
            if(updateContactRequest.getPhoneNumber() != null){
                if(parameters != 1)
                    prepSql = prepSql +", phone_number = ?";
                else
                    prepSql = prepSql +"phone_number = ?";
                parameters ++;
                phoneNumber = true;
            }
            if(!(updateContactRequest.getPhoneBookId() <= 0)){
                if(parameters != 1)
                    prepSql = prepSql +", phone_book_id = ?";
                else
                    prepSql = prepSql +"phone_book_id = ?";
                parameters++;
                phoneBookId = true;
            }
            prepSql = prepSql + " WHERE contact_id = ?";
            preparedStatement = connection.prepareStatement(prepSql);
            for(int i = 1; i != parameters; i++){
                if(firstName){
                    preparedStatement.setString(i,updateContactRequest.getFirstName());
                    firstName = false;
                    continue;
                }
                if(lastName){
                    preparedStatement.setString(i,updateContactRequest.getLastName());
                    lastName = false;
                    continue;
                }
                if(phoneNumber){
                    preparedStatement.setString(i,updateContactRequest.getPhoneNumber());
                    phoneNumber = false;
                    continue;
                }
                if(phoneBookId){
                    preparedStatement.setLong(i,updateContactRequest.getPhoneBookId());
                    phoneBookId = false;
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
    public List<Object> readContactAndPhoneBook(){
        String sql = "SELECT contacts.*, phone_books.name FROM contacts JOIN phone_books ON contacts.phone_book_id = phone_books.phone_book_id ";
        List<Object> list = new ArrayList<>();
        try(Connection connection = DataBaseConfiguration.getConnection();
        Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Contact contact = new Contact();
                PhoneBook phoneBook = new PhoneBook();

                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setPhoneNumber(resultSet.getString("phone_number"));
                contact.setContactId(resultSet.getLong("contact_id"));
                contact.setPhoneBookId(resultSet.getLong("phone_book_id"));

                phoneBook.setPhoneBookId(resultSet.getLong("phone_book_id"));
                phoneBook.setName(resultSet.getString("name"));

                list.add(contact + ", " +phoneBook);
            }
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the read process of contacts and phone books that have the same id ! "+ e.getMessage());
        }
        return list;
    }
    public void deleteContacts(long[] ids){
        String sql = "DELETE FROM contacts WHERE contact_id IN (";
        for(int i = 0; i < ids.length;i++){
            if(i < (ids.length -1)){
                sql = sql + "?,";
            }
            else{
                sql = sql + "?)";
            }
        }
        try(Connection connection = DataBaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            for(int i = 0; i < ids.length;i++){
                preparedStatement.setLong(i+1,ids[i]);
            }
            preparedStatement.executeUpdate();
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the delete process of multiple contacts using an array of ids ! "+ e.getMessage());
        }
    }
    public List<Contact> readContacts(String partialName){
        String sql = "SELECT * FROM contacts WHERE first_name LIKE ? OR last_name LIKE ?";
        partialName = "%" + partialName + "%";
        partialName = partialName.replace("\"","");
        List<Contact> contactList = new ArrayList<>();
        try(Connection connection = DataBaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,partialName);
            preparedStatement.setString(2,partialName);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            System.out.println("An error has occured during the retrieve process of multiple contacts by partial name ! "+ e.getMessage());
        }
        return contactList;
    }
    public Contact readContact(long id){
        String sql = "SELECT * FROM contacts WHERE contact_id = ?";
        Contact contact = new Contact();
        try(Connection connection = DataBaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                contact.setContactId(resultSet.getLong("contact_id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setPhoneNumber(resultSet.getString("phone_number"));
                contact.setPhoneBookId(resultSet.getLong("phone_book_id"));
            }
        }catch (SQLException | IOException | ClassNotFoundException e){
            System.out.println("An error has occured during the retrieve process of contact with id: " + id+ e.getMessage());
        }
        return contact;
    }
}
