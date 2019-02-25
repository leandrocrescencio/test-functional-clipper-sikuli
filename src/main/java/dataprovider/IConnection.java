package dataprovider;

import java.sql.ResultSet;
import java.util.List;

public interface IConnection {
	public boolean isConnected();
	public void open();
	public void close();
	public int[] executeMultipleUpdate(List<String> sql);
	public int executeUpdate(String sql);
	public ResultSet executeQuery(String sql);
}
