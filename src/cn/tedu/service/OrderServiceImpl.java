package cn.tedu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.tedu.dao.OrderDao;
import cn.tedu.dao.ProdDao;
import cn.tedu.domain.Order;
import cn.tedu.domain.OrderInfo;
import cn.tedu.domain.OrderItem;
import cn.tedu.domain.Product;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;

public class OrderServiceImpl implements OrderService {
	private ProdDao prodDao = BasicFactory.getFactory().getInstance(
			ProdDao.class);
	private OrderDao orderDao = BasicFactory.getFactory().getInstance(
			OrderDao.class);

	public void addOrder(Order order, List<OrderItem> oiList)
			throws MsgException {

		// 向order表添加一条记录
		orderDao.addOrder(order);
		// 遍历订单项，满足条件则将每项都添加到order_item表
		for (OrderItem oi : oiList) {
			// 判断库存是否足够
			// 获取对应商品信息
			Product prod = prodDao.findProdById(oi.getProduct_id());
			if (prod.getPnum() < oi.getBuyNum()) {
				// 不足则抛出MsgException异常提示对应商品库存不足
				throw new MsgException(prod.getId() + "," + prod.getName()
						+ ",pnum:" + prod.getPnum() + ":库存不足");
			}
			// 库存足够则更新库存，并将该项添加到orderitem表
			prodDao.updateProdNum(prod.getId(), prod.getPnum() - oi.getBuyNum());
			orderDao.addOrderItem(oi);
		}

	}

	public List<OrderInfo> getOrderInfoByUserId(int uid) {
		//定义订单详细信息的集合
		List<OrderInfo> oinList=new ArrayList<OrderInfo>();
		//获取用户的所有订单集合
		List<Order> oList=orderDao.findOrdersByUserId(uid);
		//获取用户的所有订单项集合
		List<OrderItem> oiList=orderDao.findOrderItemsByUserId(uid);
		//获取用户的所有订单项对应的商品
		List<Product> prods=orderDao.findProdsByUserId(uid);
		//将订单信息分类封装，一个order对应一个orderinfo
		//遍历订单集合
		for(Order order:oList){
			//创建OrderInfo对象
			OrderInfo orderInfo=new OrderInfo();
			//将order存进orderInfo
			orderInfo.setOrder(order);
			//创建map<商品信息，购买数量>存放当前订单对应的所有订单项
			Map<Product, Integer> map=new HashMap<Product, Integer>();
			//遍历oiList
			for(OrderItem orderItem:oiList){
				//判断orderItem是否属于当前订单
				if(orderItem.getOrder_id().equals(order.getId())){
					//根据订单项的商品id查询对应商品信息
					//Product prod=prodDao.findProdById(orderItem.getProduct_id());
					//在prods里找出当前订单项对应的商品
					for(Product prod:prods){
						if(prod.getId().equals(orderItem.getProduct_id())){
							//将订单项信息添加到map
							map.put(prod, orderItem.getBuyNum());
						}
					}
				}
			}
			//将订单项信息存进orderInfo
			orderInfo.setMap(map);
			//将orderInfo存进oinList
			oinList.add(orderInfo);
		}
		return oinList;
	}

	public void deleteOrderInfoByOid(String oid) throws MsgException {
		//根据订单id查询订单信息
		Order order=orderDao.findOrderByOid(oid);
		//判断订单的支付状态，只有未支付的订单才可以删除
		if(order.getPayState()!=0){
			throw new MsgException("只有未支付的订单可以删除");
		}
		//未支付：根据订单id查询该订单所有的订单项
		List<OrderItem> oiList=orderDao.findOrderItemsByOid(oid);
		//遍历集合
		for(OrderItem oi:oiList){
			//还原库存
			prodDao.changePnum(oi.getProduct_id(),oi.getBuyNum());
		}
		//根据订单id删除所有订单项
		orderDao.deleteOrderItemByOid(oid);
		//根据订单id删除对应的订单信息
		orderDao.deleteOrderByOid(oid);
	}

	public Order findOrderByOid(String oid) {
		return orderDao.findOrderByOid(oid);
	}

	public void updatePaystate(String oid,int paystate) {
		Order order=orderDao.findOrderByOidForUpdate(oid);
		if(order!=null&&order.getPayState()==0){
			orderDao.updatePaystate(oid,paystate);
		}
	}

}
