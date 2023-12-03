package foodordersystem.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import foodordersystem.Enum.OrderStatus;
import foodordersystem.Enum.OrderType;
import foodordersystem.Enum.RefundStatus;

public class Order {
	private int id;
	private int invoiceId;
	private int customerId;
	private int vendorId;
	private String address;
	private LocalDateTime date;
	private OrderType type;
	private RefundStatus refund;
	private OrderStatus status;

	public Order(
		int id,
		int invoiceId,
		int customerId,
		int vendorId,
		String address,
		LocalDateTime date,
		OrderType type,
		RefundStatus refund,
		OrderStatus status
	) {
		this.id = id;
		this.invoiceId = invoiceId;
		this.customerId = customerId;
		this.vendorId = vendorId;
		this.address = address;
		this.date = date;
		this.type = type;
		this.refund = refund;
		this.status = status;
	}

	public Order () {

	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public LocalDateTime setDate(LocalDateTime date) {
		return this.date = date;
	}

	public LocalDateTime getDate() {
		return date;
	}
	public void setOrderType(OrderType type) {
		this.type = type;
	}

	public OrderType getOrderType() {
		return type;
	}

	public void setRefundStatus(RefundStatus refund) {
		this.refund = refund;
	}

	public RefundStatus getRefundStatus() {
		return refund;
	}

	public void setOrderStatus(OrderStatus status) {
		this.status = status;
	}

	public OrderStatus getOrderStatus() {
		return status;
	}

	public ArrayList<Object[]> getOrderItemsWithMenuList() {
		ArrayList<Object[]> orderMenuList = new ArrayList<Object[]>();
		for (OrderItem orderItem : DataIO.allOrderItems) {
            if (orderItem.getOrderId() == this.id) {
				for (Menu menu : DataIO.allMenus) {
					if (orderItem.getMenuId() == menu.getId()) {
						Object[] itemDetails = new Object[2];
						itemDetails[0] = menu;
						itemDetails[1] = orderItem.getQuantity();
						orderMenuList.add(itemDetails);
					}
				}
			}
		}
		return orderMenuList;
	}
}
