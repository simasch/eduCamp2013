package business;

import file.File;
import file.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class FileManager {

    @Resource(mappedName = "jca/file")
    private FileDataSource fds;

    public void writeToFile() {
        File file = fds.getConnection();
        file.write("Content");
        file.close();
    }
}
