package com.jason.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import com.jason.util.DBUtil;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		PrintWriter out=response.getWriter();
		String idStr="";                          //需要自己从数据库中取出来
		boolean isSame=false;        //  用于判断该邮箱是否已经注册过账户
		String username=request.getParameter("username").trim();
		String password=request.getParameter("password").trim();
		String password2=request.getParameter("password2").trim();
		String sex=request.getParameter("sex").trim();
		String email=request.getParameter("email").trim();
		String phone=request.getParameter("phone");
		String job=request.getParameter("job").trim();
		String year=request.getParameter("year").trim();
		String month=request.getParameter("month").trim();
		String day=request.getParameter("day").trim();
		
		try{
			String sql="select * from user";
			Statement sta=DBUtil.getConnection().createStatement();
			ResultSet rs=sta.executeQuery(sql);
			rs.last();
			idStr=rs.getString("id");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection();
		}
		
		try{
			String sql="select * from user";
			Statement sta=DBUtil.getConnection().createStatement();
			ResultSet rs=sta.executeQuery(sql);
			while(rs.next()){
				if(rs.getString("email").equals(email)){
					isSame=true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection();
		}
		
		if(username==null||password==null||password2==null||sex==null||email==null||phone==null||job==null||year==null||month==null||day==null
				||username.equals("")||password.equals("")||password2.equals("")||sex.equals("")||email.equals("")||phone.equals("")||job.equals("")||year.equals("")||month.equals("")||day.equals("")){
			
			out.print("The register info you have input is not full,please try again");
			RequestDispatcher dis=request.getRequestDispatcher("register.jsp");
			dis.forward(request, response);
		}else if(!password.equals(password2)){
			out.print("This two password you have input is not equal,please try again");
			RequestDispatcher dis=request.getRequestDispatcher("register.jsp");
			dis.forward(request, response);
		}else if(isSame){
			out.print("This email has been used for another account ,please try another email address");
			RequestDispatcher dis=request.getRequestDispatcher("register.jsp");
			dis.forward(request, response);
		}else{
			int idInt=Integer.parseInt(idStr);
			idInt++;
			idStr=String.valueOf(idInt);
			try {
				PreparedStatement ps=DBUtil.getConnection().prepareStatement("insert into user values(?,?,?,?,?,?,?)");
				ps.setString(1, idStr);
				ps.setString(2, username);
				ps.setString(3, sex);
				ps.setString(4, phone);
				ps.setString(5, email);
				ps.setString(6, job);
				ps.setString(7, password);
				ps.executeUpdate();   //至关重要的最后一步
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtil.closeConnection();
			}
			
			RequestDispatcher dis=request.getRequestDispatcher("welcome.jsp");
			dis.forward(request, response);
		}
	}
}
