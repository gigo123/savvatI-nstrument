<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuView.jsp"%>
<div class="col-6">
	создание нового интсрумента
	${errorText}
	<form:form  action="./addlocation" method="post" class="form form--account">
		<div class="row mb--20">
			<div class="col-12">
				<div class="form__group">
					<form:label path = "name" class="form__label" for="login">
					Название места хранения
					 <span class="required">*</span>
					</form:label>
					<form:input path ="name" type="text" name="name" id="name" class="form__input" 
					required="true"  />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="form__group">
				
					<form:label  path = "boxes" class="form__label checkbox-label" for="boxes">
					<span>Есть ячейки</span>
						<form:checkbox path = "boxes"  name="boxes" id="boxes"/>
					</form:label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="form__group">
					<input type="submit" value="создать место хранения" class="btn btn-size-md"/>
				</div>
			</div>
		</div>
	</form:form >
	</div>
<!--  close div of SideMenuView -->
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/include/FooterView.jsp"%>