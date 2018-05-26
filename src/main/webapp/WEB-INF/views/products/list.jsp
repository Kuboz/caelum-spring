<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cdc"%>
<cdc:page title="Listagem de Produtos">
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user" />
		 Olá ${user.name}
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<c:url value="/products/form" var="formLink" />
		<a href="${formLink}"> Cadastrar novos produtos</a>
	</sec:authorize>
	<h1>${success}</h1>
	<table>
		<tr>
			<th>Titulo</th>
			<th>Nº Paginas</th>
			<th>Descrição</th>
			<th>Preços</th>
		</tr>
		<c:forEach items="${products}" var="product" varStatus="status">
			<tr>
				<td>${product.title }</td>
				<td>${product.numberOfPages}</td>
				<td>${product.description}</td>
				<td>
					<table>
						<c:forEach items="${product.prices}" var="price" varStatus="">
							<tr>
								<td>${price.value}</td>
								<td>${price.bookType}</td>
								<td><c:url value="/products/${product.id}"
										var="linkDetalhar" /> <a href="${linkDetalhar}">Detalhar</a></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</c:forEach>
	</table>
</cdc:page>