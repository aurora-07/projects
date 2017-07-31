package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by aviam on 7/31/2017.
 */
public class Retriever {
    private File[][] db;
    private BufferedReader br;

    public Retriever(File[][] db){
        try{
            this.br = new BufferedReader(new FileReader(DatabaseProperties.getDbFile()));
            this.db = db;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public File[][] populateDB(){
        try{
            this.db = new File[DatabaseProperties.getRows()][DatabaseProperties.getColumns()];
            if(DatabaseProperties.getDbFile().length() == 0){
                Logger logger = new Logger(this.db);
                logger.setAllToDefaultFiles();
            }
            else{
                readFromFile(this.br);
            }
            this.br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.db;
    }

    private void readFromFile(BufferedReader br){
        for(int i = 0; i < DatabaseProperties.getRows(); i++){
            for(int j = 0; j < DatabaseProperties.getColumns(); j++){
                try{
                    String dir = br.readLine();
                    if(dir != null){
                        this.db[i][j] = new File(dir);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
