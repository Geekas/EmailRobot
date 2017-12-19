package ramunas.Alksnys;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
	private List<String> records = new ArrayList();
	public List<String> getTestData(String fileName) throws IOException {
		records = Files.readAllLines(Paths.get(fileName));		
		return records;
	}
}