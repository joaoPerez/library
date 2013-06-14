package com.library.entity.enums;

public enum UserType {
	USER(1, "Usuário"), ADM(2, "Administrador");

	private final int id;

	private final String type;

	UserType(int id, String type) {
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

}
