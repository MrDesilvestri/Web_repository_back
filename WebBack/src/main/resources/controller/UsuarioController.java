package controller;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("")
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable(value = "id") Long usuarioId)
            throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para este id :: " + usuarioId));
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("")
    public Usuario crear(@Valid @RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable(value = "id") Long usuarioId,
                                               @Valid @RequestBody Usuario usuarioDetails) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para este id :: " + usuarioId));

        usuario.setNombreUsuario(usuarioDetails.getNombreUsuario());
        usuario.setContrasena(usuarioDetails.getContrasena());
        usuario.setCorreoElectronico(usuarioDetails.getCorreoElectronico());

        final Usuario actualizadoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(actualizadoUsuario);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> borrar(@PathVariable(value = "id") Long usuarioId)
            throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para este id :: " + usuarioId));

        usuarioRepository.delete(usuario);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("borrado", Boolean.TRUE);
        return respuesta;
    }
}
