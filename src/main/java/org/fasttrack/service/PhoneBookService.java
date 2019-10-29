package org.fasttrack.service;

import org.fasttrack.domain.PhoneBook;
import org.fasttrack.persistance.PhoneBookRepository;
import org.fasttrack.transfer.CreatePhoneBookRequest;
import org.fasttrack.transfer.UpdatePhoneBookRequest;

import java.security.InvalidParameterException;
import java.util.List;

public class PhoneBookService {
    private PhoneBookRepository phoneBookRepository = new PhoneBookRepository();

    public void createPhoneBook(CreatePhoneBookRequest createPhoneBookRequest) {
        System.out.println("Creating phone book: " + createPhoneBookRequest);
        phoneBookRepository.createPhoneBook(createPhoneBookRequest);
    }

    public void updatePhoneBook(long id, UpdatePhoneBookRequest updatePhoneBookRequest) {
        System.out.println("Updating phone book: " + id + updatePhoneBookRequest);
        if (!returnIfIdMatches(id)) {
            throw new InvalidParameterException("No phone book matches the provided id: " + id);
        }
        phoneBookRepository.updatePhoneBook(id, updatePhoneBookRequest);
    }

    public void deletePhoneBook(long id) {
        System.out.println("Deleting phone book: " + id);
        if (!returnIfIdMatches(id)) {
            throw new InvalidParameterException("No phone book matches the provided id: " + id);
        }
        phoneBookRepository.deletePhoneBook(id);
    }

    public List<PhoneBook> readPhoneBook() {
        System.out.println("Retrieving phone books!");
        return phoneBookRepository.readPhoneBook();
    }

    private boolean returnIfIdMatches(long id) {
        boolean found = false;
        List<PhoneBook> phoneBooks = phoneBookRepository.readPhoneBook();
        for (PhoneBook phoneBook : phoneBooks) {
            if (phoneBook.getPhoneBookId() == id) {
                found = true;
                break;
            }
        }
        return found;
    }
}
