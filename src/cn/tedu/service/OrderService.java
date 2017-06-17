package cn.tedu.service;

import java.util.List;

import cn.tedu.anno.Tran;
import cn.tedu.domain.Order;
import cn.tedu.domain.OrderInfo;
import cn.tedu.domain.OrderItem;
import cn.tedu.exception.MsgException;
/**
 * 操作订单的service
 * @author Administrator
 *
 */
public interface OrderService extends Service {
	/**
	 * 添加订单
	 * @param order 订单信息javabean
	 * @param oiList 订单项javabean
	 */
	@Tran
	public void addOrder(Order order, List<OrderItem> oiList) throws MsgException;
	
	/**
	 * 根据id获取对应用户的所有订单信息
	 * @param uid
	 * @return
	 */
	public List<OrderInfo> getOrderInfoByUserId(int uid);
	/**
	 * 根据订单id删除订单相关信息（从order表中删除一条记录，修改对应商品的库存，删除orderitem表中对应的订单项）
	 * @param oid 订单id
	 * @throws MsgException 删除非未支付的订单抛出异常
	 */
	public void deleteOrderInfoByOid(String oid) throws MsgException;
	/**
	 * 根据订单id查询订单的信息
	 * @param oid
	 * @return
	 */
	public Order findOrderByOid(String oid);
	/**
	 * 修改订单支付状态
	 * @param r6_Order
	 * @param paystate 
	 */
	@Tran
	public void updatePaystate(String oid, int paystate);

}
