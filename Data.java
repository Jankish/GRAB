public class Data {
	public String article;
	public int quantity;
	public int RSK;
	public String description;
	public String unit;
	public double price;
	public double total;
	//public double noTax;

	public Data() {
		this.article = null;
		this.quantity = 0;
		this.RSK = 0;
		this.description = null;
		this.unit = null;
		this.price = 0;
		this.total = 0;
		//this.noTax = 0;
	}

	public Data(String article, int quantity, int RSK, String description, String unit, double price, double total) {
		this.article = article;
		this.quantity = quantity;
		this.RSK = RSK;
		this.description = description;
		this.unit = unit;
		this.price = price;
		this.total = price;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(article + ";");
		sb.append(description + ";");
		sb.append(quantity + ";");
		sb.append(price + ";");
		sb.append(total);
		return sb.toString();
	}

}

