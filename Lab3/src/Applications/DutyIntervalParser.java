package Applications;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class DutyIntervalParser {
	public static List<String> parseFileBulk(String filePath) throws IOException {
		String tempString, buffer = "";
		FileReader fl = new FileReader(filePath);
		BufferedReader bf = new BufferedReader(fl);
		List<String> ret = new ArrayList<>();
		while((tempString = bf.readLine()) != null) {
			buffer += tempString;
		}
		buffer = buffer.replaceAll("\\s|¡¡", "");
		//System.out.println(buffer);
		String temp;
		String employeeTemp = "";
		String periodTemp = "";
		String rosterTemp = "";
		Pattern pattern = Pattern.compile("Employee\\{([a-zA-Z]+\\{[a-zA-Z]+,\\d{3,3}-\\d{4,4}-\\d{4,4}\\})+\\}"
				+ "|Period\\{\\d{4,4}-\\d{2,2}-\\d{2,2},\\d{4,4}-\\d{2,2}-\\d{2,2}\\}"
				+ "|Roster\\{([a-zA-Z]+\\{\\d{4,4}-\\d{2,2}-\\d{2,2},\\d{4,4}-\\d{2,2}-\\d{2,2}\\})+\\}");
        Matcher matcher = pattern.matcher(buffer);
        while(matcher.find()) {
        	temp = matcher.group();
        	if(temp.contains("Employee")) {
        		employeeTemp = temp;
        	}else if (temp.contains("Period")) {
				periodTemp = temp;
			}else if (temp.contains("Roster")){
				rosterTemp = temp;
			}
        }
		fl.close();
		bf.close();
		ret.add(periodTemp);
		ret.add(employeeTemp);
		ret.add(rosterTemp);
/*
		System.out.println(periodTemp + " 001");
		System.out.println(employeeTemp + " 002");
		System.out.println(rosterTemp + " 003");
*/
		return ret;
	}
	
	
	public static void main(String[] args) throws IOException {
		parseFileBulk("test/txt/test2.txt");
	}

}
