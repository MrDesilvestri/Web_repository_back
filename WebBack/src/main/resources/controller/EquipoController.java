package controller;

@RestController
@RequestMapping("/equipos")
public class EquipoController {
 
    @Autowired
    private EquipoRepository equipoRepository;
 
    @GetMapping("")
    public List<Equipo> getAllEquipos() {
        return equipoRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable(value = "id") Long equipoId)
            throws ResourceNotFoundException {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found for this id :: " + equipoId));
        return ResponseEntity.ok().body(equipo);
    }
 
    @PostMapping("")
    public Equipo createEquipo(@Valid @RequestBody Equipo equipo) {
        return equipoRepository.save(equipo);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable(value = "id") Long equipoId,
                                               @Valid @RequestBody Equipo equipoDetails) throws ResourceNotFoundException {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found for this id :: " + equipoId));
 
        equipo.setNombre(equipoDetails.getNombre());
        equipo.setAnhoFundacion(equipoDetails.getAnhoFundacion());
        equipo.setLiga(equipoDetails.getLiga());
        equipo.setCanchas(equipoDetails.getCanchas());
        equipo.setUsuario(equipoDetails.getUsuario());
 
        final Equipo updatedEquipo = equipoRepository.save(equipo);
        return ResponseEntity.ok(updatedEquipo);
    }
 
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteEquipo(@PathVariable(value = "id") Long equipoId)
            throws ResourceNotFoundException {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found for this id :: " + equipoId));
 
        equipoRepository.delete(equipo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
