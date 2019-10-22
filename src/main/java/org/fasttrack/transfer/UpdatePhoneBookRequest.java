package org.fasttrack.transfer;

public class UpdatePhoneBookRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UpdatePhoneBookRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
