<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cdc"%>
<cdc:page title="Login">
	<h3>Efetuar Login</h3>
	<form:form servletRelativeAction="/login">
		<label>Login:</label>
		<input type="text" name="username" />
		</br>
		<label>Senha:</label>
		<input type="password" name="password" />
		</br>
		<button>Logar</button>
	</form:form>
</cdc:page>