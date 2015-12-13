import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.text.NumberFormat;
import java.text.*;
import java.lang.Number;

import jxl.*;
import java.util.*;
import jxl.Workbook;
import jxl.write.*;

public class Model {

	private boolean checkpoint;
	private ArrayList<Data> dataList;

	public void process(ArrayList<ListData> files) {
		for (int i = 0; i < files.size(); i++) {
			System.out.println("File " + (i+1));
			dataList = new ArrayList<Data>();
			extractInfo(files.get(i).getFilePath());
			createExcel(files.get(i).getFileName());
		}
	}

	private void extractInfo(File filename) {
		checkpoint = false;
		String line;
		try {
			ArrayList<String> order = new ArrayList<String>();
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "iso-8859-1"));
			while ((line = input.readLine()) != null) {
				if (line.equalsIgnoreCase("hämtas"))
					checkpoint = true;
				if (checkpoint) 
					order.add(new String(line));
			}
			//printList(order);
			int quant = Integer.parseInt(order.get(2));
			int rows = 9;
			int outer = (quant * rows);
			double price;
			double total;
			NumberFormat format = NumberFormat.getInstance(new Locale("sv","SE"));
			Number number;
			for (int i = 4; i <= outer; i+=9){
				try {
					number = format.parse(order.get(i+5));
					price = number.doubleValue();
					number = format.parse(order.get(i+7));
					total = number.doubleValue();
					Data d = new Data(order.get(i), Integer.parseInt(order.get(i+1)), Integer.parseInt(order.get(i+2)), order.get(i+3), order.get(i+4), price, total);
					dataList.add(d);
				} catch (ParseException numExcep) {
					numExcep.printStackTrace();
					System.exit(1);
				}
			}
			printDataList(dataList);
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

	public void createExcel(String file) {
		
		System.out.println("Before replace = " + file);
		file.replace(".kos",".xls");
		System.out.println("After replace = " + file);
		String filename = "~/Document/";
		filename.concat(file);
		System.out.println(filename);
		
/**
		try {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("sv", "SE"));
			WritableWorkbook workbook = Workbook.createWorkbook(new File(filename), ws);
			WritableSheet s = workbook.createSheet("Specifikation", 0);
			//writeDataSheet(s);
			workbook.write();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		**/
	}


	private static void writeDataSheet(WritableSheet s) {
	
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
