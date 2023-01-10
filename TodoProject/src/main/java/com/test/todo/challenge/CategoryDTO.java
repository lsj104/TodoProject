package com.test.todo.challenge;

import lombok.Data;

@Data
public class CategoryDTO { 

	private int seq;
	private String name;
	
	public CategoryDTO(int seq, String name ) {
		super();
		this.seq = seq;
		this.name = name;
	}

	@Override
	public String toString() {
		return "CategoryDTO [seq=" + seq + ", name=" + name + "]";
	}

	
	
	
}
