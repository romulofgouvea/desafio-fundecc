package br.org.fundecc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.org.fundecc.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
