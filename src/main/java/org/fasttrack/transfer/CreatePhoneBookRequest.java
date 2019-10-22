package org.fasttrack.transfer;

public class CreatePhoneBookRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreatePhoneBookRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
