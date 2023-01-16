package com.test.todo.member;

import lombok.Data;
/**
 * MemberInfoDTO
 * 닉네임, 프로필이미지, 카테고리, 디데이 이름, 디데이 날짜, 챌린지 횟수, 응원메세지, 포인트
 * 
 * @author 4조
 *
 */
@Data
public class MemberInfoDTO {
	
	private String nickname; 
	private String image;
	private String category;
	
	private String ddayname;
	private String ddate;
	
	private String chalcnt;
	private String message;	
	private String point;

}
