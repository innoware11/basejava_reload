package ru.basejava.exception;

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Exception e) {
        super(message, e);
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message, e);
    }
}