package cn.tedu.dao;

import java.util.ArrayList;
import java.util.List;

import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;
import cn.tedu.utils.BeanListHandler;
import cn.tedu.utils.DaoUtils;


public class OrderDaoImpl implements OrderDao {

	public void addOrder(Order order) {
		// 编写sql语句
		String sql = "insert into orders(id,money,receiverinfo,paystate,ordertime,user_id) values(?,?,?,?,?,?)";
		// 执行
		try {
			DaoUtils.update(sql, order.getId(), order.getMoney(),
					order.getReceiverInfo(), order.getPayState(),
					order.getOrderTime(), order.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addOrderItem(OrderItem oi) {
		// 编写sql语句
		String sql = "insert into orderitem(order_id,product_id,buynum) values(?,?,?)";
		// 执行
		try {
			DaoUtils.update(sql, oi.getOrder_id(), oi.getProduct_id(),
					oi.getBuyNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Order> findOrdersByUserId(int uid) {
		// 编写sql语句
		String sql = "select * from orders where user_id=?";
		// 执行
		try {
			return DaoUtils.query(sql, new BeanListHandler<Order>(Order.class),
					uid);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Order>();
		}
	}

	public List<OrderItem> findOrderItemsByUserId(int uid) {
		String sql = "SELECT oi.* FROM orders od,orderitem oi WHERE od.id=oi.order_id AND od.user_id=?";
		try {
			return DaoUtils.query(sql, new BeanListHandler<OrderItem>(
					OrderItem.class), uid);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<OrderItem>();
		}
	}

}
