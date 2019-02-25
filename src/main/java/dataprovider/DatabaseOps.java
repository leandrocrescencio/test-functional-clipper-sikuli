package dataprovider;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class DatabaseOps {
	
	private DatabaseOps() {
		throw new IllegalStateException("Utility class");
	}
	
	private static final Logger LOGGER = Logger.getLogger(DatabaseOps.class);

	/**
	 * Converte o formato da data para outro especificado.
	 * ex: yyyy-MM-dd para dd/MM/yyyy
	 * 
	 * @param date
	 * @param fromFormat Formato da data atual.
	 * @param toFormat Formato que deseja converter a data.
	 * @return Retorna a nova data formatada.
	 */
	public static String getDateFormatted(String date, String fromFormat, String toFormat){
		try {
			DateFormat oldFormat = new SimpleDateFormat(fromFormat); 
			Date myDate = oldFormat.parse(date);  

		    SimpleDateFormat newDate = new SimpleDateFormat(toFormat);
		    
		    return newDate.format(myDate);
		} catch (ParseException e) {
			LOGGER.error(e);
			LOGGER.warn(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Converte a data de String para Date. <br>
	 * @param date Data que se deseja conveter.
	 * @param format Formato da data (ex: dd/mm/yyyy).
	 * @return um objeto de Date.
	 */
	public static Date getDate(String date, String format){
		try {
			DateFormat dtFormat = new SimpleDateFormat(format); 
		    return dtFormat.parse(date);
		    
		} catch (ParseException e) {
			LOGGER.error(e);
			LOGGER.warn(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Realiza a query e armazena o resultado numa coleção de linhas 
	 *<br>na qual cada linha possui um array de colunas.
	 *<p>Ex: 
	 *<br> Data        | Item | Responsavel
	 *<br> 31/07/2014    1831   Leandro
	 *<br> 01/08/2014    1588   Leonardo
	 *
	 *<p>result.get(0)[0] // resulta em: 31/07/2014
	 *<br>result.get(1)[2] // resulta em: Leonardo <br>
	 * @param rs ResultSet obtido a partir do método "executeQuery" da classe Connection.
	 * @return Coleção de linhas com colunas.
	 */
	public static List<String[]> getQueryResultByColumn(ResultSet rs){
		ResultSetMetaData data;
		List<String[]> queryResult = new ArrayList<>();
		
		try {
			data = rs.getMetaData();
			int colSize = data.getColumnCount();
			
			while(rs.next()){
				String[] colValue = new String[colSize];
				
				for(int i = 1; i <= colSize; i++){
					colValue[i-1] = rs.getString(i);
				}
				queryResult.add(colValue);
			}
			
			if(queryResult.isEmpty()){
				LOGGER.warn("Nenhum resultado foi encontrado no banco de dados!");
			}
			
		} catch (SQLException e) {
			LOGGER.error(e);
			LOGGER.warn(e.getMessage());
		}
		
		return queryResult;
	}
	
	/**
	 * Realiza a query e armazena o resultado numa coleção de linhas 
	 *<br>com todas as colunas concatenadas.
	 * @param rs ResultSet obtido a partir do método "executeQuery" da classe Connection.
	 * @return Coleção de linhas.
	 */
	public static List<String> getQueryResultByLine(ResultSet rs){
		List<String[]> queryResult = getQueryResultByColumn(rs);
		List<String> allLine = new ArrayList<>();
		int colSize = queryResult.get(0).length;
		String result = "";
		
		for(int i = 0; i < queryResult.size(); i++){
			for(int j = 0; j < colSize; j++){
				if(j > 0 && j <= colSize-1){
					result = result + ", ";
				}
				result = result + queryResult.get(i)[j];
				
			}
			allLine.add(result);
			result = "";
		}
		
		return allLine;
	}
	
	/**
	 * Realiza a query e armazena o resultado numa coleção de linhas 
	 * <br>com todas as colunas concatenadas.
	 * @param rs ResultSet obtido a partir do método "executeQuery" da classe Connection.
	 * @return todas as informações, separadas por item, em uma lista.
	 */
	public static List<String> getQueryResult(ResultSet rs){
		List<String[]> queryResult = getQueryResultByColumn(rs);
		List<String> allLine = new ArrayList<>();
		int colSize = queryResult.get(0).length;
		
		for(int i = 0; i < queryResult.size(); i++){
			for(int j = 0; j < colSize; j++){
				if(j <= colSize-1){
					allLine.add(queryResult.get(i)[j]);
				}
			}
		}
		return allLine;
	}

}
