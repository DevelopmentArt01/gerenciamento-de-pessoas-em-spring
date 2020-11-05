package one.digitalinnovation.personapi.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.repository.PersonRepository;
import one.digitalinnovation.personapi.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController //contolador acessado por uma api rest
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

  private PersonServices personServices;

 /* @Autowired
  public PersonController(PersonServices personServices){
      this.personServices = personServices;
  }*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody  @Valid  PersonDTO personDTO) {
         return personServices.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listALL(){
       return personServices.listAll();
    }

    @GetMapping("{/id}")
    public PersonDTO findByID(@PathVariable Long id) throws PersonNotFoundException {
       return personServices.findById(id);
    }

    @PutMapping("{/id}")
    public MessageResponseDTO updateById(@PathVariable  Long id ,@RequestBody @Valid  PersonDTO personDTO) throws PersonNotFoundException {
     return personServices.updateByID(id, personDTO);
    }

    @DeleteMapping("{/id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable  Long id) throws PersonNotFoundException {
      personServices.delete(id);
   }
}
