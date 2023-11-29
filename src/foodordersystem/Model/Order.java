package foodordersystem.Model;

public class Order {
	private int id;
	private int orderDetailId;
	private int invoiceId;
	private int customerId;
	private String address;
	private String date;
	private OrderType type;
	private RefundStatus refund;
	private OrderStatus status;

	/**
	 * Constructor
	 * @param id
	 * @param invoiceId
	 * @param orderDetailId
	 * @param customerId
	 * @param address
	 * @param date
	 * @param type
	 * @param refund
	 * @param status 
	 */
	public Order(
		// int id,
		int orderDetailId,
		int invoiceId,
		int customerId,
		String address,
		String date,
		OrderType type,
		RefundStatus refund,
		OrderStatus status
	) {
		this.orderDetailId = orderDetailId;
		this.invoiceId = invoiceId;
		this.customerId = customerId;
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

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	
	public int getOrderDetailId() {
		return orderDetailId;
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

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
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
}
