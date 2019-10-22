package org.fasttrack.domain;

public class PhoneBook {

    private long phoneBookId;
    private String name;

    public long getPhoneBookId() {
        return phoneBookId;
    }

    public void setPhoneBookId(long phoneBookId) {
        this.phoneBookId = phoneBookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "phoneBookId=" + phoneBookId +
                ", name='" + name + '\'' +
                '}';
    }
}
