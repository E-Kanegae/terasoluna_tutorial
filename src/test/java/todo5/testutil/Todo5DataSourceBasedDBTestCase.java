/**
 * @(#)Todo5DataSourceBasedDBTestCase.java
 * 
 * Copyright (c) 2017 E-Kanegae.
 */
package todo5.testutil;

import java.io.File;
import java.io.FileNotFoundException;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.springframework.util.ResourceUtils;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 * @version $Revision$
 */
public abstract class Todo5DataSourceBasedDBTestCase extends DataSourceBasedDBTestCase {

    @Override
    protected void closeConnection(IDatabaseConnection connection) throws Exception {

        assertNotNull( "DatabaseTester is not set", getDatabaseTester() );
        IDatabaseTester tester = getDatabaseTester();
        if (!tester.getConnection().getConnection().isClosed()) {
            tester.getConnection().close();
        }
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new XlsDataSet(getDataFile());
    }

    protected File getDataFile() throws FileNotFoundException {
        File xlsFile = ResourceUtils.getFile(getFilePath());
        return xlsFile;
    }

    abstract protected String getFilePath();
    abstract protected boolean getCaseSensitiveTableNm();

    @Override
    protected IDatabaseTester newDatabaseTester() {
        DataSourceDatabaseTester tester = new Todo5DataSourceDatabaseTester(getDataSource(), getCaseSensitiveTableNm());
        return tester;
    }

}
