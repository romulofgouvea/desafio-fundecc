package br.org.fundecc.controller.dto;

import br.org.fundecc.model.Usuario;

public class UsuarioDTO {
	private Long id;
	private Long key;
	private String nome;
	private String cpf;
	private String data_nascimento;
	private char sexo;
	private CargoDTO cargo;

	public static UsuarioDTO Converter(Usuario u) {
		var dto = new UsuarioDTO();

		dto.setId(u.getId());
		dto.setKey(u.getId());

		dto.setNome(u.getNome());
		dto.setCpf(u.getCpf());
		dto.setData_nascimento(u.getData_nascimento().toString());
		dto.setSexo(u.getSexo());

		try {
			// Adicionando o cargo
			var cargo = u.getCargo();
			dto.setCargo(CargoDTO.Converter(cargo));

		} catch (Exception e) {

		}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public CargoDTO getCargo() {
		return cargo;
	}

	public void setCargo(CargoDTO cargo) {
		this.cargo = cargo;
	}
}
