import java.io.File;

public class ListData{
	
	private String filename;
	private File filepath;
	
	public ListData(String filename, File filepath) {
		this.filename = filename;
		this.filepath = filepath;
	}

	public String getFileName() {
		return filename;
	}

	public File getFilePath() {
		return filepath;
	}

	public String toString() {
		return filename + " " + filepath;
	}
}
