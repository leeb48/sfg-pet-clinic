package guru.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/*
    A class designated with the MappedSuperclass annotation can be mapped in
    the same way as an entity except that the mappings will apply only to its
    subclasses since no table exists for the mapped superclass itself
*/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity {

    public Person(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
