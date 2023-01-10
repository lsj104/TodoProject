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

@WebServlet("/member/mypageeditok.do")
public class MyPageEditOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// MyPageEditOk.java

		HttpSession session = req.getSession();

		req.setCharacterEncoding("UTF-8");

		String path = req.getRealPath("/asset/images/profile");

		int size = 1024 * 1024 * 10;

		String inputUsername = "";
		String filename = "";
		String inputConfirmPassword = "";

		int result = 0;
		int result2 = 0;
		int result3 = 0;

		try {
			
			MultipartRequest multi = new MultipartRequest(req, path, size, "UTF-8", new DefaultFileRenamePolicy()

			);
			
			filename = multi.getFilesystemName("profile");
			inputUsername = multi.getParameter("inputUsername");
			inputConfirmPassword = multi.getParameter("inputConfirmPassword1");
			
			MemberDAO dao = new MemberDAO();
			
			String seq = (String) session.getAttribute("seq");
			
			int isDuplicate = dao.isDuplicate(inputUsername, seq);
			
			if (isDuplicate == 0) {
				
				if (filename == null) {
					filename = (String) session.getAttribute("image"); // 변경 전 프로필 사진
				}
				
				System.out.println("변경 닉네임: " + inputUsername);
				System.out.println("변경 비밀번호: " + inputConfirmPassword);
				System.out.println("변경 이미지: " + filename);

				result = dao.editPw(seq, inputConfirmPassword);
				result2 = dao.editNickname(seq, inputUsername);
				result3 = dao.editImage(seq, filename);
				
			}
			
		} catch (Exception e) {
			System.out.println("MyPageEditOk.doPost");
			e.printStackTrace();
		}
		
		if (result == 1 || result2 == 1 || result3 == 1) { // 성공

			session.setAttribute("pw", inputConfirmPassword);
			session.setAttribute("nickname", inputUsername);
			session.setAttribute("image", filename);

			resp.sendRedirect("/todo/member/mypageedit.do");

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
