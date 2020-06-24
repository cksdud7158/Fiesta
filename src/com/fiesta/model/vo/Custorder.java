package com.fiesta.model.vo;

public class Custorder {
	int orderCode;
	String orderSysdate;
	String orderRevdate;
	String orderPlace;
	int orderBudget;
	String orderRequire;
	String orderService;
	String custId;
	
	public Custorder() {}
	public Custorder(int orderCode, String orderSysdate, String orderRevdate, String orderPlace, int orderBudget,
			String orderRequire, String orderService, String custId) {
		this.orderCode = orderCode;
		this.orderSysdate = orderSysdate;
		this.orderRevdate = orderRevdate;
		this.orderPlace = orderPlace;
		this.orderBudget = orderBudget;
		this.orderRequire = orderRequire;
		this.orderService = orderService;
		this.custId = custId;
	}

	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderSysdate() {
		return orderSysdate;
	}
	public void setOrderSysdate(String orderSysdate) {
		this.orderSysdate = orderSysdate;
	}
	public String getOrderRevdate() {
		return orderRevdate;
	}
	public void setOrderRevdate(String orderRevdate) {
		this.orderRevdate = orderRevdate;
	}
	public String getOrderPlace() {
		return orderPlace;
	}
	public void setOrderPlace(String orderPlace) {
		this.orderPlace = orderPlace;
	}
	public int getOrderBudget() {
		return orderBudget;
	}
	public void setOrderBudget(int orderBudget) {
		this.orderBudget = orderBudget;
	}
	public String getOrderRequire() {
		return orderRequire;
	}
	public void setOrderRequire(String orderRequire) {
		this.orderRequire = orderRequire;
	}
	public String getOrderService() {
		return orderService;
	}
	public void setOrderService(String orderService) {
		this.orderService = orderService;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return "Custorder [orderCode=" + orderCode + ", orderSysdate=" + orderSysdate + ", orderRevdate=" + orderRevdate
				+ ", orderPlace=" + orderPlace + ", orderBudget=" + orderBudget + ", orderRequire=" + orderRequire
				+ ", orderService=" + orderService + ", custId=" + custId + "]";
	}
}
