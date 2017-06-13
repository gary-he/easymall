package cn.tedu.service;

import java.util.List;

import cn.tedu.dao.OrderDao;
import cn.tedu.dao.ProdDao;
import cn.tedu.domain.Order;
import cn.tedu.domain.Product;
import cn.tedu.domain.orderItem;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;

public class OrderServiceImpl implements OrderService {
	private ProdDao prodDao=BasicFactory.getFactory().getInstance(ProdDao.class);
	private OrderDao orderDao=BasicFactory.getFactory().getInstance(OrderDao.class);
	public void addOrder(Order order, List<orderItem> oiList)
			throws MsgException {
		//向order表添加一条记录
		orderDao.addOrder(order);
		//遍历订单项，满足条件则将每项都添加到order_item表
		for(orderItem oi:oiList){
			//判断库存是否足够
			//获取对应商品信息
			Product prod=prodDao.findProdById(oi.getProdId());
			if(prod.getPnum()<oi.getBuyNum()){
				//不足则抛出MsgException异常提示对应商品库存不足
				throw new MsgException(prod.getId()+","+prod.getName()+",pnum:"+prod.getPnum()+":库存不足");
			}
			//库存足够则更新库存，并将该项添加到orderitem表
			prodDao.updateProdNum(prod.getId(), prod.getPnum()-oi.getBuyNum());
			orderDao.addOrderItem(oi);
		}
	}

}
