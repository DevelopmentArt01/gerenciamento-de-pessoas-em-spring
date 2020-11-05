package one.digitalinnovation.personapi.services;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServices {

    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    /*@Autowired
    public PersonServices(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }*/

    @PostMapping
    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSaved = personMapper.toModel(personDTO);


        Person savedPerson = personRepository.save(personToSaved);
        return createMessageResponse(savedPerson.getId() , "Create person  with ID");
    }

    public List<PersonDTO> listAll() {
       List<Person> allPeople =  personRepository.findAll();
         return allPeople.stream()
                 .map(personMapper::toDTO)
                 .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {

     Person person = personRepository.findById(id)
             .orElseThrow(() -> new PersonNotFoundException(id));

       return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        personRepository.deleteById(id);

    }

    public MessageResponseDTO updateByID(Long id, PersonDTO personDTO) throws PersonNotFoundException {

      verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatePerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatePerson.getId() , "Update person with ID");
    }

    private Person verifyIfExists(Long id)throws PersonNotFoundException{
        return  personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
