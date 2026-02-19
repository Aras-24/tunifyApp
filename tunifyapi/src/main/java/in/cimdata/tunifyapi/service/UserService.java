package in.cimdata.tunifyapi.service;

import in.cimdata.tunifyapi.document.User;
import in.cimdata.tunifyapi.dto.RegisterRequest;
import in.cimdata.tunifyapi.dto.UserResponse;
import in.cimdata.tunifyapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(RegisterRequest request){
        // Prüfen ob die E-Mail vorhanden ist
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Diese E-Mail ist bereits registriert.");
        }

        //Neuen User erstellen
        User newUser = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(User.Role.USER)
                .build();

        userRepository.save(newUser);
        return UserResponse.builder()
                .id(newUser.getId())
                .email(newUser.getEmail())
                .role(UserResponse.Role.USER)
                .build();
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Für diese E-Mail existiert kein Benutzer: "+email));
    }
}
