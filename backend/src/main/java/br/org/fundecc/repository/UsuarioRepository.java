package br.org.fundecc.repository;

import br.org.fundecc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByCpf(String cpf);
}
