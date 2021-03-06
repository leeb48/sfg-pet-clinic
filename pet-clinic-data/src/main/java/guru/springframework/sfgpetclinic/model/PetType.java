package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "types")
public class PetType extends BaseEntity {

    @Column(name = "type")
    private String name;

    @Override
    public String toString() {
        return this.name;
    }
}
