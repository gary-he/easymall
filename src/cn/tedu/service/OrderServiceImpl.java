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
					Product prod=prodDao.findProdById(orderItem.getProduct_id());
					//将订单项信息添加到map
					map.put(prod, orderItem.getBuyNum());
				}
			}
			//将订单项信息存进orderInfo
			orderInfo.setMap(map);
			//将orderInfo存进oinList
			oinList.add(orderInfo);
		}
		return oinList;
	}

}
