package cn.tedu.domain;
/**
 * 存放商品信息的javabean
 * @author Administrator
 *
 */
public class Product {
	private String id;
	private String name;
	private double price;
	private String category;
	private String imgurl;
	private int pnum;
	private String description;
	
	public Product() {
		super();
	}
	
	/**
	 * 重写hashcode方法，让product实例对象的hashcode值为其id对应的hashcode值
	 */
	@Override
	public int hashCode() {
		return id==null ? 0 : id.hashCode();
	}

	/**
	 * 重写equals方法，判定id相等则为同一种商品
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(this==obj){
			return true;
		}
		if(obj instanceof Product){
			return id!=null&&id.equals(((Product)obj).getId());
		}
		return false;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
