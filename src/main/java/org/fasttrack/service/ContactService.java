package org.fasttrack.service;

import org.fasttrack.domain.Contact;
import org.fasttrack.persistance.ContactRepository;
import org.fasttrack.transfer.CreateContactRequest;
import org.fasttrack.transfer.UpdateContactRequest;

import java.util.List;

public class ContactService {
    private ContactRepository contactRepository = new ContactRepository();

    public void createContact(CreateContactRequest createContactRequest){
        System.out.println("Creating contact: " + createContactRequest);
        contactRepository.createContact(createContactRequest);
    }
    public void updateContact(long id, UpdateContactRequest updateContactRequest){
        System.out.println("Updating contact: " + id + updateContactRequest);
        contactRepository.updateContact(id,updateContactRequest);
    }
    public void deleteContact(long id){
        System.out.println("Deleting contact: " + id);
        contactRepository.deleteContact(id);
    }
    public List<Contact> readContact(){
        System.out.println("Retrieving contacts !");
        return contactRepository.readContact();
    }
    public Contact readContact(long id) {
        System.out.println("Retrieving contact with id: " + id);
        return contactRepository.readContact(id);
    }
    public List<Contact> readContact(String partialName){
        System.out.println("Retrieving contacts using partial name: " + partialName);
        return contactRepository.readContacts(partialName);
    }
    public void deleteContacts(long[] ids){
        for(long id:ids){
            System.out.println("Deleting contact: " + id);
        }
        contactRepository.deleteContacts(ids);
    }
    public List<Contact> readContactsFromPhoneBook(long phoneBookId){
        System.out.println("Retrieving contacts from phonebook: " + phoneBookId);
        return contactRepository.readContactsFromPhoneBook(phoneBookId);
    }
}
