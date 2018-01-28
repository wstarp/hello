package com.cennavi.server.db;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.cennavi.server.model.TableBean;

public class DBConnectionFactory {

	private static DataSource _dataSource = null;

	public static final String ls = System.getProperty("line.separator", "/n");

	public static final String sp = File.separator;

	public void setDataSource(DataSource dataSource) {
		_dataSource = dataSource;
	}

	/**
	 * 获得表结构信息-MySQL
	 * 
	 * @param schemName
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<TableBean> getTableBean_mysql(String schemName, String tableName) {
		List<TableBean> tableBeanList = new ArrayList<TableBean>();
		JdbcTemplate connection = new JdbcTemplate(_dataSource);
		String sql = "SELECT COLUMN_NAME,COLUMN_COMMENT,CHARACTER_MAXIMUM_LENGTH,EXTRA FROM `information_schema`.`COLUMNS` where `TABLE_SCHEMA`='" + schemName + "' " + "and `TABLE_NAME`='" + tableName + "' order by `ORDINAL_POSITION`";
		// * Oracle *//
		// String sql = "SELECT COLUMN_NAME,COMMENTS as COLUMN_COMMENT, 0 as
		// CHARACTER_MAXIMUM_LENGTH from user_col_comments where Table_Name = '"
		// +
		// tableName + "'";
		Iterator its = connection.queryForList(sql).iterator();
		while (its.hasNext()) {
			Map resMap = (Map) its.next();
			BigInteger b = (BigInteger) resMap.get("CHARACTER_MAXIMUM_LENGTH");
			tableBeanList.add(new TableBean((String) resMap.get("COLUMN_NAME"), (String) resMap.get("COLUMN_COMMENT"), b, (String) resMap.get("COLUMN_NAME")));
		}
		return tableBeanList;
	}
}
