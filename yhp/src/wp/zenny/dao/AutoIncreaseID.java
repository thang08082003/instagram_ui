package wp.zenny.dao;

import java.util.Map;

public class AutoIncreaseID {
	Map<String,Object> map;
	String label;
	public AutoIncreaseID(Map map, String label) {
		this.map = map;
		this.label = label;
	}
	public String getId() {
		int value = map.size() + 1;
		String id = setIdFromValue(value);
		while(map.containsKey(id)) {
			id = setIdFromValue(value++);
		}
		return id;
	}
	private String setIdFromValue(int value) {
		String id;
		if(value < 10)
			id = label +"00"+ value;
		else if(value < 100)
			id = label + "0" + value;
		else id = label + value;
		
		return id;
	}
}
