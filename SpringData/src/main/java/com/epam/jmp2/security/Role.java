package com.epam.jmp2.security;

public class Role {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + "]";
	}	

}
