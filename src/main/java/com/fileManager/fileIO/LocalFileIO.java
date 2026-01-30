package com.fileManager.fileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.fileManager.Notification.EventNotifier;

public class LocalFileIO implements IFileIOStratergy {
    @Override
    public void save(String filePath,byte[] data){
       File file = new File(filePath);
       try(FileOutputStream fos = new FileOutputStream(file)){
        fos.write(data);
        EventNotifier.getInstance().notifyObservers("File saved");
        return;
       }catch(Exception e){
        e.printStackTrace();
       }
        
    }
    @Override
    public byte[] read(String filePath){
        File file = new File(filePath);
        boolean exists = file.exists();
        if(!exists){
            return new byte[0];
        }
        try{FileInputStream fis = new FileInputStream(file);
            byte[] data = fis.readAllBytes();
            fis.close();
            EventNotifier.getInstance().notifyObservers("File Read");
            return data;

        }catch(Exception e){
            e.printStackTrace();
            return new byte[0];
        }

    }
    @Override
    public void delete(String filePath){
        File file = new File(filePath);
        file.delete();
    }
    @Override
    public boolean find(String filePath){
        File file = new File(filePath);
        return file.exists();
    }
}
