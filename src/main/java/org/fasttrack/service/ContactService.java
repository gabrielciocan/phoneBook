package org.fasttrack.service;

import org.fasttrack.persistance.ContactRepository;
import org.fasttrack.transfer.CreateContactRequest;
import org.fasttrack.transfer.UpdateContactRequest;

public class ContactService {
    private ContactRepository contactRepository = new ContactRepository();

    public void createContact(CreateContactRequest createContactRequest,long phoneBookId){
        System.out.println("Creating contact: " + createContactRequest);
        contactRepository.createContact(createContactRequest,phoneBookId);
    }
    public void updateContact(long id, UpdateContactRequest updateContactRequest){
        System.out.println("Updating contact: " + id + updateContactRequest);
        contactRepository.updateContact(id,updateContactRequest);
    }
    public void deleteContact(long id){
        System.out.println("Deleting contact: " + id);
        contactRepository.deleteContact(id);
    }
    public void readContact(){
        System.out.println("Retrieving contacts !");
        contactRepository.readContact();
    }
    public void deleteContacts(long[] ids){
        for(long id:ids){
            System.out.println("Deleting contact: " + id);
        }
        contactRepository.deleteContacts(ids);
    }
}
