package com.compass.assessment;

public record Contact(
        Long id,
        String firstname,
        String lastname,
        String email,
        int zipcode,
        String address) {

}
