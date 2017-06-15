package cn.tedu.dao;

import java.util.List;

import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;
import cn.tedu.domain.Product;
import cn.tedu.service.Service;

/**
 * 操作订单信息的dao
 * @author Administrator
 *
 */
public interface OrderDao extends Dao {
	/**
	 * 向order表添加一条记录
	 * @param order
	 */
	public void addOrder(Order order);
	/**
	 * 向orderitem表添加一条记录
	 * @param oi
	 */
	public void addOrderItem(OrderItem oi);
	/**
	 * 根据id获取对应用户的所有订单
	 * @param uid
	 * @return
	 */
	public List<Order> findOrdersByUserId(int uid);
	/**
	 * 根据id获取对应用户的所有订单项 
	 * @param uid
	 * @return
	 */
	public List<OrderItem> findOrderItemsByUserId(int uid);
	/**
	 * 根据id获取对应用户所有订单项的商品
	 * @param uid
	 * @return
	 */
	public List<Product> findProdsByUserId(int uid);
	
}
