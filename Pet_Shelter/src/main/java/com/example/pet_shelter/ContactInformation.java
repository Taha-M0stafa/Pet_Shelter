public class ContactInformation {
    private int phoneNumber;
    private String name;
    private String email;
    private String address;

    public ContactInformation(){
        this.phoneNumber=-1;
        name="user";
        email="example@gmail.com";
        address="no current address";
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}
