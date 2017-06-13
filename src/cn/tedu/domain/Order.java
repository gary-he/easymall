package cn.tedu.domain;

import java.util.Date;
/**
 * 封装订单信息的javabean
 * @author Administrator
 *
 */
public class Order {
	private String id;//订单id
	private double money;//订单金额
	private String receiverInfo;//收货人详细信息
	private int payState;//支付状态，0：未支付，1:已支付
	private Date orderTime;//订单提交时间
	private int userId;//用户id
	
	public Order() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(String receiverInfo) {
		this.receiverInfo = receiverInfo;
	}

	public int getPayState() {
		return payState;
	}

	public void setPayState(int payState) {
		this.payState = payState;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
