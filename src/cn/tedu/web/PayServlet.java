package cn.tedu.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Order;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;
import cn.tedu.utils.PaymentUtil;


public class PayServlet extends HttpServlet {
	//获取配置信息
	private static Properties prop=new Properties();
	static{
		String path=PayServlet.class.getClassLoader().getResource("merchantInfo.properties").getPath();
		try {
			prop.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//private OrderService service=BasicFactory.getFactory().getInstance(OrderService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收订单id
		String oid=request.getParameter("oid");
		//准备第三方支付平台需要的参数
		String p0_Cmd="Buy";//业务类型
		String p1_MerId=prop.getProperty("p1_MerId");//商户编号
		String p2_Order=oid;//订单id
		//订单金额，测试时使用0.11
		String p3_Amt = "0.01";
			//部署到正式环境前，将该值改为调用业务层查询出的金额
			//Order order = service.findOrderByOid(oid);
			//String p3_Amt=order.getMoney()+"";
		String p4_Cur ="CNY";//币种
		String p5_Pid = "";//商品名称
		String p6_Pcat ="";//商品种类
		String p7_Pdesc="";//商品描述
		//商户接收支付成功数据的地址
		String p8_Url=prop.getProperty("responseURL");
		String p9_SAF="";//送货地址
		String pa_MP="";//商户扩展信息
		//支付通道编码
		String pd_FrpId=request.getParameter("pd_FrpId");
		//应答机制
		String pr_NeedResponse = "1";
		//加密(将一系列参数按照一定的加密算法生成一个新的字符串)
		String keyValue=prop.getProperty("keyValue");
		//签名数据（由以上参数通过密钥加密后得到）
		String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId,
				p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, 
				p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId,
				pr_NeedResponse, keyValue);
		//将这些参数保存到request作用域
		request.setAttribute("pd_FrpId", pd_FrpId);
		request.setAttribute("p0_Cmd", p0_Cmd);
		request.setAttribute("p1_MerId", p1_MerId);
		request.setAttribute("p2_Order", p2_Order);
		request.setAttribute("p3_Amt", p3_Amt);
		request.setAttribute("p4_Cur", p4_Cur);
		request.setAttribute("p5_Pid", p5_Pid);
		request.setAttribute("p6_Pcat", p6_Pcat);
		request.setAttribute("p7_Pdesc", p7_Pdesc);
		request.setAttribute("p8_Url", p8_Url);
		request.setAttribute("p9_SAF", p9_SAF);
		request.setAttribute("pa_MP", pa_MP);
		request.setAttribute("pr_NeedResponse", pr_NeedResponse);
		request.setAttribute("hmac", hmac);
		//转发到confim.jsp
		request.getRequestDispatcher("/confirm.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
