package io.nzbee.test.integration.entity;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_RefreshSchema {

	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	 
    @Test
    @Rollback(false)
    public void refreshSchema() {
    	Connection con = null;
		try {
			con = database.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_schema.sql"));
		System.out.println("Schema Refreshed!");
    }
    
    @Test
    @Rollback(false)
    public void refreshData() {
    	Connection con = null;
		try {
			con = database.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_data.sql"));
    	System.out.println("Data Refreshed!");
    }
}