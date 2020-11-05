package one.digitalinnovation.personapi.utils;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtils {


    private static final String FIST_NAME ="User One" ;
    private static final String LAST_NAME =" UserLastName" ;
    private static final String CPF_NUMBER = "333.333.333-79";
    private static final Long PERSON_ID = 1L ;
    public static final LocalDate BIRTH_DATE = LocalDate.of(2010 , 10 , 1);


    public static PersonDTO createFakeDTO() {
       return PersonDTO.builder()
               .firstName(FIST_NAME)
               .lastName(LAST_NAME)
               .cpf(CPF_NUMBER)
               .birthDate("04-04-2010")
               .phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
               .build();

    }

    public static Person createFakeEntity() {

        return Person.builder()
                .id(PERSON_ID)
                .firstName(FIST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();

    }
}
