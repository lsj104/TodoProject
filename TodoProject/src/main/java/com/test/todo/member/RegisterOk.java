package com.test.todo.member;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * 회원가입 한 회원의 정보 처리를 위한 서블릿
 * @author 4조
 *
 */
@WebServlet("/member/registerok.do")
public class RegisterOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		req.setCharacterEncoding("UTF-8");

		String path = req.getRealPath("/asset/images/profile");

		int size = 1024 * 1024 * 10;

		String email = "";
		String pw = "";
		String nickname = "";
		String category = "";
		String filename = "";
		String ddayname = "";
		String ddate = "";

		int mresult = 0;
		int miresult = 0;

		try {

			MultipartRequest multi = new MultipartRequest(req, path, size, "UTF-8", new DefaultFileRenamePolicy()

			);

			email = multi.getParameter("email");
			pw = multi.getParameter("pw");
			nickname = multi.getParameter("nickname");
			category = multi.getParameter("category");

			filename = multi.getFilesystemName("profile");
			ddayname = multi.getParameter("ddayname");
			ddate = multi.getParameter("ddate");

			if (filename == null) {
				filename = "basic_profile.png"; // 기본 프로필 사진
			}

			MemberDAO dao = new MemberDAO();
			MemberDTO mdto = new MemberDTO();

			mdto.setEmail(email);
			mdto.setPw(pw);

			mresult = dao.registerMember(mdto);

			System.out.println(mdto);

			MemberInfoDTO midto = new MemberInfoDTO();

			midto.setNickname(nickname);
			midto.setImage(filename);
			midto.setCategory(category);
			midto.setDdayname(ddayname);
			midto.setDdate(ddate);

			miresult = dao.registerMemberInfo(midto);

			System.out.println(midto);

		} catch (Exception e) {
			System.out.println("RegisterOk.doPost");
			e.printStackTrace();
		}

		if (mresult == 1 && miresult == 1) {

			MemberDAO dao = new MemberDAO();
			PlusRewardDAO pdao = new PlusRewardDAO();

			int prResult = pdao.registerPoint();
			pdao.updatePoint();
			dao.optionCreate();

			resp.sendRedirect("login.do");
		} else {
			
			PrintWriter writer = resp.getWriter();

			writer.print("<script>");
			writer.print("alert('failed');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();
		}

	}

}