package satriiadaffa.crm.api.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customers")
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "NIK Tidak Boleh Kosong")
    @Pattern(regexp = "\\d{16,16}", message = "NIK harus 16 digit angka")
    private String NIK;

    @NotBlank(message = "Nama Tidak Boleh Kosong")
    @Size(min = 2, max = 100, message = "Nama harus 2-100 karakter")
    private String name;

    @NotBlank(message = "Email Tidak Boleh Kosong")
    @Email(message = "Email tidak valid")
    private String email;

    @NotBlank(message = "Nomor Telefon Tidak Boleh Kosong")
    @Pattern(regexp = "\\d{10,15}", message = "Nomor telepon harus 10-15 digit angka")
    private String phone;

    // Gender sebagai boolean (false = Pria, true = Wanita)
    @Pattern(regexp = "Male|Female", message = "Jenis Kelamin harus 'Male' atau 'Female'")
    private String gender;

    @NotBlank(message = "Alamat Tidak Boleh Kosong")
    @Size(min = 10, max = 255, message = "Alamat harus 10-255 karakter")
    private String address;

    @NotBlank(message = "Status Tidak Boleh Kosong")
    @Pattern(regexp = "Lead|Active|Inactive", message = "Status hanya bisa 'Lead', 'Active', atau 'Inactive'")
    private String status;
}
