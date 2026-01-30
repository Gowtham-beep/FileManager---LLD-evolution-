package com.fileManager.fileIO;

public interface IFileIOStratergy {
    
    public void save(String filePath,byte[] data);
    public byte[] read(String filePath);
    public void delete(String filePath);
    public boolean find(String filePath);
}
