package satriiadaffa.crm.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import satriiadaffa.crm.api.models.UserModel;
import satriiadaffa.crm.api.repositories.UserRepository;
import satriiadaffa.crm.api.securities.JwtSecurity;
import satriiadaffa.crm.api.services.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtSecurity jwtSecurity;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserModel user){

        // Cek apakah email sudah digunakan
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email sudah digunakan!");
        }

        // Cek apakah username sudah digunakan
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username sudah digunakan!");
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User Berhasil Didaftarkan");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserModel user) {
        Optional<UserModel> foundUser = userRepository.findByUsername(user.getUsername());

        if (foundUser.isPresent() && new BCryptPasswordEncoder().matches(user.getPassword(), foundUser.get().getPassword())) {
            String role = foundUser.get().getRole().name(); // ✅ Ambil role user
            String token = jwtSecurity.generateToken(user.getUsername(), role); // ✅ Tambahkan role ke token

            // ✅ Response lebih informatif dalam format JSON
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login berhasil");
            response.put("token", token);
            response.put("username", foundUser.get().getUsername());
            response.put("role", role); // ✅ Tambahkan role dalam response

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body(Map.of("error", "Username atau Password salah"));
    }


}
