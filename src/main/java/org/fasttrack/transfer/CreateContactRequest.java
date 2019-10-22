package org.fasttrack.transfer;

public class CreateContactRequest {

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
        return "CreateContactRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneBookId='" + phoneBookId + '\'' +
                '}';
    }
}
