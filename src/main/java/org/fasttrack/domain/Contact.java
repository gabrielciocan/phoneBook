package org.fasttrack.domain;

public class Contact {

    private long contactId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private long phoneBookId;

    public long getPhoneBookId() {
        return phoneBookId;
    }

    public void setPhoneBookId(long phoneBookId) {
        this.phoneBookId = phoneBookId;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + contactId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phone_book_id=" + phoneBookId +
                '}';
    }
}
