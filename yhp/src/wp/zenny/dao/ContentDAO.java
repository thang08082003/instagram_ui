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
import wp.zenny.model.Content;
import wp.zenny.model.Question;

public class ContentDAO {

	public Map<String,Content> mapContent = getMapContent();
	
	public ContentDAO() {}
	
	private Map<String,Content> getMapContent(){
		Map<String,Content> map = new HashMap<String,Content>();
		Connection conn = ConnectDatabase.getConnection();
		try {
			CallableStatement stmt = conn.prepareCall("call spGetContent",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			String contentId;
			String contentName;
			String subId;
			Date dateCreate;
			String creator;
			Timestamp ts;
			while(rs.next()) {
				contentId = rs.getString(1);
				contentName = rs.getString(2);
				subId = rs.getString(3);
				 ts = rs.getTimestamp(4);
				dateCreate = ts==null? ts: new Date(ts.getTime());
				creator = rs.getString(5);
				Content Content = new Content(contentId,contentName,subId,dateCreate,creator);
				map.put(rs.getString(1), Content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	public boolean InsertContent(Content ct) {
		boolean result = false;
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Connection conn = ConnectDatabase.getConnection();
			CallableStatement stmt = conn.prepareCall("call spInsertContent(?,?,?,?,?)");
			stmt.setString(1, ct.getContentId());
			stmt.setString(2, ct.getContentName());
			stmt.setString(3, ct.getSubId());
			stmt.setString(4, s.format(new java.sql.Date(ct.getDateCreate().getTime())));
			stmt.setString(5, ct.getCreator());
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return result;
	}
	
	public boolean DeleteContent(String contentId) {
		boolean result = false;
		try {
			Connection conn = ConnectDatabase.getConnection();
			CallableStatement stmt = conn.prepareCall("call spDeleteContent(?)");
			stmt.setString(1, contentId);
			
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return result;
	}
	public boolean UpdateContent(String contentId,String contentName,String subId) {
		boolean result = false;
		try {
			Connection conn = ConnectDatabase.getConnection();
			CallableStatement stmt = conn.prepareCall("call spUpdateContent(?,?,?)");
			stmt.setString(1, contentId);
			stmt.setString(2, contentName);
			stmt.setString(3, subId);
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return result;
	}
	public String getContentID(String Contentname) {
		for(String Contentid: mapContent.keySet()) {
			Content Content = mapContent.get(Contentid);
			if(Content.getContentName().equals(Contentname)) {
				return Content.getContentId();
			}
		}
		return null;
	}
	
	public Map<String,Content> getListContentForEachSub(String subId){
		Map<String,Content> mapContentForEachSub = new HashMap<String, Content>();
		for(Content ct : mapContent.values()) {
			if(ct.getSubId()!=null && ct.getSubId().equals(subId)) {
				mapContentForEachSub.put(ct.getContentId(), ct);
			}
		}
		return mapContentForEachSub;
	}
	
	public String getNumOfQuestion(String ctId) {
		int i = 0;
		for(Question q : new QuestionDAO().mapQuestion.values()) {
			if(q.getContentId() != null &&q.getContentId().equals(ctId))
				i++;
		}
		return String.valueOf(i);
	}
	public static void main(String[] args) {
		ContentDAO c =new ContentDAO();
		Object[] obj = c.getListContentForEachSub("SJ002").values().toArray();
		for(int i=0;i<obj.length;i++) {
			Content ct = (Content)obj[i];
			System.out.println(ct.getContentId());
		}
	}
	
}
