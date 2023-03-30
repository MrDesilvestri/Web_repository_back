package controller;

@RestController
@RequestMapping("/canchas")
public class CanchaController {
 
    @Autowired
    private CanchaRepository canchaRepository;
 
    @GetMapping("/")
    public List<Cancha> getAllCanchas() {
        return canchaRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Cancha> getCanchaById(@PathVariable(value = "id") Long canchaId)
            throws ResourceNotFoundException {
        Cancha cancha = canchaRepository.findById(canchaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cancha no encontrada para este id :: " + canchaId));
        return ResponseEntity.ok().body(cancha);
    }
 
    @PostMapping("/")
    public Cancha createCancha(@RequestBody Cancha cancha) {
        return canchaRepository.save(cancha);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<Cancha> updateCancha(@PathVariable(value = "id") Long canchaId,
                                           @RequestBody Cancha canchaDetails) throws ResourceNotFoundException {
        Cancha cancha = canchaRepository.findById(canchaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cancha no encontrada para este id :: " + canchaId));
 
        cancha.setNombre(canchaDetails.getNombre());
        cancha.setDireccion(canchaDetails.getDireccion());
        cancha.setCiudad(canchaDetails.getCiudad());
        cancha.setCapacidad(canchaDetails.getCapacidad());
        cancha.setSuperficie(canchaDetails.getSuperficie());
        cancha.setEquipos(canchaDetails.getEquipos());
        cancha.setUsuario(canchaDetails.getUsuario());
 
        final Cancha updatedCancha = canchaRepository.save(cancha);
        return ResponseEntity.ok(updatedCancha);
    }
 
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCancha(@PathVariable(value = "id") Long canchaId)
            throws ResourceNotFoundException {
        Cancha cancha = canchaRepository.findById(canchaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cancha no encontrada para este id :: " + canchaId));
 
        canchaRepository.delete(cancha);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}

