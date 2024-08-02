package com.compass.assessment.util;

import java.util.List;

// Defining a contract as we might want to user different implementations for different types or algorithms used
// depending on the case we could also use strategy pattern, but it's always a good idea to code to an interface
public interface DuplicateFinder<T> {

    List<T> findDuplicates(List<T> dataset);

}
