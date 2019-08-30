package uet.k59t.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean deleted;

    private String firstName;

    private String lastName;

    private String company;

    private String email;

    private String source;

    private Boolean qualified;

    private int status;

    private boolean converted;
}
