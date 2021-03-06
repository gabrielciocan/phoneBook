package org.fasttrack;

import org.apache.commons.lang3.RandomStringUtils;
import org.fasttrack.domain.Contact;
import org.fasttrack.domain.PhoneBook;
import org.fasttrack.persistance.ContactRepository;
import org.fasttrack.persistance.PhoneBookRepository;
import org.fasttrack.transfer.CreateContactRequest;
import org.fasttrack.transfer.CreatePhoneBookRequest;
import org.fasttrack.transfer.UpdateContactRequest;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{

    static CreateContactRequest getContactInstance(){
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setFirstName(RandomStringUtils.randomAlphabetic(4));
        createContactRequest.setLastName(RandomStringUtils.randomAlphabetic(4));
        createContactRequest.setPhoneNumber(RandomStringUtils.randomNumeric(10));
        return createContactRequest;
    }
    static CreatePhoneBookRequest getPhoneBookInstance(){
        CreatePhoneBookRequest createPhoneBookRequest = new CreatePhoneBookRequest();
        createPhoneBookRequest.setName(RandomStringUtils.randomAlphabetic(5));
        return createPhoneBookRequest;
    }

    public static void main( String[] args ) {
        PhoneBookRepository phoneBookRepository = new PhoneBookRepository();
//
        ContactRepository contactRepository = new ContactRepository();
//
        CreateContactRequest createContactRequest = getContactInstance();
//        createContactRequest.setPhoneBookId(1);
//
        CreatePhoneBookRequest createPhoneBookRequest = getPhoneBookInstance();

//        phoneBookRepository.createPhoneBook(createPhoneBookRequest);
//        createPhoneBookRequest = getPhoneBookInstance();
//        phoneBookRepository.createPhoneBook(createPhoneBookRequest);

//        contactRepository.createContact(createContactRequest, 10);
//        createContactRequest = getContactInstance();
//        contactRepository.createContact(createContactRequest,10);
//        UpdateContactRequest updateContactRequest = new UpdateContactRequest();
//        updateContactRequest.setLastName("Updatedxx");
//        updateContactRequest.setFirstName("Also update");
//        updateContactRequest.setPhoneNumber("5555556");
//        updateContactRequest.setPhoneBookId(10);
//
//        contactRepository.updateContact(30,updateContactRequest);
//
        List<Contact> contactList = contactRepository.readContact();
        for (Contact contact : contactList) {
            System.out.println(contact);
        }
//        System.out.println(contactList);
//
//        contactList = contactRepository.readContactsFromPhoneBook(1);
//        System.out.println("Only from one phone book: " + contactList);
//
//        List<PhoneBook> phoneBooks = phoneBookRepository.readPhoneBook();
//        System.out.println(phoneBooks);

//        contactRepository.deleteContactsByFirstName("%h%");
//        for(Contact contact:contactList){
//            System.out.println(contact);
//        }
//        long[] ids = {1,2,3};
//        contactRepository.deleteContacts(ids);
//        List<Object> list = contactRepository.readContactAndPhoneBook();
//        for (Object ob : list) {
//            System.out.println(ob);
//        }
        List<PhoneBook> phoneBooks = phoneBookRepository.readPhoneBook();
        for(PhoneBook phoneBook : phoneBooks){
            System.out.println(phoneBook);
        }
    }
}
