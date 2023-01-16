package com.test.todo.todolist;

import lombok.Data;

/**
 * 
 * CalDTO
 * 
 * @author 4조
 *
 */
@Data
public class CalDTO {
	private int seq;
	private String content;
	private String startdate;
	private String endd;
	private int mesq;

	@Override
	public String toString() {
		return "CalDTO [seq=" + seq + ", content=" + content + ", startdate=" + startdate + ", endd=" + endd + ", mesq="
				+ mesq + "]";
	}

	/**
	 * 
	 * CalDTO 생성자
	 * 
	 * @param seq 일정 번호
	 * @param content 일정 내용
	 * @param startdate 일정 시작일
	 * @param endd 일정 종료일
	 * @param mesq 회원번호
	 */
	public CalDTO(int seq, String content, String startdate, String endd, int mesq) {
		super();
		this.seq = seq;
		this.content = content;
		this.startdate = startdate;
		this.endd = endd;
		this.mesq = mesq;
	}

}
