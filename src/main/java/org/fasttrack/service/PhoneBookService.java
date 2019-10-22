package org.fasttrack.service;

import org.fasttrack.persistance.PhoneBookRepository;
import org.fasttrack.transfer.CreatePhoneBookRequest;
import org.fasttrack.transfer.UpdatePhoneBookRequest;

public class PhoneBookService {
    private PhoneBookRepository phoneBookRepository = new PhoneBookRepository();

    public void createPhoneBook(CreatePhoneBookRequest createPhoneBookRequest){
        System.out.println("Creating phone book: " + createPhoneBookRequest);
        phoneBookRepository.createPhoneBook(createPhoneBookRequest);
    }
    public void updatePhoneBook(long id, UpdatePhoneBookRequest updatePhoneBookRequest){
        System.out.println("Updating phone book: " + id + updatePhoneBookRequest);
        phoneBookRepository.updatePhoneBook(id,updatePhoneBookRequest);
    }
    public void deletePhoneBook(long id){
        System.out.println("Deleting phone book: " + id);
        phoneBookRepository.deletePhoneBook(id);
    }
    public void readPhoneBook(){
        System.out.println("Retrieving phone books!");
        phoneBookRepository.readPhoneBook();
    }
}
