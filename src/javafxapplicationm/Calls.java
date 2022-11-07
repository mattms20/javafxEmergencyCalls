/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationm;

/**
 *
 * @author Matt Markopoulos
 */

//class about the calls
public class Calls {
    
    int id;
    String name, surname,city,address,division,incident,time, phone;

    public Calls(int id, String phone, String name, String surname, String city, String address, String division, String incident,String time) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.address = address;
        this.division = division;
        this.incident = incident;
        this.time=time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }
        public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getDivision() {
        return division;
    }

    public String getIncident() {
        return incident;
    }
        public String getTime() {
        return time;
    }
    
}
