<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuView.jsp"%>
<div class="col-6">
	создание нового документа премещения
	<div class="table-content table-responsive">
		<table class="table text-center">
			<thead>
				<tr>
					<th>&nbsp;</th>
					<th class="text-left">номер</th>
					<th>место видачи</th>
					<th>ячека видачи</th>
					<th>место приема</th>
					<th>ячейка приема</th>
					<th>инструмент</th>
					<th>количество</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${requestScope.productList}">
					<tr id="productRow${product.key.id}">
						<td class="product-thumbnail text-left"><img
							src="<c:url value="./resources/productImage/${product.key.id}.JPG"/>"
							alt="Product Thumnail"></td>
						<td class="product-name text-left wide-column">
							<h3>
								<a href="product-details.html">${product.key.name}</a>
							</h3>
						</td>
						<td class="product-price"><span class="product-price-wrapper">
								<span class="money" id="price${product.key.id}">${product.key.price}</span>
						</span></td>
						<td class="product-quantity"><img width="20px"
							src="<c:url value="./resources/img/-.png"/>" id="imgMinus"
							onclick="minus(${product.key.id})" /> <span
							id="span${product.key.id}">${product.value}</span> <img
							width="20px" src="<c:url value="./resources//img/+.png"/>"
							id="imgPlus" onclick="plus(${product.key.id})" /></td>
						<td class="product-total-price"><span
							class="product-price-wrapper"> <span class="money"
								id="totalPrice${product.key.id}">${product.value*product.key.price}</span>
						</span></td>
						<td class="product-remove text-left"><input type="hidden"
							name="productToRemove" value="${product.key.id}" /> <input
							type="submit" value="remove"
							onclick="removeProduct(${product.key.id})"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="row no-gutters border-top pt--20 mt--20">
		<div class="col-sm-6 text-sm-right">
			<button type="submit" class="cart-form__btn">Clear Cart</button>
		</div>
	</div>
	<div class="cart-collaterals">
		<div class="cart-totals">
			<h5 class="font-size-14 font-bold mb--15">Cart totals</h5>
			<div class="cart-calculator__item order-total">
				<div class="cart-calculator__item--head">
					<span>Total</span>
				</div>
				<div class="cart-calculator__item--value">
					<span class="product-price-wrapper"> <span class="money"
						id="totalSum">${totalSumm}</span>
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="btn btn-size-md btn-shape-square btn-fullwidth"
		id="checkOut" onclick="confirmOrder()">Proceed To Checkout</div>
</div>
</div>
<!--  close div of SideMenuView -->
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/include/FooterView.jsp"%>