package by.andreisergeichyk.repository;

import by.andreisergeichyk.config.TestPersistenceConfig;
import by.andreisergeichyk.util.DatabaseHelper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPersistenceConfig.class)
@Transactional
public class BaseCase {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Before
    public void init() {
        databaseHelper.deleteTestData();
        databaseHelper.prepareDatabase();
    }
}
