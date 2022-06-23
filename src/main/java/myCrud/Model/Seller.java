package myCrud.Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Seller extends Person{
    @NotEmpty(message = "Nip should not be empty")
    private String nip;

    public Seller(int id, String name, String password, String email, String nip) {
        super(id, name, password, email);
        this.nip = nip;
    }

    public Seller() {
        super();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }


    public String getNip() {

        return nip;
    }

    public void setNip(String Nip) {

        this.nip=Nip;
    }
}