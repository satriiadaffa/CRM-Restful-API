package satriiadaffa.crm.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nama Tidak Boleh Kosong")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Email tidak boleh kosong") // level APP (Hibernate Validator), HTTP 400 Bad Request (JSON error response)
    @Email(message = "Format email tidak valid")
    @Column(nullable = false, unique = true) // level database (JPA), exc handling Database Constraint Exception
    private String email;

    @NotBlank(message = "Nama Tidak Boleh Kosong")
    @Column(nullable = false, unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
