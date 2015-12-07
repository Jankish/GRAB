import java.io.*;
import java.nio.charset.StandardCharsets;

public class Parser {

	public Parser(File filename) {
		String line;
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "iso-8859-1"));
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
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

	public static void main(String[] args){
		File fileDir = new File("/Users/daniel/Documents/miscellaneous/grossParse/temp.kos");
		Parser prs = new Parser(fileDir);
	}
}

