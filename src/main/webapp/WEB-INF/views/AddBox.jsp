<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuView.jsp"%>
<div class="col-6">
	создание новой ячейки ${errorText}
	<form:form action="./addinstument" method="post"
		class="form form--account">
		<div class="row mb--20">
			<div class="col-12">
				<div class="form__group">
					<form:label path="number" class="form__label" for="login">
					номер ячейки
					 <span class="required">*</span>
					</form:label>
					<form:input path="number" type="text" name="name" id="name"
						class="form__input" required="true" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-3">
				<form:label path="locationWB">место хранения</form:label>
			</div>
			<div class="col-3">
				<form:select path="locationWB">
					<form:option value="NONE" label="Select" />
					<form:options items="${locationWB}" />
				</form:select>
			</div>
		</div>

		<div class="row">
			<div class="col-12">
				<div class="form__group">
					<input type="submit" value="создать ячейку"
						class="btn btn-size-md" />
				</div>
			</div>
		</div>
	</form:form>
</div>
<!--  close div of SideMenuView -->
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/include/FooterView.jsp"%>