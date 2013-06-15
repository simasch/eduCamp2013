package file.internal;

import file.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;

public class FileConnection implements File, LocalTransaction {

    private String buffer;
    private FileOutputStream fileOutputStream;
    private ConnectionRequestInfo connectionRequestInfo;
    public final static String FILE_NAME = "c:\\temp\\jcafile.txt";
    private FileManagedConnection genericManagedConnection;
    private PrintWriter out;

    public FileConnection(PrintWriter out, FileManagedConnection genericManagedConnection, ConnectionRequestInfo connectionRequestInfo) {
        this.out = out;
        this.genericManagedConnection = genericManagedConnection;
        this.connectionRequestInfo = connectionRequestInfo;
        this.initialize();
    }

    private void initialize() {
        try {
            this.buffer = null;
            this.fileOutputStream = new FileOutputStream(FILE_NAME, true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException("Cannot initialize OutputStream: " + FILE_NAME);
        }

    }

    public void write(String content) {
        this.buffer = content;
    }

    public void close() {
        this.genericManagedConnection.close();
    }

    public void destroy() {
        try {
            if (this.fileOutputStream != null) {
                this.fileOutputStream.close();
            }
            this.fileOutputStream = null;
            this.buffer = null;
        } catch (IOException ex) {
            Logger.getLogger(FileConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException("Cannot close stream: " + ex, ex);
        }
    }

    public void begin() throws ResourceException {
        this.initialize();
    }

    public void commit() throws ResourceException {
        try {
            this.fileOutputStream.write(this.buffer.getBytes());
            this.fileOutputStream.flush();
            this.fileOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(FileConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new ResourceException(ex);
        }
    }

    public void rollback() throws ResourceException {
        this.buffer = null;
        try {
            this.fileOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(FileConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new ResourceException(ex);
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileConnection other = (FileConnection) obj;
        if (this.connectionRequestInfo != other.connectionRequestInfo && (this.connectionRequestInfo == null || !this.connectionRequestInfo.equals(other.connectionRequestInfo))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.connectionRequestInfo != null ? this.connectionRequestInfo.hashCode() : 0);
        return hash;
    }
}