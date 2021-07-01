package br.org.fundecc.controller;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import br.org.fundecc.controller.dto.CargoDTO;
import br.org.fundecc.model.Cargo;
import br.org.fundecc.repository.CargoRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cargos")
public class CargoController {

    Logger logger = LoggerFactory.getLogger(CargoController.class);
    CargoRepository cargoRepository;

    public CargoController(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @GetMapping("")
    public List<CargoDTO> ListarCargos(@RequestParam(defaultValue = "asc") String ordem) {

        List<Cargo> cargos = new ArrayList<Cargo>();

        logger.info(ordem);

        if (ordem.equals("asc")) {
            cargos = cargoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        } else if (ordem.equals("desc")) {
            cargos = cargoRepository.findAll(Sort.by(Sort.Direction.DESC, "nome"));
        }

        return cargos
                .stream()
                .map(CargoDTO::Converter)
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public void CadastrarCargo(@RequestBody CargoDTO dto) {

        var listaCargo = cargoRepository.findAll();

        final boolean[] existeNome = new boolean[1];

        listaCargo
                .stream()
                .forEach(c -> {
                    if (dto.getNome().equals(c.getNome())) {
                        existeNome[0] = true;
                    }
                });

        if(!existeNome[0]) {
            var cargo = new Cargo();
            cargo.setNome(dto.getNome());
            cargoRepository.save(cargo);
        }
    }

    @PutMapping("/{id}")
    public void AtualizarCargo(@PathVariable("id") long id, @RequestBody CargoDTO dto) throws Exception {
        var c = cargoRepository.findById(id);

        if (c.isPresent()) {
            var cargoSave = c.get();
            cargoSave.setNome(dto.getNome());
            cargoRepository.save(cargoSave);
        } else {
            throw new Exception("Cargo não encontrado!");
        }
    }
}
