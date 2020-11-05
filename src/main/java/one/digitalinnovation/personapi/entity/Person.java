package one.digitalinnovation.personapi.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(nullable = false)
     private String firstName;

     @Column(nullable = false)
     private String lastName;

     @Column(nullable = false, unique = true)
     private String cpf;

     private LocalDate birthDate;

     @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST ,CascadeType.MERGE ,CascadeType.REMOVE})
     private List<Phone> phones;

}
