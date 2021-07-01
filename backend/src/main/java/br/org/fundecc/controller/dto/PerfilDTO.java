package br.org.fundecc.controller.dto;

import br.org.fundecc.model.Perfil;

public class PerfilDTO {
	private Long id;
	private Long key;
	private String nome;
	private boolean remover;

	public static PerfilDTO Converter(Perfil p) {
		var dto = new PerfilDTO();

		dto.setId(p.getId());
		dto.setKey(p.getId());

		dto.setNome(p.getNome());

		dto.setRemover(true);

		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isRemover() {
		return remover;
	}

	public void setRemover(boolean remover) {
		this.remover = remover;
	}
}
