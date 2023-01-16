package com.test.todo.item;

import lombok.Data;

/**
 * 
 * MinusRewardDTO
 * 구매 내역 번호, 회원 번호, 구매 날짜, 아이템 번호, 차감 포인트 
 * 
 * @author 4조
 *
 */
@Data
public class MinusRewardDTO {

	private String seq;
	private String mseq;
	private String regdate;
	private String iseq;
	
	private int minusPoint;
	
}
