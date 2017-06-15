package cn.tedu.dao;

import java.util.List;

import cn.tedu.domain.Product;

/**
 * 操作商品信息的dao
 * @author Administrator
 *
 */
public interface ProdDao extends Dao {
	/**
	 * 查找所有所有商品，将商品信息封装进list集合并返回
	 * @return
	 */
	public List<Product> findAll();
	/**
	 * 根据参数查询所有满足条件的商品信息，将商品信息封装进list集合并返回
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
	 * @param pid 商品id
	 * @param pnum 商品新的库存数量
	 * @return
	 */
	public boolean updateProdNum(String pid,int pnum);
	
	/**
	 * 删除指定id的商品记录
	 * @param pid
	 * @return
	 */
	public boolean delProdById(String pid);
	
	/**
	 * 根据id查找相应商品信息
	 * @param pid
	 * @return
	 */
	public Product findProdById(String pid);
}
