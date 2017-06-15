package cn.tedu.domain;
/**
 * 封装订单项（订单与商品的关联信息）的javabean
 * @author Administrator
 *
 */
public class OrderItem {
	private String order_id;//订单id
	private String product_id;//商品id
	private int buyNum;//购买数量
	
	public OrderItem() {
		super();
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	
}
