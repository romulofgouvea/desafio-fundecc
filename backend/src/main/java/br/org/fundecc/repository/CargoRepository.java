package br.org.fundecc.repository;

import br.org.fundecc.model.Cargo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
