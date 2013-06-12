package com.library.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Banda {

	private int id;
	
	private String nome;
	
	private int anoDeFormacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAnoDeFormacao() {
		return anoDeFormacao;
	}

	public void setAnoDeFormacao(int anoDeFormacao) {
		this.anoDeFormacao = anoDeFormacao;
	}
	
	

	// getters e setters
}
