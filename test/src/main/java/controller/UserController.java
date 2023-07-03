import prueba.test.util.JwtUtil;
import static org.mockito.Mockito.*;
import java.util.Date;
import java.util.UUID;

// Importar las clases necesarias para trabajar con JWT y Spring Security
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("{\"mensaje\": \"El correo ya está registrado\"}");
        }


        // Crear una instancia del modelo de usuario
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        // Configurar otros campos del usuario
        user.setId(UUID.randomUUID().toString());
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(user.getCreated());
        user.setIsActive(true);

        // Generar el token JWT
        String token = generateJWT(user.getId());

        // Asignar el token al usuario
        user.setToken(token);

        // Persistir el usuario en la base de datos
        userRepository.save(user);

        // Retornar el usuario y el token en la respuesta
        return ResponseEntity.ok(user);
    }


    private String generateJWT(String userId) {
        // Configurar los claims del token JWT
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + TOKEN_EXPIRATION_TIME); // Define el tiempo de expiración del token

        return Jwts.builder()
                .setId(userId)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET_KEY) // Define el algoritmo de firma y la clave secreta para firmar el token
                .compact();
    }

}




