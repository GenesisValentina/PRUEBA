import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

@SpringBootApplication
@RestController
@RequestMapping("/api/users")
public class UserRegistrationApp {

    private Map<String, User> users = new HashMap<>();

    @PostMapping("/register")
    public Object registerUser(@RequestBody UserRegistrationRequest request) {
        String email = request.getEmail();
        
        // Validar formato de correo electrónico
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!Pattern.matches(emailRegex, email)) {
            ErrorMessage errorMessage = new ErrorMessage("Formato de correo electrónico incorrecto");
            return errorMessage;
        }
        
        // Verificar si el correo ya está registrado
        if (users.containsKey(email)) {
            ErrorMessage errorMessage = new ErrorMessage("El correo ya está registrado");
            return errorMessage;
        }
        
        // Crear un nuevo usuario
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(request.getName());
        user.setEmail(email);
        user.setPassword(request.getPassword());
        user.setPhones(request.getPhones());
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(user.getCreated());
        user.setToken(UUID.randomUUID().toString());
        user.setActive(true);
        
        // Almacenar el usuario en la base de datos
        users.put(email, user);
        
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserRegistrationApp.class, args);
    }
}