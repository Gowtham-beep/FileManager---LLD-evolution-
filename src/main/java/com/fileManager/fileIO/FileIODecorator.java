package com.fileManager.fileIO;

public abstract class FileIODecorator implements IFileIOStratergy{
    private IFileIOStratergy stratergy;

    public FileIODecorator(IFileIOStratergy stratergy){
        this.stratergy = stratergy;
    }
    @Override
    public void save(String filePath, byte[] data) {
        stratergy.save(filePath, data);
    }

    @Override
    public byte[] read(String filePath) {
        return stratergy.read(filePath);
    }

    @Override
    public void delete(String filePath) {
        stratergy.delete(filePath);
    }

    @Override
    public boolean find(String filePath) {
        return stratergy.find(filePath);
    }

}
