import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class xmlParser {
	
	/**
	 * This method parses the XML string for particular tag. If found it
	 * returns all the text between the tag as vector. 
	 * 
	 * @param xmlFileString xml in string format
	 * @param tagName tag to be parsed
	 * @return Vector text between the tag
	 */
	public static Vector getXMLTagValue(String xmlFileString, String tagName) {
		
		if (xmlFileString.length() == 0 || tagName.length() == 0) {
			return null;
		}
		
		String pattern = "<" + tagName + ">(.+?)</" + tagName + ">" ;
		Pattern r = Pattern.compile(pattern,Pattern.DOTALL | Pattern.MULTILINE);
		Matcher m = r.matcher(xmlFileString);
		
		Vector<String> myVector = new Vector<String>(3,2);
		while(m.find()){
			myVector.add(m.group(1).trim());
		}
		return myVector;
	}
	
	public static void main(String[] args) {
		
		Vector v = getXMLTagValue("<STUDENTS><STUDENT><NAME>HillyBilly</NAME>\n<AGE> \n\n 19</AGE><CLASS>10</CLASS>"
				+"</STUDENT><STUDENT><NAME>\nCaptain Kirk</NAME><AGE>20</AGE><CLASS>10</CLASS></STUDENT>"
				+"</STUDENTS>", "STUDENT");
		Vector ageV = getXMLTagValue((String)v.elementAt(0), "AGE");
		System.out.println("Age is: " + ageV.elementAt(0));
	}

}
