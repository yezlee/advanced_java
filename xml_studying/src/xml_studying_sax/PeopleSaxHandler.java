package xml_studying_sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


//SAX를 통해 파싱을 하기 위해 먼저 DefaultHandler를 상속받는 Handler 클래스를 작성
public class PeopleSaxHandler extends DefaultHandler{ //필수로 상속 받아야 함
	
	//파싱한 사람객체를 넣을 리스트
	private List<Person> personList;
	//파싱한 사람 객체
	private Person person;
	//character 메소드에서 저장할 문자열 변수
	private String str;
	
	//PeopleSaxHandler handler = new PeopleSaxHandler(); 이걸 해주니까
	//위에 객체생성하면서 생성자 호출시 리스트를 생성함
	public PeopleSaxHandler() {
		personList = new ArrayList<>();
	}
	
	@Override //DefaultHandler
	public void startElement(String uri, String localName, String name, Attributes att) {
		//시작 태그를 만났을 때 발생하는 이벤트
		if(name.equals("person")) {
			person = new Person();
			personList.add(person);
		}
	}
	
	@Override//DefaultHandler
	public void characters(char[] ch, int start, int length) { //안에 속성들을 캐릭터에 넣고 시작태그를 넣고 얼마길이만큼 내가 뽑아올거냐 라고 정해주는거 
		//태그와 태그 사이의 내용을 처리
		str = new String(ch,start,length);
	}
	
	
	@Override//DefaultHandler
	public void endElement(String uri, String localName, String name) {
		//끝 태그를 만났을 때,
		if(name.equals("age")) {
			person.setAge(Integer.parseInt(str));
		}else if(name.equals("name")) {
			person.setName(str);
		}else if(name.equals("gender")) {
			person.setGender(str);
		}else if(name.equals("role")) {
			person.setRole(str);
		}
	}
	
	
    public List<Person> getPersonList(){ 
		return personList;
	}
	public void setPersonList(List<Person> personList) {
		this.personList=personList;
	}
}
