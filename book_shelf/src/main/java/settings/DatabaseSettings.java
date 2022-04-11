package settings;

public class DatabaseSettings {
	
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	
	public static final String JDBC_URL = "jdbc:mysql://localhost/jsp_book_shelf?connectionCollation=utf8mb4_general_ci";
	
	public static final String DB_USER = "root";
	
	public static final String DB_PASS = "root";
	
	public static final int DB_EXECUTION_SUCCESS = 1;
	
	public static final int DB_EXECUTION_FAILURE = 0;
	
	public static final int DB_EXECUTION_FAILURE_ERR_DUP_KEYNAME = 1062;

}
