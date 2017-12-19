package ramunas.Alksnys;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class FileManager {
	private List<String> atachedFiles;
	private String contactFile;

	public FileManager() {
		atachedFiles = new ArrayList<>();
		contactFile = "";
	}

	public void addFile(String filePath) {
		if (!filePath.isEmpty()) {
			atachedFiles.add(filePath);
		} else {
			JOptionPane.showMessageDialog(null, Const.noAtachFileText, "Informatio", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void setContFile(String filePath) {
		if (!filePath.isEmpty()) {
			contactFile = filePath;
		} else {
			JOptionPane.showMessageDialog(null, Const.noContactFileText, "Information",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void clearList(){
		atachedFiles.clear();
	}
	public List<String> getList(){
		return atachedFiles;
	}
	public String getContFile(){
		return contactFile;
	}
}
