package com.fileManager.FileManager;
//Stratergy design pattern
import com.fileManager.fileIO.IFileIOStratergy;

public class FileManager {
    IFileIOStratergy stratergy ;

    public FileManager(IFileIOStratergy stratergy){
        this.stratergy = stratergy;
    }
    public void setStratergy(IFileIOStratergy stratergy){
        this.stratergy = stratergy;
    }
    public void upload(String filePath, byte[] data){
        stratergy.save(filePath, data);
    }
    public byte[] download(String filePath){
        return stratergy.read(filePath);
    }

}
