package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

   public String filepath;

   //The class constructor called with argument as Filepath defined
    public  ReadProperties(String filepath){

        this.filepath =filepath;
    }

    // The method to read the properties value from Properties File
    public String readProperty(String propkey){
        String propval ="";
        int check =0;

        try {
            while (check ==0){

                File readPropFile = new File(filepath);
                if (readPropFile.exists()){

                    Properties properties = new Properties();
                    FileInputStream fio = new FileInputStream(readPropFile);
                    properties.load(fio);
                    propval = properties.getProperty(propkey);
                    check =1;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  propval;
    }

}
