package com.web.back.Controllers;



import com.web.back.Entities.ERole;
import com.web.back.Entities.Role;
import com.web.back.Exceptions.UserExceptions.UsuarioReserveException;
import com.web.back.auth.AuthenticationRequest;
import com.web.back.payload.request.SignupRequest;
import com.web.back.payload.response.JwtResponse;
import com.web.back.payload.response.MessageResponse;
import com.web.back.repository.RoleRepository;
import com.web.back.security.jwt.JwtUtils;
import com.web.back.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.web.back.Entities.User;
import com.web.back.Exceptions.UserExceptions.UsuarioFoundException;
import com.web.back.Exceptions.UserExceptions.UsuarioNotFoundException;
import com.web.back.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 4200)
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    // Endpoint para obtener todos los usuarios
    @Operation(summary = "Gets the list of users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returns a list of users filtered by a parameter",
         content = { @Content(mediaType = "application/json",schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request",content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))) })
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsuarios(@RequestParam(value = "filter", required = false, defaultValue = "") String filter) {
        try
        {
            List<User> usuarios = new ArrayList<>(usuarioRepository.findAll());
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    //Endpoint para obtener un usuario por ID
    @Operation(summary = "Get an user by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))) })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        User user = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        return ResponseEntity.ok().body(user);
    }

    // Endpoint para crear un usuario
    @Operation(summary = "Add a new User received as a parameter")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created", content = { @Content(mediaType = "application/json",schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))),
        @ApiResponse(responseCode = "404", description = "User with given id already exist", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioFoundException.class))),
        @ApiResponse(responseCode = "406", description = "User nickname unavaliable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))) })
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getName(),
        signUpRequest.getIdentification(),
        signUpRequest.getTelefono(),
        signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userERole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: ERole is not found."));
            roles.add(userERole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminERole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: ERole is not found."));
                        roles.add(adminERole);

                        break;
                    case "mod":
                        Role modERole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: ERole is not found."));
                        roles.add(modERole);
                        break;
                    default:
                        Role userERole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: ERole is not found."));
                        roles.add(userERole);
                }
            });
        }
        user.setRoles(roles);
        usuarioRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    //Endpoint para loguear un usuario
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    /*@Operation(summary = "Update an user received as a parameter")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))),
        @ApiResponse(responseCode = "404", description = "User not found",content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))) })
    @PutMapping("/update/{id}")
    public static ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody User usuario) {
        User user = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        user.setName(usuario.getName());
        user.setIdentification(usuario.getIdentification());
        user.setTelefono(usuario.getTelefono());
        user.setEmail(usuario.getEmail());
        user.setPassword(usuario.getPassword());
        usuarioRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    // Endpoint para eliminar un usuario por ID
    @CrossOrigin
    @Operation(summary = "Delete a user by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User deleted", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))),
        @ApiResponse(responseCode = "404", description = "user not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))),
        @ApiResponse(responseCode = "406", description = "user has a reserve asociated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioReserveException.class))) })
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        User user = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        if(user != null){
            throw new UsuarioNotFoundException(id);
        }else{
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("User with id " + id +" deleted successfully");
        }
    }*/
}
