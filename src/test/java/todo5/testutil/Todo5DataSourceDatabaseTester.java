/**
 * @(#)Todo5DataSourceDatabaseTester.java
 * 
 * Copyright (c) 2017 E-Kanegae.
 */
package todo5.testutil;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 * @version $Revision$
 */
public class Todo5DataSourceDatabaseTester extends DataSourceDatabaseTester{

    boolean isCaseSensitive;
    
    public Todo5DataSourceDatabaseTester(DataSource dataSource, boolean isCaseSensitive) {
        this(dataSource);
        this.isCaseSensitive = isCaseSensitive;
    }
    
    public Todo5DataSourceDatabaseTester(DataSource dataSource) {
        super(dataSource);
    }
    
    @Override
    public IDatabaseConnection getConnection() throws Exception {

        IDatabaseConnection databaseConnection = super.getConnection();

        DatabaseConfig config = databaseConnection.getConfig();
        config.setFeature(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES,getCaseSensitive());

        return databaseConnection;
    }

    protected boolean getCaseSensitive(){
        return this.isCaseSensitive;
    }
    
    
}
