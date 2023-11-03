package com.devsu.user.repository.person.impl;

import com.devsu.user.domain.entity.Person;
import com.devsu.user.repository.person.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonRepository implements IPersonRepositoryFacade {

    private final IPersonRepository personRepository;

    @Autowired
    public PersonRepository(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }
}
