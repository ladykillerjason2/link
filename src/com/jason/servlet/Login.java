package com.jason.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.jason.util.*;

import java.sql.*;

import com.jason.bean.UserBean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		// TODO Auto-generated method stub
		
		UserBean ub=new UserBean();
		HttpSession ss=request.getSession();
		ss.setAttribute("userbean", ub);
		String id=request.getParameter("id").trim();
		String password=request.getParameter("password"). trim();
		try {
			Statement sta=DBUtil.getConnection().createStatement();
			PreparedStatement ps=DBUtil.getConnection().prepareStatement("select * from user where id = ?");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				if(password.equals(rs.getString("password"))){
					ub.setId(id);                                                //装填bean
					ub.setName(rs.getString("name"));
					ub.setEmail(rs.getString("email"));
					ub.setJob(rs.getString("job"));
					ub.setSex(rs.getString("sex"));
					ub.setPhone(rs.getString("phone"));
					
					ss.setAttribute("id", id);                           //装填session
					ss.setAttribute("name", rs.getString("name"));
					ss.setAttribute("password", rs.getString("password"));
					
					RequestDispatcher dis=request.getRequestDispatcher("loginSucceed.jsp");
					dis.forward(request, response);
				}else{
					//JOptionPane.showMessageDialog(null, "密码错误","错误对话框",JOptionPane.ERROR_MESSAGE);
					PrintWriter out=response.getWriter();
					RequestDispatcher dis=request.getRequestDispatcher("index.jsp");
					dis.forward(request, response);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
