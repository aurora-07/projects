package testing;

import java.io.*;
import java.nio.file.Files;

/**
 * Created by aviam on 7/23/2017.
 */
public class FilePopulate {
    public FilePopulate(String filetype){
        File file = new File("src/test." + filetype);
        for(int dirName = 1; dirName <= 12; dirName++) {
            String dn = dirName < 10 ? "0" + dirName : "" + dirName;
            File dir = new File("src/time_files/" + dn);
            if (!dir.exists()) {
                dir.mkdir();
            }
            for (int min = 0; min < 60; min++) {
                String name = min < 10 ? "0" + min : "" + min;
                File dest = new File(dir.toString() + "/" + name + "." + filetype);
                try {
                    copyFileUsingJava7Files(file, dest);
                } catch (Exception e) {

                }
            }
        }
    }

    private void copyFileUsingJava7Files(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }

    public static void main(String[] args) {
        new FilePopulate("mp3");
    }
}
