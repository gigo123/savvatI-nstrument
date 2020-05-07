<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuDocView.jsp"%>
<div class="col-9">
	отчет об месте хранения
	<form:form action="./locationReport" method="post" modelAttribute="reportSettings">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<div class="row mb--20">
			<div class="col-1">
			виберете место хранения
			</div>
			<div class="col-2">
					<form:select path="locationId"
						onchange="searchOutBoxNE(${i.index })">
						<form:options items="${locationList}" />
					</form:select>
				</div>
				<div class="col-2">
				<div class="form__group">
					<input type="submit" value="создать отчет" class="btn btn-size-md" />
				</div>
			</div>
		</div>
		</form:form>
		<form:form action="./locationReport" method="post"
		modelAttribute="locReport">
		<c:forEach items="${locReport}" varStatus="i">
			<div class="row mb--20">
			
				<div class="col-2">
					<form:label path="locReport[${i.index }].name;">
					${locReport[i.index].name}
					</form:label>
				</div>

				<div class="col-2">
					<form:label path="locReport[${i.index }].totalAmount;">
					{locReport[i.index].totalAmount}
					</form:label>
				</div>
			</div>
		</c:forEach>
	</form:form>
	<div id="feedback"></div>
</div>
<!--  close div of SideMenuView -->
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/include/FooterView.jsp"%>