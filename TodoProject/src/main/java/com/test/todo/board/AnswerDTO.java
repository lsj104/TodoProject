package com.test.todo.board;

import lombok.Data;

/**
 * 
 * AnswerDTO
 * 댓글번호, 회원번호, 게시글번호, 등록 날짜, 댓글 내용, 닉네임, 프로필 이미지, name 
 * 
 * @author 4조
 *
 */
@Data
public class AnswerDTO {

	private String seq;
	private String mseq;
	private String qseq;
	private String adate;
	private String content;
	
	private String nickname;
	private String image;
	private String name;
	
}

























