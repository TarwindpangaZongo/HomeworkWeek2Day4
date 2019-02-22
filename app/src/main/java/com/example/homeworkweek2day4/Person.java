package com.example.homeworkweek2day4;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;
    private int Id;

    protected Person(Parcel in) {
        name = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        zip = in.readString();
        phone = in.readString();
        email = in.readString();
        Id = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public Person() {
    }

    public Person(String name, String address, String city, String state, String zip, String phone, String email, int id) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        Id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zip);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeInt(Id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public static Creator<Person> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", Id=" + Id +
                '}';
    }
}
