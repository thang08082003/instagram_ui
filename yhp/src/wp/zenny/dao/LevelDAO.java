package wp.zenny.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import wp.zenny.model.ConnectDatabase;

import wp.zenny.model.Level;

public class LevelDAO {

	public Map<String,Level> mapLevel = getMapLevel();
	
	public LevelDAO() {}
	
	private Map<String,Level> getMapLevel(){
		Map<String,Level> map = new HashMap<String,Level>();
		Connection conn = ConnectDatabase.getConnection();
		String levelId;
		String levelName;
		try {
			CallableStatement stmt = conn.prepareCall("call spGetLevel",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				levelId = rs.getString(1);
				levelName = rs.getString(2);
				Level Level = new Level(levelId,levelName);
				map.put(levelId, Level);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public String getLevelID(String Levelname) {
		for(String Levelid: mapLevel.keySet()) {
			Level Level = mapLevel.get(Levelid);
			if(Level.getLevelName().equals(Levelname)) {
				return Level.getLevelId();
			}
		}
		return null;
	}
	

}
