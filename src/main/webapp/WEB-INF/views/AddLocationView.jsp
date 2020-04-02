<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuView.jsp"%>
<div class="col-6">
	${errorText}
	создание нового места хранения
	<form action="./addlocation" method="post" class="form form--account">
		<div class="row mb--20">
			<div class="col-12">
				<div class="form__group">
					<label class="form__label" for="login">
					Название места хранения
					 <span class="required">*</span>
					</label> <input type="text" name="name" id="name" class="form__input"
						required  value="${user.login}">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="form__group">
				
					<label class="form__label checkbox-label" for="hasbox">
					<span>Есть ячейки</span>
						<input type="checkbox" name="hasbox" id="hasbox">
					</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="form__group">
					<input type="submit" value="создать место хранения" class="btn btn-size-md">
				</div>
			</div>
		</div>
	</form>
	</div>
<!--  close div of SideMenuView -->
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/include/FooterView.jsp"%>