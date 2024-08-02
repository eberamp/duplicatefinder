package com.compass.assessment.util;

import com.compass.assessment.Contact;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ContactDuplicateFinder implements DuplicateFinder<Contact> {

    public List<Contact> findDuplicates(List<Contact> contacts){
        /* Not ideal implementation as this is an O(n^2) * 2 approach, first thing we could do to improve it would be
           to order each field lexicographically and then implement a binary search, on the other hand if we wanted
           granularity we could create a tree mapping for each field as a sort of search engine autocompletion
           putting the most likely matches ahead down the tree as we iterate through each item
        */

        // I was tempted to override the equals method in Contact, but I would not have enough control over each field
        // so i rather use filtering, we could also use custom predicated for different levels of accuracy we would want
        contacts.stream().forEach(contact -> {
            Map<Long, List<ContactMatch>> lowMatches = contacts.stream()
                    .filter( it -> !Objects.equals(it.id(), contact.id()))
                    .filter( it ->
                            it.firstname().equalsIgnoreCase(contact.firstname())
                                    || it.zipcode() == contact.zipcode()
                    )
                    .map(it ->  new ContactMatch(contact.id(), it.id(), MatchScore.LOW))
                    .collect(Collectors.groupingBy(ContactMatch::sourceId));

            Map<Long, List<ContactMatch>> highMatches = contacts.stream()
                    .filter( it -> !Objects.equals(it.id(), contact.id()))
                    .filter( it -> it.email().equalsIgnoreCase(contact.email()))
                    .filter( it -> it.address().equalsIgnoreCase(contact.address()))
                    .filter( it -> it.zipcode() == contact.zipcode())
                    .map(it ->  new ContactMatch(contact.id(), it.id(), MatchScore.LOW))
                    .collect(Collectors.groupingBy(ContactMatch::sourceId));
        });

        return Collections.emptyList();
    }
}
