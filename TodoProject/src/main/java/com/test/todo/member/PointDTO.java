package com.test.todo.member;

import lombok.Data;
/**
 * 
 * PointDTO 클래스
 * 포인트 관련 getter, setter
 * 
 * @author 4조
 */
@Data
public class PointDTO {
	
	private String seq;
	private String mseq;
	private String regdate;
	private String iseq;
	
	private String itemname;
	private String itemcost;
	private String image;
	
}
