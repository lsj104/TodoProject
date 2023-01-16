package com.test.todo.challenge;

import lombok.Data;

/**
 * 
 * CategoryDTO 클래스
 * 카테고리 이름 getter, setter 
 * 
 * @author 4조  
 *
 */
@Data
public class CategoryDTO { 

	private int seq;
	private String name;
	
	/**
	 * CategoryDTO 생성자
	 * 
	 * @param seq  카테고리 번호
	 * @param name 카테고리 이름
	 */
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
