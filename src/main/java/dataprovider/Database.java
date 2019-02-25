package dataprovider;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import exceptions.TimeoutException;

public class Database implements IDatabase {
	
	private static final Logger LOGGER = Logger.getLogger(Database.class);
	
	private Connection conn;
	private String url;
	private String name;
	private String password;
	
	public Database(String url, String name, String password){
		this.conn = new Connection();
		this.url = url;
		this.name = name;
		this.password = password;
	}
	
	@Override
	public int insert(String sql) {
		return this.conn.executeUpdate(sql);
	}

	@Override
	public int[] insert(List<String> sql) {
		return this.conn.executeMultipleUpdate(sql);
	}
	
	@Override
	public int delete(String sql) {
		return this.conn.executeUpdate(sql);
	}
	
	@Override
	public int[] delete(List<String> sql) {
		return this.conn.executeMultipleUpdate(sql);
	}

	@Override
	public int update(String sql) {
		return this.conn.executeUpdate(sql);
	}

	@Override
	public int[] update(List<String> sql) {
		return this.conn.executeMultipleUpdate(sql);
	}
	
	@Override
	public ResultSet select(String sql) {
		return this.conn.executeQuery(sql);
	}
	
	public IConnection getConnection(){
		return this.conn;
	}
	
	public class Connection implements IConnection {
		private java.sql.Connection connect;
		private java.sql.Statement statement;
		
		public Connection (){
			this.statement = null;
			this.connect = null;
		}
		
		@Override
		public boolean isConnected() {
			try {
				return !statement.isClosed();
			} catch (SQLException e) {
				LOGGER.warn(e);
				return false;
			}
		}
		
		public void checkDriverConnection(){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				LOGGER.error(e);
			}
		}
		
		public void open(){
			checkDriverConnection();
			
			try {
				connect =  DriverManager.getConnection(url, name, password);
				connect.setAutoCommit(false);
				DatabaseMetaData mtdt = connect.getMetaData();
				
				this.statement = connect.createStatement();
				
				LOGGER.info("Conexão realizada com sucesso!\n");
				LOGGER.info("URL em uso: " + mtdt.getURL());
				LOGGER.info("Usuario: " + mtdt.getUserName());
				LOGGER.info("DBMS: " + mtdt.getDatabaseProductName());
				LOGGER.info("DBMS version: " + mtdt.getDatabaseProductVersion());
				LOGGER.info("Driver: " + mtdt.getDriverName());
				LOGGER.info("Driver version: " + mtdt.getDriverVersion()+"\n");
			}
			 catch(SQLException e){
				 LOGGER.error(e);
			 }
		}
		
		public int[] executeMultipleUpdate(List<String> sql) {
			int[] result = new int[sql.size()];
			try {	
				for(int i = 0; i < sql.size(); i++){
					statement.addBatch(sql.get(i));
				}
				result = statement.executeBatch();
			} catch (SQLException e) {
				LOGGER.warn(e);
			}
			
			return result;
		}

		@Override
		public int executeUpdate(String sql) {
			try {
				LOGGER.info("SCRIPT executado: \n" + sql + "\n");
				statement.addBatch(sql);
				return statement.executeBatch()[0];
			} catch (SQLException e) {
				LOGGER.warn(e);
				throw new TimeoutException(e.getMessage());
			}
		}

		@Override
		public ResultSet executeQuery(String sql) {
			try {
				LOGGER.info("QUERY executada: \n" + sql + "\n");
				return statement.executeQuery(sql);
			} catch (SQLException e) {
				LOGGER.warn(e);
				throw new TimeoutException(e.getMessage());
			}
		}
		
		public void close(){
			try {
				String userName = connect.getMetaData().getUserName();
				this.connect.commit();
				this.statement.close();
				LOGGER.info("Conexão encerrada!");
				LOGGER.info("Usuário '"+ userName +"' foi desconectado.\n");
			} catch (SQLException e) {
				LOGGER.warn(e);
			}
		}
	}

}
