package org.fasttrack.service;

import org.fasttrack.domain.Contact;
import org.fasttrack.persistance.ContactRepository;
import org.fasttrack.transfer.CreateContactRequest;
import org.fasttrack.transfer.UpdateContactRequest;

import java.security.InvalidParameterException;
import java.util.List;

public class ContactService {
    private ContactRepository contactRepository = new ContactRepository();

    public void createContact(CreateContactRequest createContactRequest) {
        System.out.println("Creating contact: " + createContactRequest);
        contactRepository.createContact(createContactRequest);
    }

    public void updateContact(long id, UpdateContactRequest updateContactRequest) {
        System.out.println("Updating contact: " + id + updateContactRequest);
        if (!returnIfContactIdMatches(id)) {
            throw new InvalidParameterException("No contact matches the provided id: " + id);
        }
        contactRepository.updateContact(id, updateContactRequest);
    }

    public void deleteContact(long id) {
        System.out.println("Deleting contact: " + id);
        if (!returnIfContactIdMatches(id)) {
            throw new InvalidParameterException("No contact matches the provided id: " + id);
        }
        contactRepository.deleteContact(id);
    }

    public List<Contact> readContact() {
        System.out.println("Retrieving contacts !");
        return contactRepository.readContact();
    }

    public Contact readContact(long id) {
        System.out.println("Retrieving contact with id: " + id);
        if (!returnIfContactIdMatches(id)) {
            throw new InvalidParameterException("No contact matches the provided id: " + id);
        }
        return contactRepository.readContact(id);
    }

    public List<Contact> readContact(String partialName) {
        System.out.println("Retrieving contacts using partial name: " + partialName);
        List<Contact> contactsList = contactRepository.readContacts(partialName);
        if (contactsList.size() == 0) {
            throw new InvalidParameterException("No contact matches the provided partial name: " + partialName);
        }
        return contactsList;
    }

    public void deleteContacts(long[] ids) {
        for (long id : ids) {
            System.out.println("Deleting contact: " + id);
        }
        contactRepository.deleteContacts(ids);
    }

    public List<Contact> readContactsFromPhoneBook(long phoneBookId) {
        System.out.println("Retrieving contacts from phonebook: " + phoneBookId);
        List<Contact> contactsList = contactRepository.readContactsFromPhoneBook(phoneBookId);
        if (contactsList.size() == 0) {
            throw new InvalidParameterException("No contact matches the provided phone book id: " + phoneBookId);
        }
        return contactRepository.readContactsFromPhoneBook(phoneBookId);
    }

    private boolean returnIfContactIdMatches(long id) {
        Contact contact = contactRepository.readContact(id);
        if (contact.getFirstName() == null && contact.getLastName() == null && contact.getPhoneNumber() == null &&
                contact.getPhoneBookId() == 0 && contact.getContactId() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
