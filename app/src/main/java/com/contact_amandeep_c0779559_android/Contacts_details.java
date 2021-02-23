package com.contact_amandeep_c0779559_android;

public class Contacts_details {
  public  String _id, firstname, address, lastname,email,phoneno;

    public Contacts_details(String _id, String firstname, String address, String lastname, String email, String phoneno) {
        this._id = _id;
        this.firstname = firstname;
        this.address = address;
        this.lastname = lastname;
        this.email = email;
        this.phoneno = phoneno;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
