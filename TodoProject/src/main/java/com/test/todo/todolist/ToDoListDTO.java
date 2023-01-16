package com.test.todo.todolist;

import lombok.Data;

/**
 * ToDoListDTO
 * ToDoList 번호, 회원번호, ToDoList내용, ToDoList 등록 날짜, ToDoList 타입(1:main, 2:sub)
 * 
 * @author 4조
 *
 */

@Data
public class ToDoListDTO {
	
	private String seq;			//투두리스트 번호
 	private String mseq;		//회원번호
	private String content;		//투두리스트 내용
	private String regdate;		//투두리스트 날짜
	private String type;		//투두리스트 타입(1:main, 2:sub)
	
}