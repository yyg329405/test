package util.dbutil;

/**
*DBUtil.java
*连接数据库
*/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;

import com.mysql.jdbc.PreparedStatement;
public class DBUtil {
    private String driver;//定义驱动
    private String url;//定义URL
    private String user;//定义用户名
    private String password;//定义密码
    private Connection conn;//定义连接
    private Statement stmt;//定义STMT
    private PreparedStatement ps;
    private ResultSet rs;//定义结果集
    //构造函数，默认加裁配置文件为jdbc.driver
    public DBUtil(){
        this("jdbc.properties");
    }
    //有参构造函数，传入路径Conf并用方法loadProperties进行加载，用setConn进行设置
    public DBUtil(String conf) {
        loadProperties(conf);
        setConn();
    }
    //返回Conn
    public Connection getConn(){
        return this.conn;
    }
  //传入参数路径Conf加载配置文件取得配置文件中的参数并设置为类变量的参数
    private void loadProperties(String conf){
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(conf));//根据配置文件路径Conf加载配置文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.driver = props.getProperty("driverClassName");//从配置文件中取得相应的参数并设置类变量
        this.url = props.getProperty("url");
        this.user = props.getProperty("username");
        this.password = props.getProperty("password");
    }
    //implement the Connection
    //设置CONN
    private void setConn(){
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(url,user,password);
        } catch(ClassNotFoundException classnotfoundexception) {
              classnotfoundexception.printStackTrace();
            System.err.println("db: " + classnotfoundexception.getMessage());
        } catch(SQLException sqlexception) {
            System.err.println("db.getconn(): " + sqlexception.getMessage());
        }
    }
    //执行插入
       public void doInsert(String sql) {
        try {
            Statement statement = conn.createStatement();
            int i = stmt.executeUpdate(sql);
        } catch(SQLException sqlexception) {
            System.err.println("db.executeInset:" + sqlexception.getMessage());
        }finally{
        	
        }
    }
    //执行删除
    public void doDelete(String sql) {
        try {
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(sql);
        } catch(SQLException sqlexception) {
            System.err.println("db.executeDelete:" + sqlexception.getMessage());
        }
    }
    //执行更新
    public void doUpdate(String sql) {
    	try {
//            stmt = conn.createStatement();
    		stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
    		int i = stmt.executeUpdate(sql);
    	} catch(SQLException sqlexception) {
    		System.err.println("db.executeUpdate:" + sqlexception.getMessage());
    	}
    }
    //执行更新
    public void doUpdateResult(String sql, Map<String,String> map) {
        try {
			conn.setAutoCommit(false);
			ps = (PreparedStatement) conn.prepareStatement(sql,  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();

			Set set=map.keySet();//用接口实例接口
			Iterator iter = set.iterator();
			String key = null;
			String value = null;
			while (iter.hasNext()) {// 遍历二次,速度慢
				key = (String) iter.next();
				value = map.get(key);
			}

			while (rs.next()) {
				System.out.println(rs.getString(key)+">>>"+value);
				rs.updateString(key, value);
				rs.updateRow();
			}
			ps.close();
			rs.close();
			conn.commit();
        } catch(SQLException sqlexception) {
            System.err.println("db.executeUpdate:" + sqlexception.getMessage());
        }
    }
    //查询结果集
    public ResultSet doSelect(String sql) {
    	try {
    		stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
    		rs = stmt.executeQuery(sql);
    	} catch(SQLException sqlexception) {
    		System.err.println("db.executeQuery: " + sqlexception.getMessage());
    	}
    	return rs;
    }
    
    //查询结果集
    public ResultSet doSelect(String sql, Object[] objs) {
    	try {
//        	ps = (PreparedStatement) conn.prepareStatement(sql, java.sql.ResultSet.TYPE_SCROLL_SENSITIVE, java.sql.ResultSet.CONCUR_UPDATABLE );
    		ps = (PreparedStatement) conn.prepareStatement(sql, java.sql.ResultSet.TYPE_SCROLL_SENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY );
    		for(int i=1; i<=objs.length; i++){
        		ps.setObject(1, objs[i-1]);
    		}
    		rs = ps.executeQuery();
    	} catch(SQLException sqlexception) {
    		System.err.println("db.executeQuery: " + sqlexception.getMessage());
    	}
    	return rs;
    }
    
    /**
     *关闭数据库结果集，数据库操作对象，数据库链接
       @Function: Close all the statement and conn int this instance and close the parameter ResultSet
       @Param: ResultSet
       @Exception: SQLException,Exception
      **/
     public void close(ResultSet rs) throws SQLException, Exception {

       if (rs != null) {
         rs.close();
         rs = null;
       }

       if (stmt != null) {
         stmt.close();
         stmt = null;
       }

       if (conn != null) {
         conn.close();
         conn = null;
       }
     }

     /**
      *关闭数据库操作对象，数据库连接对象
      * Close all the statement and conn int this instance
      * @throws SQLException
      * @throws Exception
      */
     public void close() throws SQLException, Exception {
       if (stmt != null) {
         stmt.close();
         stmt = null;
       }

       if (conn != null) {
         conn.close();
         conn = null;
       }
     }
    //测试类
    public static void main(String[] args){
        try{
        	//根据传入配置路径构造类
            DBUtil db = new DBUtil("src\\config\\dbconfig.properties");
//            //取得连接
            Connection conn = db.getConn();
            if(conn != null && !conn.isClosed()) {
                System.out.println("連結成功");
                
                ResultSet rs = db.doSelect("select * from stu where note like ?", new Object[]{"a%b"});
                while(rs.next()){
                    System.out.println(rs.getString("NOTE"));
                  }
                rs.close();
                conn.close();
            
//            String sql = "update stu set note='siski' where sid=1";
//            db.doUpdate(sql);
            
//                String sql = "select * from stu where sid=1";
//                Map map = new HashedMap();
//                map.put("NAME", "KIT2");
//                db.doUpdateResult(sql, map);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }  
}




