<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cdc"%>
<cdc:page title="Formulario de produtos">
	<form:form action="${spring:mvcUrl('PC#save').build()}" method="post" 
		commandName="product" enctype="multipart/form-data">
		<div>
			<label for="title">Título</label>
			<form:input path="title" id="title"/>
			<form:errors path="title"/>
        </div>
        <div>
            <label for="description">Descrição</label>
            
			<form:textarea path="description" rows="10" cols="20" 
				id="description"/>
			<form:errors path="description"/>
			
       	</div>
        <div>
            <label for="numberOfPages">Número de páginas</label>
            
			<form:input path="numberOfPages" id="numberOfPages"/>
			<form:errors path="numberOfPages"/>
        </div>
        <div>
        	<c:forEach items="${types}" var="bookType" varStatus="status">
        		<div>
        			<label for="price_${bookType}">${bookType}</label>
        			<input type="text" name="prices[${status.index}].value" 
        				id="price_${bookType}"/>
        				
        			<input type="hidden" name="prices[${status.index}].bookType"
        				value="${bookType}"/>
        		</div>
        	</c:forEach>
        </div>
        <div>
        	<label for="releaseDate">Data de lançamento</label>
			<form:input path="releaseDate" id="releaseDate"/>
			<form:errors path="releaseDate"/>
        </div>
        <div>
        	<label for="summary">Sumario do livro</label>
        	<input type="file" name="summary" id="summary" />
        	<form:errors path="summaryPath"/>
        </div>
        <div>
            <input type="submit" value="Enviar"/>
        </div>
	</form:form>
</cdc:page>