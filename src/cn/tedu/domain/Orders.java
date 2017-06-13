package cn.tedu.domain;

import java.util.Date;

/**
 * 订单信息javabean
 * @author Administrator
 *
 */
public class Orders {
	private String id;//订单id
	private double money;//订单金额
	private String receiverInfo;//收货人详细信息
	private int payState;//支付状态，0表示为支付，1表示已支付
	private Date orderTime;//订单添加时间
	private int userId;//用户id
	
	public Orders() {
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
