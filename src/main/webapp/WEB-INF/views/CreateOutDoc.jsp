<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuView.jsp"%>
<div class="col-9">
	создание нового документа премещения
	<div class="table-content table-responsive">
		<form:form action="./createOutDoc" method="post"
			modelAttribute="exDocWEBList">
			<form:errors path="*" cssClass="errorblock" element="div" />
			<table class="table text-center">
				<thead>
					<tr>
						<th class="text-left">номер</th>
						<th>место видачи</th>
						<th>ячека видачи</th>
						<th>инструмент</th>
						<th>количество</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${exDocWEBList.docList}" varStatus="i">
						<tr id="docRow${i.index}">
							<td class="doc-id">${i.index+1}</td>
							<td class="doc-out-loc text-left" id="doc-out-loc-${i.index}">
								<form:select path="docList[${i.index }].outLocation">
									<form:options items="${locationList}" />
								</form:select>
							</td>
							<td class="doc-out-box text-left wide-column"><form:select
									path="docList[${i.index }].outBox">
								</form:select></td>
							<td class="doc-instrum" id="doc-instrum-${i.index}"><form:select
									path="docList[${i.index }].instrument">
									<form:options items="${instrumentMap}" />
								</form:select></td>
							<td class="doc-amount text-left"><form:input
									path="docList[${i.index }].amount" id="amount-${i.index}"
									class="form__input" required="true" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>

	<input type="submit" value="создать документ" class="btn btn-size-md" />

	</form:form>
	<div id="feedback"></div>
	<input type="submit" class="btn btn-size-md" id="searchBox"
		value="searchBox" onclick="searchBox()" /> <input type="submit"
		class="btn btn-size-md" id="searchInstrument" value="searchInstrument"
		onclick="searchInstrum()" /> <input type="submit"
		class="btn btn-size-md" id="addRow" value="add row" onclick="addRow()" />

</div>

<script>
	
	function searchBox() {
		var counter = 0;
		var list = document.getElementById("docList0.outLocation");
		var search = {};
		let boxIndex = new Array();
		while (list != null) {
			var search = {}
			var id = list.options[list.selectedIndex].value;
			boxIndex.push(id);
			counter = counter + 1;
			list = document.getElementById("docList" + counter + ".outLocation");
		}
		search["boxId"] = boxIndex;
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "./createOutDoc/getBoxFilter",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
			//	display(data);
				
			for (var i = 0, j = data.boxListId.length; i < j; i += 1) {   
				var boxmap = new Map(Object.entries(data.boxListId[i].BoxMap))
			//for (var key of myMap.keys()) {
  //console.log(key);
//}
//for (var value of myMap.values()) {
 // console.log(value);
//}
				var select = document.getElementById("docList" + i +".outBox");
				select.options.length=0;
				for (var [key, value] of boxmap) {
			console.log(key + ' = ' + value);
				
					var option = document.createElement("option");
					option.value = key,
					option.text =value;
					select.add(option);	
				}
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

	function enableSearchButton(flag) {
		$("#btn-search").prop("disabled", flag);
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