package com.web.back.Controllers;



import com.web.back.Entities.Role;
import com.web.back.Exceptions.UserExceptions.UserPasswordException;
import com.web.back.auth.AuthenticationRequest;
import com.web.back.auth.AuthenticationResponse;
import com.web.back.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.back.Entities.User;
import com.web.back.Exceptions.UserExceptions.UsuarioFoundException;
import com.web.back.Exceptions.UserExceptions.UsuarioNotFoundException;
import com.web.back.Exceptions.UserExceptions.UsuarioReserveException;
import com.web.back.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@RequiredArgsConstructor
public class UsuarioController {



    private final PasswordEncoder passwordEncoder;

    @Autowired
    private  UsuarioRepository usuarioRepository;

    private final JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Endpoint para obtener todos los usuarios
    /*@CrossOrigin
    @Operation(summary = "Gets the list of users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returns a list of users filtered by a parameter",
         content = { @Content(mediaType = "application/json",schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request",content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))) })
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsuarios(@RequestParam(value = "filter", required = false, defaultValue = "") String filter) {
        List<User> usuarios = new ArrayList<>();
        try
        {
            usuarioRepository.findAll().forEach(usuarios::add);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    // Endpoint para obtener un usuario por ID
    @Operation(summary = "Get an user by its id")
    @CrossOrigin
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))) })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        User user = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        return ResponseEntity.ok().body(user);
    }*/

    // Endpoint para crear un usuario
    @Operation(summary = "Add a new User received as a parameter")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created", content = { @Content(mediaType = "application/json",schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))),
        @ApiResponse(responseCode = "404", description = "User with given id already exist", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioFoundException.class))),
        @ApiResponse(responseCode = "406", description = "User nickname unavaliable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))) })
    @PostMapping("/register")
    public AuthenticationResponse createUsuario(@RequestBody @Valid User usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()){
            throw new UsuarioFoundException(usuario.getEmail());
        }else{
            User user = new User();
            user.setName(usuario.getName());
            user.setIdentification(usuario.getIdentification());
            user.setTelefono(usuario.getTelefono());
            user.setEmail(usuario.getEmail());
            if (usuario.getPassword() == null || usuario.getPassword().isEmpty()){
                throw new UsuarioReserveException("Password");
            }else if (usuario.getPassword().length() < 8){
                throw new UserPasswordException("the size of the password must be greater than 8");
            }
            user.setPassword(passwordEncoder.encode(usuario.getPassword()));
            user.setRole(Role.USER);
            usuarioRepository.save(user);
            var token = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .user(user)
                    .token(token)
                    .build();
        }
    }

    //Endpoint para loguear un usuario
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest usuario) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword()));
            var user = usuarioRepository.findByEmail(usuario.getEmail()).get();
            var token = jwtService.generateToken(user);
            return ResponseEntity.status(HttpStatus.OK).body(AuthenticationResponse.builder()
                    .user(user)
                    .token(token)
                    .build());
    }

     // Endpoint para actualizar un usuario por ID
    /*@CrossOrigin
    @Operation(summary = "Update an user received as a parameter")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))),
        @ApiResponse(responseCode = "404", description = "User not found",content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioNotFoundException.class))) })
    @PutMapping("/update/{id}")
    public static ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody User usuario) {
        User user = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        if(user != null){
            throw new UsuarioNotFoundException(id);
        }else{
            usuarioRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }
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
