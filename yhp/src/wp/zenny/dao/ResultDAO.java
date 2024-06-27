	package wp.zenny.dao;

	import java.sql.CallableStatement;
	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
	import java.util.Map;
	import wp.zenny.model.ConnectDatabase;
import wp.zenny.model.Result;

	public class ResultDAO {
		
		public ResultDAO() {}
		
		public Map<String,Result> getMapResult(String username){
			Map<String,Result> map = new HashMap<String,Result>();
			Connection conn = ConnectDatabase.getConnection();
			String examname;
			float mark;
			Date dateTest;
			Timestamp ts;
			int timeFinish;
			String examid;
			try {
				CallableStatement stmt = conn.prepareCall("call spGetResult(?)",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					examid = rs.getString(1);
					examname = rs.getString(2);
					mark = rs.getFloat(3);
					timeFinish = rs.getInt(4);
					 ts = (Timestamp)rs.getTimestamp(5);
					 dateTest = ts==null?ts: new Date(ts.getTime());
					Result result = new Result(examname, mark, timeFinish,dateTest);
					map.put(examid, result);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
			return map;
		}
		public boolean InsertResult(String exId,String username,float mark, int timeFinish) {
			boolean result = false;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Connection conn = ConnectDatabase.getConnection();
				CallableStatement stmt = conn.prepareCall("call spInsertResult(?,?,?,?,?)");
				stmt.setString(1, exId);
				stmt.setString(2, username);
				stmt.setFloat(3, mark);
				stmt.setString(4, sdf.format(new java.sql.Date(new Date().getTime())));
				stmt.setInt(5, timeFinish);
				stmt.executeUpdate();
				return true;
				
			} catch(SQLException e) {
				e.printStackTrace();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			return result;

		}
		public static void main(String[] args) {
			SimpleDateFormat a =new SimpleDateFormat("hh:mm");
			for(Result s :new ResultDAO().getMapResult("15110378").values()) {
				System.out.println(a.format(s.getDateTest()));
			}
		}
	}
