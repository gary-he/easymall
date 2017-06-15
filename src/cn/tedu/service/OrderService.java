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

}
