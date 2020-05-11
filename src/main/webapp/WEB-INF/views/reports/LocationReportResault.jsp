<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuReportView.jsp"%>
<div class="col-9">
	отчет об месте хранения
	<form:form action="./locationReport" method="post"
		modelAttribute="reportSettings">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<div class="row mb--20">
				<div class="col-3">виберете место хранения</div>
				<div class="col-2">
					<form:select path="locationId">
						<form:options items="${locationList}" />
					</form:select>
				</div>
				<div class="col-2">
					<form:label path="box" class="form__label checkbox-label" for="box">
						<span>по ячейке</span>
						<form:checkbox path="box" name="box" id="box"
							onchange="searchBox()" />
					</form:label>
				</div>
				<div class="col-2">
					<form:select path="boxId">
					</form:select>
				</div>
				<div>
					<input type="submit" value="создать отчет" class="btn btn-size-md" />
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
<script>
	function searchBox() {
		var list= document.getElementById("locationId");	
			var search = {};
		search["boxId"] = list.options[list.selectedIndex].value;
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "./report/getBoxFilter",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				//display(data);
				var boxmap = new Map(Object.entries(data.boxListId))
				var select  = document.getElementById("boxId");
				select.options.length=0;
				for (var [key, value] of boxmap) {
					console.log(key + ' = ' + value);
					var option = document.createElement("option");
					option.value = key,
					option.text =value;
					select.add(option);	
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
				//display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});

	}
	</script>
<!--  close div of SideMenuView -->
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/include/FooterView.jsp"%>