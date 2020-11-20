package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BuiltSqlMapClient {
	private static SqlMapClient smc;
	
	public static SqlMapClient getSqlMapClient() {
		return smc;
	}
	
	static {
		Reader rd = null;
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // Reader객체 닫기
			
			// 환경 설정 끝
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(rd!=null) try {rd.close();} catch(IOException e2) {}
		}
	}
}
