package cn.tedu.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DaoUtils {
private static ComboPooledDataSource pool = new ComboPooledDataSource();
	
	private DaoUtils(){}
	
	/**
	 * 获取连接池实例
	 * @return pool
	 */
	public static DataSource getPool(){
		return pool;
	}
	
	/**
	 * 从连接池中获取一个连接
	 * @return 
	 * @throws SQLException
	 */
	public static Connection getConn() throws SQLException{
		return pool.getConnection();
	}
	/**
	 * 查询记录
	 * @param sql
	 * @param rsh
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static <T> T query(String sql,ResultSetHandler<T> rsh,Object...params) throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=getConn();
			ps=conn.prepareStatement(sql);
			if(params!=null&&params.length>0){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}  
			}
			rs=ps.executeQuery();
			
			//用结果集处理器处理结果集并将返回值返回
			return rsh.handler(rs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			close(conn, ps, rs);
		}
	}
	/**
	 * 增加，删除，修改记录
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static int update(String sql,Object...params) throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=getConn();
			ps=conn.prepareStatement(sql);
			if(params!=null&&params.length>0){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			//返回影响的行数
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			close(conn, ps, rs);
		}
	}
	/**
	 * 关闭资源
	 * @param conn 数据库连接
	 * @param stat 传输器
	 * @param rs 结果集
	 */
	public static void close(Connection conn, Statement stat, ResultSet rs){
		if(rs != null ){
			try {
				rs.close();//关闭rs
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs = null;
			}
		}
		if(stat != null ){
			try {
				stat.close();//关闭stat
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				stat = null;
			}
		}
		if(conn != null ){
			try {
				conn.close();//关闭conn
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
	}
}
