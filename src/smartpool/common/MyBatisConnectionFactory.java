package smartpool.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

public class MyBatisConnectionFactory {

	private static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "SqlMapConfig.xml";
            Reader reader = Resources.getResourceAsReader(resource);
			if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
                sqlSessionFactory.getConfiguration().addMappers("smartpool.data");
			}
		}

		catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}
		catch (IOException iOException) {
			iOException.printStackTrace();
		}
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
