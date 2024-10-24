package MainCode;

import java.io.File;

public class NewDirCreationAndRemove {
    

    public static String createDir(){

        String newDirPath = System.getProperty("user.dir") + "/Downloads";

        File file = new File(newDirPath);

        if (!file.exists()){
            file.mkdir();
        } else{
            System.out.println("File already exists");
        }


        return file.getAbsolutePath();

    }


    public static void deleteDir(String dirPath){

        File newDirFile = new File(dirPath);

        if(newDirFile.exists() & newDirFile.isDirectory()){

            File[] files = newDirFile.listFiles();

            for(File file: files){
                if (file.delete()){
                    System.out.println("File has been deleted");
                } else {
                    System.out.println("File has not been deleted");
                }
            }

            if(newDirFile.delete()){
                System.out.println("Dir has been deleted");
            } else {
                System.out.println("Dir has not been deleted");
            }

        }


    }

}
