package com.test.todo.board;

import lombok.Data;

/**
 * BoardDTO 클래스
 * 게시판 시작 글 seq, 멤버 seq, 날짜, 제목, 내용, 조회수, 이름, 닉네임, 프로필 이미지
 * 
 * @author 4조
 */
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

























