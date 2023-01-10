package com.test.todo.board;

import lombok.Data;

@Data
public class BoardDTO {
	
	//질문게시판
	private String seq; 
	private String mseq; 
	private String qdate; 
	private String title; 
	private String content; 
	private String readcount;
	
	//이름,프사
	private String name;
	private String nickname;
	private String image;
	
	
}

























