import java.text.*;
import java.util.Locale;

public class Data {
	//public String article;
	public String quantity;
	public String RSK;
	public String description;
	public String unit;
	public String price;
	public String total;
	//public double noTax;

	public Data() {
		//this.article = null;
		this.quantity = null;
		//this.RSK = null;
		this.description = null;
		this.unit = null;
		this.price = null;
		this.total = null;
		//this.noTax = 0;
	}

	public Data(String quantity, String description, String unit, String price, String total) {
		//this.article = article;
		this.quantity = quantity;
		//this.RSK = RSK;
		this.description = description;
		this.unit = unit;
		this.price = price;
		this.total = price;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//sb.append(article + ";");
		sb.append(description + ";");
		sb.append(quantity + ";");
		sb.append(price + ";");
		sb.append(total);
		return sb.toString();
	}

}

