package file;

import java.io.Serializable;
import javax.resource.Referenceable;

public interface FileDataSource extends Serializable, Referenceable {

    File getConnection();
}
