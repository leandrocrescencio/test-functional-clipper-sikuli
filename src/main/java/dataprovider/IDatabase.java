package dataprovider;

import java.sql.ResultSet;
import java.util.List;

public interface IDatabase {

	public int insert(String sql);
	public int delete(String sql);
	public int update(String sql);
	public int[] insert(List<String> sql);
	public int[] delete(List<String> sql);
	public int[] update(List<String> sql);
	public ResultSet select(String sql);
	public IConnection getConnection();
}

