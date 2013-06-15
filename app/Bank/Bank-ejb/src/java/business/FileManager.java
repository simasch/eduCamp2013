package business;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.connectorz.files.Bucket;
import org.connectorz.files.BucketStore;

@Stateless
@LocalBean
public class FileManager {

    @Resource(mappedName = "jca/BucketStore")
    private BucketStore bucketStore;

    public void writeToFile() {
        Bucket bucket = bucketStore.getBucket();
        bucket.write("abc.txt", "content".getBytes());
        bucket.close();
    }
}
