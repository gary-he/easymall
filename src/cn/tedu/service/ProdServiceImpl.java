package cn.tedu.service;

import java.util.List;

import cn.tedu.dao.ProdDao;
import cn.tedu.domain.Product;
import cn.tedu.factory.BasicFactory;

public class ProdServiceImpl implements ProdService {
	private ProdDao dao=BasicFactory.getFactory().getInstance(ProdDao.class);
	public List<Product> findAll() {
		//调用dao层的方法，并将结果返回
		return dao.findAll();
	
	}
	public boolean updatePnum(String pid, int pnum) {
		return dao.updateProdNum(pid, pnum);
	}
	public boolean delProdById(String pid) {
		return dao.delProdById(pid);
	}
	public List<Product> findAll(String _name, String _category,
			double _minPrice, double _maxPrice) {
		return dao.findAll(_name, _category,  _minPrice,  _maxPrice);
	}
	public Product findProdById(String pid) {
		return dao.findProdById(pid);
	}

}
