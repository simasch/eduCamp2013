package file.internal;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import javax.resource.ResourceException;
import javax.resource.spi.*;
import javax.security.auth.Subject;

public class FileManagedConnectionFactory implements ManagedConnectionFactory, Serializable {

    private PrintWriter out;

    public Object createConnectionFactory(ConnectionManager cxManager) throws ResourceException {
        return new FileDataSourceImpl(out, this, cxManager);
    }

    public Object createConnectionFactory() throws ResourceException {
        return new FileDataSourceImpl(out, this, null);
    }

    public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo info) {
        return new FileManagedConnection(out, this, info);
    }

    public ManagedConnection matchManagedConnections(Set connectionSet, Subject subject, ConnectionRequestInfo info)
            throws ResourceException {
        for (Iterator it = connectionSet.iterator(); it.hasNext();) {
            FileManagedConnection gmc = (FileManagedConnection) it.next();
            ConnectionRequestInfo connectionRequestInfo = gmc.getConnectionRequestInfo();
            if ((info == null) || connectionRequestInfo.equals(info)) {
                return gmc;
            }
        }
        throw new ResourceException("Cannot find connection for info!");
    }

    public void setLogWriter(PrintWriter out) throws ResourceException {
        this.out = out;
    }

    public PrintWriter getLogWriter() throws ResourceException {
        return this.out;
    }
}
