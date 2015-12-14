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
			printList(order);
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

		String filename = createPath(file);
		System.out.println(filename);

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
		}
	}

	private String createPath(String filename) {
		StringBuilder sb = new StringBuilder();
		int index = filename.indexOf(".");
		sb.append("/Users/daniel/Documents/");
		sb.append(filename.substring(0,index));
		sb.append(".");
		sb.append("xls");
		return sb.toString();
	}

	private static void writeDataSheet(WritableSheet s) {

		/* Format the Font */
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);
		WritableCellFormat cf = new WritableCellFormat(wf);
		cf.setWrap(true);

		/* Creates Label and writes date to one cell of
		 * sheet*/
		Label l = new Label(0,0,"Date",cf);
		s.addCell(l);
		WritableCellFormat cf1 = 
			new WritableCellFormat(DateFormats.FORMAT9);

		DateTime dt = 
			new DateTime(0,1,new Date(), cf1, DateTime.GMT);

		s.adCell(dt);

		/* Creates Label and writes
		 * float number to one cell
		 * of sheet*/
		l = new Label(2,0,"Float", cf);
		s.addCell(l);
		WritableCellFormat cf2 = new WritableCellFormat(NumberFormats.FLOAT);
		Number n = new Number(2,1,3.1415926535,cf2);
		s.addCell(n);

		n = new Number(2,2,-3.1415926535, cf2);
		s.addCell(n);

		/* Creates
		 * Label
		 * and
		 * writes
		 * float
		 * number
		 * upto
		 * 3 
		 *        decimal
		 *        to
		 *        one
		 *        cell
		 *        of
		 *        sheet
		 *        */
		l = new Label(3,0,"3dps",cf);
		s.addCell(l);
		NumberFormat dp3 = new NumberFormat("#.###");
		WritableCellFormat dp3cell = new WritableCellFormat(dp3);
		n = new Number(3,1,3.1415926535,dp3cell);
		s.addCell(n);

		/* Creates
		 * Label
		 * and
		 * adds
		 * 2
		 * cells
		 * of
		 * sheet*/
		l = new Label(4, 0, "Add 2 cells",cf);
		s.addCell(l);
		n = new Number(4,1,10);
		s.addCell(n);
		n = new Number(4,2,16);
		s.addCell(n);
		Formula f = new Formula(4,3, "E1+E2");
		s.addCell(f);

		/* Creates
		 * Label
		 * and
		 * multipies
		 * value
		 * of
		 * one
		 * cell
		 * of
		 * sheet
		 * by
		 * 2*/
		l = new Label(5,0, "Multipy by 2",cf);
		s.addCell(l);
		n = new Number(5,1,10);
		s.addCell(n);
		f = new Formula(5,2, "F1 * 3");
		s.addCell(f);

		/* Creates
		 * Label
		 * and
		 * divide
		 * value
		 * of
		 * one
		 * cell
		 * of
		 * sheet
		 * by
		 * 2.5
		 * */
		l = new Label(6,0, "Divide",cf);
		s.addCell(l);
		n = new Number(6,1, 12);
		s.addCell(n);
		f = new Formula(6,2, "F1/2.5");
		s.addCell(f);
	}
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
