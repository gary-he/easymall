package cn.tedu.service;

import java.util.List;

import cn.tedu.domain.Order;
import cn.tedu.domain.orderItem;
import cn.tedu.exception.MsgException;
/**
 * 操作订单的service
 * @author Administrator
 *
 */
public interface OrderService {
	/**
	 * 添加订单
	 * @param order 订单信息javabean
	 * @param oiList 订单项javabean
	 */
	public void addOrder(Order order, List<orderItem> oiList) throws MsgException;

}
