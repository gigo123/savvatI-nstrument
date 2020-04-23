<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuOperationView.jsp"%>
<div class="col-6">
	создание новой ячейки
	<form:form action="./addbox" method="post"
		modelAttribute="boxListLocation" class="form form--account">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<div class="row mb--20">
			<div class="col-12">
				<form:label path="manyBox">создание диапазона ячеек</form:label>
				<form:radiobutton path="manyBox" value="O" label="одна" />
				<form:radiobutton path="manyBox" value="M" label="диапазон" />
			</div>
		</div>
		<div class="row mb--20">
			<div class="col-12">
				<div class="form__group">
					<form:label path="number" class="form__label" for="login">
					номер ячейки
					</form:label>
					<form:input path="number" type="text" name="name" id="name"
						class="form__input" required="true" />
					<form:errors path="number" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-3">
				<form:label path="locationWB">место хранения</form:label>
			</div>
			<div class="col-3">
				<form:select path="locationWB">
					<form:option value="none" label="вибрать место" />
					<form:options items="${locationWB}" />
				</form:select>
				<form:errors path="locationWB" />
			</div>
		</div>	
		<div class="row">
			<div class="col-3">
				<form:label path="startNum" class="form__label" for="login">
					начальная ячейка
					</form:label>
				<form:input path="startNum" type="text" name="name" id="name"
					class="form__input" />
			</div>
			<div class="col-3">
				<form:label path="endNum" class="form__label" for="login">
					конечная ячейка
					</form:label>
				<form:input path="endNum" type="text" name="name" id="name"
					class="form__input" />
			</div>

		</div>
		<div class="row">
			<div class="col-12">
				<div class="form__group">
					<input type="submit" value="создать ячейку" class="btn btn-size-md" />
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