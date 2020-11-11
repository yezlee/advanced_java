import java.io.File;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
public class XmlParsering {
 
	public static void main(String[] args) {
 
		try {
			
			// 파일을 읽어옴
			File file = new File("D:/D_Other/xml/book3.xml");
			// 문서를 읽기위한 공장(Factory)를 만들어줌
			DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance(); //xml을 읽기위한 준비과정
			// 빌더를 생성 
			DocumentBuilder docBuild = docBuildFact.newDocumentBuilder();
			// 생성된 빌더를 통해서 xml문서를 Document객체로 파싱해 가져옴
			Document doc = docBuild.parse(file);
			// dom tree가 XML문서의 구조대로 완성해줌
			doc.getDocumentElement().normalize();//파싱한걸 트리구조로 만들어줌
			
			// 노드의 이름을 출력
			System.out.println("루트 요소 : " + doc.getDocumentElement().getNodeName());
			System.out.println();
 
			// person엘리먼트 리스트
			NodeList personlist = doc.getElementsByTagName("person");
			
			// 반복문을 실행하여 노드의 정보를 얻어옴
			for (int i = 0; i < personlist.getLength(); i++) {
 
				System.out.println("-----------------"+ (i+1) +"번 고객 정보 ------------------");
 
				Node personNode = personlist.item(i);
 
				if (personNode.getNodeType() == Node.ELEMENT_NODE) {
					// person엘리먼트 
					Element personElmnt = (Element) personNode;
 
					// name 태그의 정보 출력
					NodeList nameList= personElmnt.getElementsByTagName("name");
					Element nameElmnt = (Element) nameList.item(0);
					Node name = nameElmnt.getFirstChild();
					System.out.println("name    : " + name.getNodeValue());
 
					// tel 태그의 정보출력
					NodeList telList= personElmnt.getElementsByTagName("tel");
					Element telElmnt = (Element) telList.item(0);
					Node tel = telElmnt.getFirstChild();
					System.out.println("tel     : " + tel.getNodeValue());
 
					// address 태그의 정보 출력
					NodeList addressList= personElmnt.getElementsByTagName("address");
					Element addressElmnt = (Element) addressList.item(0);
					Node address = addressElmnt.getFirstChild();
					System.out.println("address : " + address.getNodeValue());
				}
 
				System.out.println("---------------------------------------------");
				System.out.println();
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
