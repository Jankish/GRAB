import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.text.*;

public class Model {

	private boolean checkpoint;
	private ArrayList<Data> dataList;

	public void process(File filename) {
		dataList = new ArrayList<Data>();
		extractInfo(filename);
		createExcel();
	}

	private void extractInfo(File filename) {
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
			printList(order);
			process(order, dataList);
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

	private void process(ArrayList<String> info, ArrayList<Data> dataList) {
		//ArrayList<Data> dataList = new ArrayList<Data>();
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
			dataList.add(d);
			} catch (ParseException numExcep) {
				numExcep.printStackTrace();
				System.exit(1);
			}
		}
		//printDataList(processed);
	}

	public void createExcel() {
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
}
