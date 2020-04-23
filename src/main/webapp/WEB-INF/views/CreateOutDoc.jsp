<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuView.jsp"%>
<div class="col-9">
	создание нового документа премещения
	<form:form action="./createOutDoc" method="post"
		modelAttribute="exDocWEBList">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<div class="row mb--20">
			<div class="col-1">номер</div>
			<div class="col-2">место видачи</div>
			<div class="col-1">ячека видачи</div>
			<div class="col-3">инструмент</div>
			<div class="col-2">количество</div>
			<div class="col-1">удалить</div>
		</div>

		<c:forEach items="${exDocWEBList.docList}" varStatus="i">
			<div class="row mb--20">
				<div class="col-1">${i.index+1}</div>
				<div class="col-2">
					<form:select path="docList[${i.index }].outLocation"
						onchange="searchOutBox(${i.index })">
						<form:options items="${locationList}" />
					</form:select>
				</div>

				<div class="col-1">
					<form:select path="docList[${i.index }].outBox"
						onchange="searchInstrum(${i.index })">
					</form:select>
				</div>
				<div class="col-3">
					<form:select path="docList[${i.index }].instrument">
						<form:options items="${instrumentMap}" />
					</form:select>
				</div>
				<div class="col-2">
					<form:input path="docList[${i.index }].amount"
						id="amount-${i.index}" class="form__input" required="true" />
				</div>
				<div class="col-1">
					<input type="button" value="remove"
						onclick="removeRow(${i.index })">
				</div>
			</div>
		</c:forEach>



		<input type="submit" value="создать документ" class="btn btn-size-md" />
		<input class="btn btn-size-md" id="searchInstrument" value="add row"
			onclick="addRow()" />
	</form:form>
	<div id="feedback"></div>

</div>

<script>
	
	function searchOutBox(id) {
		var list= document.getElementById("docList" + id + ".outLocation");	
		var search = {};
		search["boxId"] = list.options[list.selectedIndex].value;
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "./createExDoc/getBoxFilter",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				//display(data);
				var boxmap = new Map(Object.entries(data.boxListId))
				var select  = document.getElementById("docList" + id +".outBox");
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
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});

	}


	function searchInstrum() {
		var search = {}
		var id = document.getElementById("docList0.outBox").value
		search["boxId"] = id;
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "./createOutDoc/getInstrumentFilter",
			data : JSON.stringify(search),
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				//display(data);
				   
			var instrumentMap = new Map(Object.entries(data.InstrumentMapId));
				var select = document.getElementById("docList0.instrument");
				select.options.length=0;
				for (var [key, value] of instrumentMap) {
			console.log(key + ' = ' + value);
					var option = document.createElement("option");
					option.value = key,
					option.text =value;
					select.add(option);	
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});

	}

	function addRow() {

		window.location.href = "./createOutDoc?addRow=1";
	}
	
function removeRow(id) {
		window.location.href = "./createExDoc?removeRow=" + id;
			
	}

	function display(data) {
		var json = "<h4>Ajax Response</h4><pre>"
				+ JSON.stringify(data, null, 4) + "</pre>";
		$('#feedback').html(json);
	}
</script>

</div>
<!--  close div of SideMenuView -->
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/include/FooterView.jsp"%>