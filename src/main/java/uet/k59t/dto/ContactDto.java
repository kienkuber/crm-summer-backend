package uet.k59t.dto;

import lombok.Data;

@Data
public class ContactDto {
    private Long id;

    private boolean deleted;

    private Long accountId;

    private String firstName;

    private String lastName;

    private String email;
}
