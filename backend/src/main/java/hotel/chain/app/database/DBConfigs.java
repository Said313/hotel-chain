package hotel.chain.app.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConfigs {
    protected String dbHost = "localhost";
    protected String dbPort = "3306";
    protected String dbLogin = "root";
    protected String dbPassword = "IronDragon1327";
//    protected String dbPassword = "gaffor402";
    protected String dbName = "hotel";

    public static boolean isEmpty(ResultSet rs) throws SQLException {
        return (!rs.isBeforeFirst() && rs.getRow() == 0);
    }

}
