package cn.tedu.dao;

import cn.tedu.domain.Order;
import cn.tedu.domain.orderItem;
import cn.tedu.utils.DaoUtils;

public class OrderDaoImpl implements OrderDao {

	public void addOrder(Order order) {
		//编写sql语句
		String sql="insert into order(id,money,receiverinfo,paystate,ordertime,user_id) values(?,?,?,?,?,?)";
		//执行
		try {
			DaoUtils.update(sql, order.getId(),order.getMoney(),order.getReceiverInfo(),order.getPayState(),order.getOrderTime(),order.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addOrderItem(orderItem oi) {
		//编写sql语句
		String sql="insert into orderitem(order_id,product_id,buynum) values(?,?,?)";
		//执行
		try {
			DaoUtils.update(sql, oi.getOrderId(),oi.getProdId(),oi.getBuyNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
