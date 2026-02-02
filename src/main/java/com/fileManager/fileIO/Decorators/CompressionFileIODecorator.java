package com.fileManager.fileIO.Decorators;

import com.fileManager.fileIO.IFileIOStratergy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import java.io.ByteArrayInputStream;

public class CompressionFileIODecorator extends FileIODecorator {
    public CompressionFileIODecorator(IFileIOStratergy stratergy) {
        super(stratergy);
    }

    @Override
    public void save(String filePath, byte[] data) {
        try {
            byte[] compressedData = compress(data);
            super.save(filePath, compressedData);
        } catch (IOException e) {
            throw new RuntimeException("Compression failed", e);
        }
    }

    @Override
    public byte[] read(String filePath) {
        byte[] compressedData = super.read(filePath);
        try {
            return decompress(compressedData);
        } catch (IOException e) {
            throw new RuntimeException("Decompression failed", e);
        }
    }

    private byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputStream)) {
            deflaterOutputStream.write(data);
        }
        return outputStream.toByteArray();
    }

    private byte[] decompress(byte[] compressedData) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (InflaterInputStream inflaterInputStream = new InflaterInputStream(inputStream)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inflaterInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
        }
        return outputStream.toByteArray();
    }
}