<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuDocView.jsp"%>
<div class="col-9">
	отчет об месте хранения
	<form:form action="./locationReport" method="post"
		modelAttribute="reportSettings">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<div class="row mb--20">
			<div class="col-3">виберете место хранения</div>
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
		<div class="row mb--20">
		<div class="col-4">
		название
		</div>
		<div class="col-2">
		количество
		</div>
		<div class="col-2">
		ед измирения
		</div>
		</div>
		<div class="row mb--20">
			<div class="col-4">
				<form:label path="name">
					${locReport.name}
					</form:label>
			</div>
			<div class="col-2">
				<form:label path="totalAmount">
					${locReport.totalAmount}
					</form:label>
			</div>
		</div>
			<c:forEach items="${locReport.reportBox}" varStatus="i">
			<div class="row mb--20">
			
				<div class="col-4">
					<form:label path="reportBox[${i.index }].name">
					${locReport.reportBox[i.index].name}
					</form:label>
				</div>

				<div class="col-2">
					<form:label path="reportBox[${i.index }].totalAmount">
					${locReport.reportBox[i.index].totalAmount}
					</form:label>
				</div>

			</div>
			<c:forEach items="${locReport.reportBox[i.index].reportItems}" varStatus="j">
			<div class="row mb--20">
			
				<div class="col-4">
					<form:label path="reportBox[${i.index}].reportItems[${j.index}].name">
					${locReport.reportBox[i.index].reportItems[j.index].name}
					</form:label>
				</div>

				<div class="col-2">
					<form:label path="reportBox[${i.index}].reportItems[${j.index}].amount">
					${locReport.reportBox[i.index].reportItems[j.index].amount}
					</form:label>
				</div>
					<div class="col-2">
					<form:label path="reportBox[${i.index}].reportItems[${j.index}].measure">
					${locReport.reportBox[i.index].reportItems[j.index].measure}
					</form:label>
				</div>
			</div>
			</c:forEach>
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