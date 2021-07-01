package br.org.fundecc.controller;

import br.org.fundecc.controller.dto.PerfilDTO;
import br.org.fundecc.model.Perfil;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import br.org.fundecc.repository.PerfilRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/perfis")
public class PerfilController {

	PerfilRepository perfilRepo;

	// construtor
	public PerfilController(PerfilRepository repo) {
		this.perfilRepo = repo;
	}

	@GetMapping("")
	public List<PerfilDTO> ListarPerfis() {
		var perfis = perfilRepo.findAll(Sort.by(Sort.Direction.ASC, "nome"));

		return perfis.stream().map(PerfilDTO::Converter).collect(Collectors.toList());
	}

	@PostMapping("")
	public void CadastrarPerfil(@RequestBody PerfilDTO dto) {
		// se nao tiver nome return
		if (dto.getNome().equals("")) {
			return;
		}

		var listaPerfil = perfilRepo.findAll();

		final boolean[] existeNome = new boolean[1];

		// verifica se o nome é unico
		listaPerfil.stream().forEach(c -> {
			if (dto.getNome().equals(c.getNome())) {
				existeNome[0] = true;
			}
		});

		if (!existeNome[0]) {
			// declara uma variavel do tipo objeto do perfil
			var perfil = new Perfil();
			// seta na variavel perfil o nome vindo do body contido no dto
			perfil.setNome(dto.getNome());
			// salva o perfil no banco através do repositorio
			perfilRepo.save(perfil);
		}
	}

	@PutMapping("/{id}")
	public void EditarPerfil(@PathVariable("id") long id, @RequestBody PerfilDTO dto) throws Exception {
		// repositorio vai no banco e busca por id o perfil
		var perfilPorId = perfilRepo.findById(id);
		// se contem algum id vindo do repositorio
		if (perfilPorId.isPresent()) {
			// peguei o perfil pelo id
			var perfilParaSalvar = perfilPorId.get();

			// setei o nome que veio do body na entidade que vai ser salva
			perfilParaSalvar.setNome(dto.getNome());

			// salvei a entidade no banco para atualizar o nome dela
			perfilRepo.save(perfilParaSalvar);
		} else {
			throw new Exception("Perfil não encontrado!");
		}
	}

	@DeleteMapping("/{id}")
	public void RemoverPerfil(@PathVariable("id") long id) {
		var perfilPorId = perfilRepo.findById(id);

		// verificar se nao está ligado ao usuario

		if (perfilPorId.isPresent()) {
			var perfilParaDeletar = perfilPorId.get();
			perfilRepo.delete(perfilParaDeletar);
		}
	}
}
