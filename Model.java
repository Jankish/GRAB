import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.text.*;

public class Parser {

	private boolean checkpoint;

	public Parser(File filename) {
		checkpoint = false;
		String line;
		try {
			ArrayList<Data> items = new ArrayList<Data>();
			ArrayList<String> order = new ArrayList<String>();
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "iso-8859-1"));
			while ((line = input.readLine()) != null) {
				if (line.equalsIgnoreCase("hämtas"))
					checkpoint = true;
				if (checkpoint) 
					order.add(new String(line));
			}
			//printList(order);
			process(order);
		} catch (FileNotFoundException found) {
			found.printStackTrace();
			System.exit(1);
		} catch (UnsupportedEncodingException encode) {
			encode.printStackTrace();
			System.exit(1);
		} catch (IOException ioexcep) {
			ioexcep.printStackTrace();
			System.exit(1);
		}
	}

	public ArrayList<Data> process(ArrayList<String> info) {
		ArrayList<Data> processed = new ArrayList<Data>();
		int quant = Integer.parseInt(info.get(2));
		int rows = 9;
		int outer = (quant * rows);
		double price;
		double total;
		NumberFormat format = NumberFormat.getInstance(new Locale("sv","SE"));
		Number number;
		for (int i = 4; i <= outer; i+=9){
			try {
			number = format.parse(info.get(i+5));
			price = number.doubleValue();
			number = format.parse(info.get(i+7));
			total = number.doubleValue();
			Data d = new Data(info.get(i), Integer.parseInt(info.get(i+1)), Integer.parseInt(info.get(i+2)), info.get(i+3),info.get(i+4), price, total);
			processed.add(d);
			} catch (ParseException numExcep) {
				numExcep.printStackTrace();
				System.exit(1);
			}
		}
		//printDataList(processed);
		return processed;
	}

	public void printList(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public void printDataList(ArrayList<Data> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public static void main(String[] args){
		File fileDir = new File("/Users/daniel/Documents/miscellaneous/GRAB/temp.kos");
		Parser prs = new Parser(fileDir);
	}
}

