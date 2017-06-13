package cn.tedu.dao;

import cn.tedu.domain.Order;
import cn.tedu.domain.orderItem;

/**
 * 操作订单信息的dao
 * @author Administrator
 *
 */
public interface OrderDao {
	/**
	 * 向order表添加一条记录
	 * @param order
	 */
	public void addOrder(Order order);
	/**
	 * 向orderitem表添加一条记录
	 * @param oi
	 */
	public void addOrderItem(orderItem oi);

}
