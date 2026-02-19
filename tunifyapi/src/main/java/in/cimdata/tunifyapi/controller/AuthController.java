package in.cimdata.tunifyapi.controller;

import in.cimdata.tunifyapi.document.User;
import in.cimdata.tunifyapi.dto.AuthRequest;
import in.cimdata.tunifyapi.dto.AuthResponse;
import in.cimdata.tunifyapi.dto.RegisterRequest;
import in.cimdata.tunifyapi.dto.UserResponse;
import in.cimdata.tunifyapi.service.AppUserDetailsService;
import in.cimdata.tunifyapi.service.UserService;
import in.cimdata.tunifyapi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        try {
            // Benutzer authentifizieren
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));


            // User laden
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            User existingUser = userService.findByEmail(request.getEmail());

            // JWT Token erzeugen
            String token = jwtUtil.generateToken(userDetails, existingUser.getRole().name());


            return ResponseEntity.ok(new AuthResponse(token,request.getEmail(),existingUser.getRole().name()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("E-Mail-Adresse oder Passwort stimmt nicht.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }





    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        try {
            UserResponse response = userService.registerUser(request);
            return ResponseEntity.ok(request);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Registrierungsfehler: " + e.getMessage());
        }

    }
}
