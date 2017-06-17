package cn.tedu.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import cn.tedu.domain.Product;
import cn.tedu.utils.BeanHandler;
import cn.tedu.utils.BeanListHandler;
import cn.tedu.utils.DaoUtils;

public class ProdDaoImpl implements ProdDao {

	public List<Product> findAll() {
		try {
			//调用daoutils工具类的query方法，传入beanlidthandler结果集处理器将结果封装成list
			return DaoUtils.query("select * from products", new BeanListHandler<Product>(Product.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public boolean updateProdNum(String pid, int pnum) {
		try {
			//调用daoutils工具类的update方法，更新对应商品的库存数量
			return DaoUtils.update("update products set pnum=? where id=?", pnum,pid)>0;
		} catch (Exception e) { 
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public boolean delProdById(String pid) {
		try {
			return DaoUtils.update("delete from products where id=?", pid)>0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Product> findAll(String _name, String _category,
			double _minPrice, double _maxPrice) {
		try {
			return DaoUtils.query("select * from products where name like ? and category like ? and price>? and price<?", 
					new BeanListHandler<Product>(Product.class),
					"%"+_name+"%","%"+_category+"%",_minPrice,_maxPrice);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Product findProdById(String pid) {
		try {
			return DaoUtils.query("select * from products where id=?", new BeanHandler<Product>(Product.class), pid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void changePnum(String pid, int num) {
		String sql="update products set pnum=pnum+? where id=?";
		try {
			DaoUtils.update(sql, num,pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
