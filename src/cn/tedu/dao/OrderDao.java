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
	/**
	 * 根据订单id从orders表中查询对应的订单信息
	 * @param oid
	 * @return 对应订单信息对象
	 */
	public Order findOrderByOid(String oid);
	/**
	 * 根据订单id从orderitem表中查询对应订单下的所有订单项
	 * @param oid
	 * @return 订单项集合
	 */
	public List<OrderItem> findOrderItemsByOid(String oid);
	/**
	 * 根据订单id从orderitem表中删除对应订单下的所有订单项
	 * @param oid
	 */
	public boolean deleteOrderItemByOid(String oid);
	/**
	 * 根据订单id从orders表中删除对应订单信息
	 * @param oid
	 */
	public boolean deleteOrderByOid(String oid);
	/**
	 * 根据订单id查询订单信息（添加悲观锁版本）
	 * @param oid
	 * @return
	 */
	public Order findOrderByOidForUpdate(String oid);
	/**
	 * 修改订单的支付状态
	 * @param oid
	 * @param paystate
	 */
	public void updatePaystate(String oid, int paystate);
	
}
