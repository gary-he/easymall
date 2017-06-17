package cn.tedu.dao;

import java.util.ArrayList;
import java.util.List;

import cn.tedu.domain.Order;
import cn.tedu.domain.OrderItem;
import cn.tedu.domain.Product;
import cn.tedu.utils.BeanHandler;
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

	public List<Product> findProdsByUserId(int uid) {
		String sql = "SELECT prod.* "
				+ "FROM orders od,orderitem oi,products prod "
				+ "WHERE od.id=oi.order_id " 
				+ "AND oi.product_id=prod.id "
				+ "AND od.user_id=?";

		try {
			return DaoUtils.query(sql, new BeanListHandler<Product>(
					Product.class), uid);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Product>();
		}
	}

	public Order findOrderByOid(String oid) {
		String sql="select * from orders where id=?";
		try {
			return DaoUtils.query(sql, new BeanHandler<Order>(Order.class), oid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<OrderItem> findOrderItemsByOid(String oid) {
		String sql="select * from orderitem where order_id=?";
		try {
			return DaoUtils.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), oid);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<OrderItem>();
		}
	}

	public boolean deleteOrderItemByOid(String oid) {
		String sql="delete from orderitem where order_id=?";
		try {
			return DaoUtils.update(sql, oid)>0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteOrderByOid(String oid) {
		String sql="delete from orders where id=?";
		try {
			return DaoUtils.update(sql, oid)>0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Order findOrderByOidForUpdate(String oid) {
		String sql="select * from orders where id=? for update";
		try {
			return DaoUtils.query(sql, new BeanHandler<Order>(Order.class), oid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updatePaystate(String oid, int paystate) {
		String sql="update orders set paystate=? where id=?";
		try {
			DaoUtils.update(sql, paystate,oid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
