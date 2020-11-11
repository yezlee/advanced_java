package xml_studying_sax;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class PersonSaxTest {
	public static void main(String[] args) {
		File file = new File("./src/xml_studying_sax/people.xml");	// file 대신, api 키로 만들어진 uri 주소도 가능
		SAXParserFactory factory = SAXParserFactory.newInstance();	
		// SAXParser 객체를 초기화 하려면
		// 먼저 SAXParserFactory 인스턴스를 초기화 해야한다.
		
		// 이 메소드는 SAXParserFactory 구현 클래스를 로드하고 
		// 초기화하기 위해 javax.xml.parsers.SAXParserFactory 시스템 속성을 참고한다. 
		
		// 만약 javax.xml.parser.SAXParserFactory 
		// 시스템 속성이 정의되어 있지 않을 때에는 
		// 플랫폼 디폴트의 SAXParserFactory 인스턴스가 리턴된다. 
		
		// 만약 런타임에서 SAXParserFactory 
		// 구현 클래스가 설명된 javax.xml.parsers.SAXParserFactory 
		// 속성이 로드 되지 않거나 초기화가 안되면 
		// FactoryConfigurationError가 발생된다.
		
		
		try {

			// factory를 사용할 SAXParser 의 새로운 객체 생성
			SAXParser parser = factory.newSAXParser();
			// PeopleSaxHandler 클래스 선언
			PeopleSaxHandler handler = new PeopleSaxHandler();
			parser.parse(file, handler);
			
			// PeopleSaxHandler 클래스에 있는 파싱한 사람객체를 넣은 리스트를 출력하기 위해 
			// 파싱한 사람객체리스트를 담을 리스트를 만든다.
			List<Person> list = handler.getPersonList();
			
			for(Person p:list) {
				System.out.println(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
}

