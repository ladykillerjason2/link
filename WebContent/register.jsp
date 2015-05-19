<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.Calendar" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Calendar cal=Calendar.getInstance();
	int n=cal.get(Calendar.YEAR);
	//out.print(n);
%>
	<form action="register" method="post">
		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>确认密码</td>
				<td><input type="password" name="password2"></td>
			</tr>
			<tr>
				<td><input type="radio"  name="sex"  value="女">女</td>
				<td><input type="radio"  name="sex" value="男" checked="true">男 </td>
			</tr>
			
			<tr>
				<td>邮箱</td>
				<td><input type="text" name="email" ></td>
			</tr>
			<tr>
				<td>手机</td>
				<td><input type="text" name="phone" ></td>
			</tr>
			<tr>
				<td>job</td>
				<td><input type="text" name="job" ></td>
			</tr>
			<tr>
				<td colspan="2">
					<select name="year">
					<option value="年" checked="true">年</option>
					<%
					int year=n;
						while(year>=1900){
					%>
							<option value="<%=year%>"><%=year %></option>
					<%
							year--;
						}
					%>
					</select>
					
					<select name="month">
					<option value="月" checked="true">月</option>
					<%
						int month=12;
						while(month>=1){
					%>
							<option value="<%=month%>"><%=month%></option>
					<%
						month--;
						}
					%>
					</select>
					
					<select name="day">
					<option value="日" checked="true">日</option>
					<%
						int day=31;
						while(day>=1){
					%>
							<option value="<%=day%>"><%=day%></option>
					<%
						day--;
						}
					%>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><input type="submit" value="提交"></td>
				<td><input type="reset"  value="重置" ></td>
			</tr>
	</table>
	</form>
	
</body>
</html>