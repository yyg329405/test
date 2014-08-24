package util.dbutil;

/**
*DBUtil.java
*�������ݿ�
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
    private String driver;//��������
    private String url;//����URL
    private String user;//�����û���
    private String password;//��������
    private Connection conn;//��������
    private Statement stmt;//����STMT
    private PreparedStatement ps;
    private ResultSet rs;//��������
    //���캯����Ĭ�ϼӲ������ļ�Ϊjdbc.driver
    public DBUtil(){
        this("jdbc.properties");
    }
    //�вι��캯��������·��Conf���÷���loadProperties���м��أ���setConn��������
    public DBUtil(String conf) {
        loadProperties(conf);
        setConn();
    }
    //����Conn
    public Connection getConn(){
        return this.conn;
    }
  //�������·��Conf���������ļ�ȡ�������ļ��еĲ���������Ϊ������Ĳ���
    private void loadProperties(String conf){
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(conf));//���������ļ�·��Conf���������ļ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.driver = props.getProperty("driverClassName");//�������ļ���ȡ����Ӧ�Ĳ��������������
        this.url = props.getProperty("url");
        this.user = props.getProperty("username");
        this.password = props.getProperty("password");
    }
    //implement the Connection
    //����CONN
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
    //ִ�в���
       public void doInsert(String sql) {
        try {
            Statement statement = conn.createStatement();
            int i = stmt.executeUpdate(sql);
        } catch(SQLException sqlexception) {
            System.err.println("db.executeInset:" + sqlexception.getMessage());
        }finally{
        	
        }
    }
    //ִ��ɾ��
    public void doDelete(String sql) {
        try {
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(sql);
        } catch(SQLException sqlexception) {
            System.err.println("db.executeDelete:" + sqlexception.getMessage());
        }
    }
    //ִ�и���
    public void doUpdate(String sql) {
    	try {
//            stmt = conn.createStatement();
    		stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
    		int i = stmt.executeUpdate(sql);
    	} catch(SQLException sqlexception) {
    		System.err.println("db.executeUpdate:" + sqlexception.getMessage());
    	}
    }
    //ִ�и���
    public void doUpdateResult(String sql, Map<String,String> map) {
        try {
			conn.setAutoCommit(false);
			ps = (PreparedStatement) conn.prepareStatement(sql,  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();

			Set set=map.keySet();//�ýӿ�ʵ���ӿ�
			Iterator iter = set.iterator();
			String key = null;
			String value = null;
			while (iter.hasNext()) {// ��������,�ٶ���
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
    //��ѯ�����
    public ResultSet doSelect(String sql) {
    	try {
    		stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
    		rs = stmt.executeQuery(sql);
    	} catch(SQLException sqlexception) {
    		System.err.println("db.executeQuery: " + sqlexception.getMessage());
    	}
    	return rs;
    }
    
    //��ѯ�����
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
     *�ر����ݿ����������ݿ�����������ݿ�����
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
      *�ر����ݿ�����������ݿ����Ӷ���
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
    //������
    public static void main(String[] args){
        try{
        	//���ݴ�������·��������
            DBUtil db = new DBUtil("src\\config\\dbconfig.properties");
//            //ȡ������
            Connection conn = db.getConn();
            if(conn != null && !conn.isClosed()) {
                System.out.println("�B�Y�ɹ�");
                
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




