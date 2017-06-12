package cn.tedu.service;

import java.util.List;

import cn.tedu.domain.Product;
/**
 * 操作商品信息的service
 * @author Administrator
 *
 */
public interface ProdService {
	/**
	 * 查询所有商品信息，返回所有商品组成的list集合
	 * @return
	 */
	public List<Product> findAll();
	/**
	 * 根据指定的参数查询所有满足条件的商品信息，返回所有商品组成的list集合
	 * @param _name 商品名称摘要
	 * @param _category 商品类别摘要
	 * @param _minPrice 最低价格
	 * @param _maxPrice 最高价格
	 * @return
	 */
	public List<Product> findAll(String _name, String _category,
			double _minPrice, double _maxPrice);
	/**
	 * 修改指定id的商品的库存数量
	 * @param pid
	 * @param pnum
	 * @return
	 */
	public boolean updatePnum(String pid,int pnum);
	
	/**
	 * 删除指定id的商品
	 * @param pid
	 * @return
	 */
	public boolean delProdById(String pid);
	
	/**
	 * 根据id查找对应商品信息
	 * @param pid
	 * @return
	 */
	public Product findProdById(String pid);
	
}
