package cn.tedu.domain;
/**
 * 封装订单项（订单与商品的关联信息）的javabean
 * @author Administrator
 *
 */
public class orderItem {
	private String orderId;//订单id
	private String prodId;//商品id
	private int buyNum;//购买数量
	
	public orderItem() {
		super();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	
	
}
