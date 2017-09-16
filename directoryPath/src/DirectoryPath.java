
import javax.swing.filechooser.FileSystemView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class DirectoryPath {

    private static final String SEPARATOR = File.separator;
    private static final String ROOT;
    private static final String HOME_DIR = FileSystemView.getFileSystemView().getDefaultDirectory().toString();
    private static final String TEST_FOLDER_NAME = "test";
    private static final String TEST_FOLDER = HOME_DIR + SEPARATOR + TEST_FOLDER_NAME;
    private static String fileName = "test";
    private static String fileType = ".txt";
    private static String filePath;

    static {
        while (true){
            int i = 0;
            for (File f : File.listRoots()){
                System.out.println(i++ + " -> " + f.toString());
            }

            System.out.print("Input root number: ");
            byte rootNumber = new Scanner(System.in).nextByte();
            if (rootNumber >= 0 &&rootNumber < File.listRoots().length){
                ROOT = File.listRoots()[rootNumber].getAbsolutePath();
                break;
            }
        }

    }

    public static void main (String args[]) throws IOException {
        fileName = fileName.concat(fileType);
        filePath = TEST_FOLDER + SEPARATOR + fileName;
        new File(TEST_FOLDER).mkdir();

        if (Files.exists(Paths.get(filePath))){
            System.out.print("The file: " + fileName + ", already exist!\n" +
                    "Do you want to replace it: Yes or No: ");
            boolean isReplaced = new Scanner(System.in).next().equals("Yes") ? true : false;
            if (!isReplaced){
                System.out.println("Input another file name: ");
                fileName = new Scanner(System.in).next();
                main(args);
            }
            new File(filePath).delete();
        }

        new File(filePath).createNewFile();


        System.out.println("Writing in " + filePath + "\nPlease wait!");
        addToFile(new File(ROOT));
        System.out.println("Done");
        return;
    }

    public static void addToFile(File file){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true))){
            bw.write(file.getAbsolutePath());
            bw.newLine();
        } catch (IOException e){
            e.printStackTrace();
        }

        if(file.isDirectory()){
            String[] subNote = file.list();
            if (subNote != null){
                for(String filename : subNote){
                    addToFile(new File(file, filename));
                }
            }
        }

    }
}
