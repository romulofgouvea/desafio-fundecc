package br.org.fundecc.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Pessoa {

	@Column(name = "nome", nullable = false)
	private String nome;

	@Basic(optional = false)
	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf;

	@Column(name = "data_nascimento")
	private LocalDateTime data_nascimento;

	@Column(name = "sexo")
	private char sexo;

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

	public LocalDateTime getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDateTime data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

}
