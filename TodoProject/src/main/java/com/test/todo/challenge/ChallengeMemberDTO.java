package com.test.todo.challenge;


/**
 * 
 * ChallengeMemberDTO
 * 
 * @author 4조
 *
 */
public class ChallengeMemberDTO {
	private int seq;
	private int cseq;
	private int mseq;
	private String cnickname;
	private int ccnt;
	
	
	@Override
	public String toString() {
		return "ChallengeMemberDTO [seq=" + seq + ", cseq=" + cseq + ", mseq=" + mseq + ", cnickname=" + cnickname
				+ ", ccnt=" + ccnt + "]";
	}


	/**
	 * ChallengeMemberDTO 생성자
	 * 
	 * @param seq ChallengeMember 번호 
	 * @param cseq 탤린지 번호
	 * @param mseq 회원번호
	 * @param cnickname 챌린지 닉네임
	 * @param ccnt 챌린지 횟수
	 */
	public ChallengeMemberDTO(int seq, int cseq, int mseq, String cnickname, int ccnt) {
		super();
		this.seq = seq;
		this.cseq = cseq;
		this.mseq = mseq;
		this.cnickname = cnickname;
		this.ccnt = ccnt;
	}
	
	
	
	
	
	
}
