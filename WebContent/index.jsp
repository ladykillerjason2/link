<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.jason.util.*" %>
 <%@ page import="java.sql.*"%>
 <%@ page import="java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>link:登陆</title>
</head>
<body>
<%-- <%
	Calendar cal=Calendar.getInstance();
	int n=cal.get(Calendar.YEAR);
	out.print(n);
%> --%>
<%
	try{
		String sql="select * from user";
		Statement sta=DBUtil.getConnection().createStatement();
		ResultSet rs=sta.executeQuery(sql);
		rs.last();
		out.print(rs.getString("name"));
		out.print("<BR>");
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		DBUtil.closeConnection();
	}
%>
	<p>我是杨叶</p>
	<p>我是杨森</p>
	<form action="login"  method="post">
		<table>
			<tr>
				<td>用户ID</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>用户密码</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td><input type="submit"  value="提交"></td>
				<td><a text-decoration="none"  href="register.jsp">注册</a></td>
			</tr>
		</table>
	</form>
	
</body>
</html>
