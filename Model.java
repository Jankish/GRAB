import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.text.NumberFormat;
import java.text.*;
import java.lang.Number;
import java.util.Locale;
import jxl.*;
import java.util.*;
import jxl.Workbook;
import jxl.write.*;

public class Model {

	private boolean checkpoint;
	private ArrayList<Data> dataList;
	//private Locale loc = new Locale("sv", "SE");
	//private NumberFormat format = NumberFormat.getInstance(loc);
	//DecimalFormat df = new DecimalFormat("#,00", DecimalFormatSymbols.getInstance(loc));
	//NumberFormat format = NumberFormat.getInstance();

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
			//	printList(order);
			int quant = Integer.parseInt(order.get(2));
			int rows = 9;
			int outer = (quant * rows);
			double price;
			double total;
			//DecimalFormat decimal = (DecimalFormat) format;
			Number number;
			for (int i = 4; i <= outer; i+=9){
				Data d = new Data(order.get(i+1), order.get(i+3), order.get(i+4), order.get(i+5), order.get(i+7));
				dataList.add(d);
				/*	try {
					System.out.println(order.get(i+5));
					number = format.parse(order.get(i+5));
					System.out.println("Number is " + number);
					price = number.doubleValue();
					System.out.println("Price is " + price);
					number = format.parse(order.get(i+7));
					total = number.doubleValue();
					Data d = new Data(order.get(i), Integer.parseInt(order.get(i+1)), Integer.parseInt(order.get(i+2)), order.get(i+3), order.get(i+4), price, total);
					dataList.add(d);
					} catch (ParseException numExcep) {
					numExcep.printStackTrace();
					System.exit(1);
					}
				 **/
			}
			//	printDataList(dataList);
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

		String filename = createPath(file);
		System.out.println(filename);

		/*
		   try {
		   WorkbookSettings ws = new WorkbookSettings();
		   ws.setLocale(new Locale("sv", "SE"));
		   WritableWorkbook workbook = Workbook.createWorkbook(new File(filename), ws);
		   WritableSheet s = workbook.createSheet("Sheet1", 0);
		//writeDataSheet(s);
		workbook.write();
		workbook.close();
		} catch (IOException e) {
		e.printStackTrace();
		} catch (WriteException e) {
		e.printStackTrace();
		}**/

		try {

			File newFile = new File(filename);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "UTF8"));
			//	BufferedWrite output = new BufferedWriter(new OutputStreamReader(new FileInputS§tream(filename), "iso-8859-1"));
			//FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
			//BufferedWriter bw = new BufferedWriter(fw);
			//bw.write("Artikel;Antal/st;Pris/st;Total\n");
			out.append("Artikel;Antal/st;Pris/st;Total\n");
			for (Data d : dataList) {
				out.append(d.toString());
				out.append("\n");
				//bw.write(d.toString());
				//bw.write("\n");
			}
			//bw.close();
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException unsuppEn) {
			unsuppEn.printStackTrace();
		} catch (IOException ioE) {
			ioE.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String createPath(String filename) {
		StringBuilder sb = new StringBuilder();
		int index = filename.indexOf(".");
		//sb.append("/Users/daniel/Documents/");
		sb.append("C:\\Users\\Perica\\Documents\\PJ3 VVS Malmö AB\\GRAB\\");
		sb.append(filename.substring(0,index));
		sb.append(".");
		sb.append("txt");
		return sb.toString();
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
