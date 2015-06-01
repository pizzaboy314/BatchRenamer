import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class Worker {
	
	private static JFileChooser fc;
	public ArrayList<File> files;
	public static String find;
	public static String replace;

	public static void main(String[] args) {
		find = null;
		replace = null;
		
		File headDir = null;
		headDir = getPath();
		System.out.println(headDir.getAbsolutePath());
		
		find = (String) JOptionPane.showInputDialog(null, "Enter a file string to replace.",
				"Find", JOptionPane.PLAIN_MESSAGE, null, null, null);

		if (find == null || find.equals("")) {
			System.exit(0);
		}
		
		replace = (String) JOptionPane.showInputDialog(null, "Enter your replacement.","Replace", JOptionPane.PLAIN_MESSAGE, null, null, null);
		
		if (replace == null || replace.equals("")) {
			System.exit(0);
		}
		
		doRename(headDir);
	}
	
	public static File getPath(){
		File returnFile = null;
		File resultPath = new File(System.getProperty("user.dir"));
		fc = new JFileChooser(resultPath);
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();

			if(f.isDirectory()){
				returnFile = f;
			}
		}
		
		return returnFile;
	}
	
	public static boolean doRename(File directory){
		boolean state = true;
		if(directory.isDirectory()){
			for(File f : directory.listFiles()){
				if(f.isDirectory()){
					state = doRename(f);
				} else {
					f.renameTo(new File(f.getAbsolutePath().replaceAll(find, replace)));
				}
			}
		} else {
			directory.renameTo(new File(directory.getAbsolutePath().replaceAll(find, replace)));
		}
		return state;
	}

}
