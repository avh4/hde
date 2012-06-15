package net.avh4.system.datastore;

public interface DataStore {

    void writeData(String path, String data) throws DataStoreException;

}
