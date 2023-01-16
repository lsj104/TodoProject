package com.test.todo.member;

import lombok.Data;

/**
 * PlusRewardDTO
 * 획득 포인트 내역 번호, 회원번호, 획득 날짜, 획득 포인트 번호, 획득 포인트, 획득 포인트 내용 
 * 
 * @author 4조
 *
 */
@Data
public class PlusRewardDTO {

	private String seq;
	private String mseq;
	private String regdate;
	private String pseq;	
	
	private int plusPoint;
	private String plusName;
	
}
