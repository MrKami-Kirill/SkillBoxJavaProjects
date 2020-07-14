import com.google.common.base.Strings;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

@Log4j2
@Log4j
public class FileAccess {

  private FileSystem fileSystem;

  /**
   * Initializes the class, using rootPath as "/" directory
   *
   * @param rootPath - the path to the root of HDFS, for example, hdfs://localhost:32771
   */
  public FileAccess(String rootPath) {
    configureFileSystem(rootPath);
  }

  public void configureFileSystem(String rootPath) {
    try {
      Configuration configuration = new Configuration();
      configuration.set("dfs.client.use.datanode.hostname", "true");
      configuration.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
      configuration.setBoolean("dfs.support.append", true);
      System.setProperty("HADOOP_USER_NAME", "root");
      fileSystem = FileSystem.get(new URI(rootPath), configuration);
    } catch (URISyntaxException | IOException exception) {
      log.error(exception);
    }
  }

  /**
   * Creates empty file or directory
   *
   * @param path
   */
  public void create(String path) {
    Path file = new Path(fileSystem.getUri() + path);
    try {
      if (fileSystem.exists(file)) {
        fileSystem.delete(file, true);
      }
      fileSystem.create(file);
      log.info("\"" + path + "\" created!");
    } catch (IOException exception) {
      log.error(exception);
    }
  }

  /**
   * Appends content to the file
   *
   * @param path
   * @param content
   */
  public void append(String path, String content) {
    Path file = new Path(fileSystem.getUri() + path);
    Boolean isAppendable = Boolean.valueOf(fileSystem.getConf().get("dfs.support.append"));
    try {
      if (isAppendable) {
        if (!fileSystem.exists(file)) {
          log.info("\"" + path + "\" not found!");
        } else {
          FSDataOutputStream fs_append = fileSystem.append(file);
          PrintWriter writer = new PrintWriter(fs_append);
          writer.append(content);
          writer.flush();
          fs_append.hflush();
          writer.close();
          fs_append.close();
        }
      } else {
        log.info("Please set the \"dfs.support.append\" property to true");
      }
    } catch (IOException exception) {
      log.error(exception);
    }
  }

  /**
   * Returns content of the file
   *
   * @param path
   * @return
   */
  public String read(String path) throws IOException {
    Path file = new Path(fileSystem.getUri() + path);
    if (!fileSystem.exists(file) || fileSystem.isDirectory(file)) {
      log.info("\"" + path + "\" not found!");
      return null;
    }
    FSDataInputStream fsDataInputStream = fileSystem.open(file);
    String result = IOUtils.toString(fsDataInputStream);
    if (Strings.isNullOrEmpty(result)) {
      log.info("\"" + path + "\" not found!");
      return null;
    }
    log.info("\"" + path + "\" read!");
    return result;
  }

  /**
   * Deletes file or directory
   *
   * @param path
   */
  public void delete(String path) {
    Path file = new Path(fileSystem.getUri() + path);
    try {
      if (!fileSystem.exists(file)) {
        log.info("\"" + path + "\" not found!");
      }
      fileSystem.delete(file, true);
      log.info("\"" + path + "\" deleted!");
    } catch (IOException exception) {
      log.error(exception);
    }
  }

  /**
   * Checks, is the "path" is directory or file
   *
   * @param path
   * @return
   */
  public boolean isDirectory(String path) throws IOException {
    Path file = new Path(fileSystem.getUri() + path);
    if (fileSystem.exists(file)) {
      if (fileSystem.isDirectory(file)) {
        log.info("\"" + path + "\" is directory!");
        return true;
      }
      if (fileSystem.isFile(file)) {
        log.info("\"" + path + "\" is file!");
        return true;
      }
    }
      log.info("\"" + path + "\" not found!");
      return false;
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list (String path) throws IOException {
      Path file = new Path(fileSystem.getUri() + path);
      List<String> list = new ArrayList<>();
      if (fileSystem.isDirectory(file)) {
        RemoteIterator<LocatedFileStatus> remoteIterator = fileSystem.listFiles(file, true);
        while (remoteIterator.hasNext()) {
          LocatedFileStatus locatedFileStatus = remoteIterator.next();
          list.add(locatedFileStatus.getPath().toUri().getPath());
        }
        return list;
      }
      log.info("\"" + path + "\" not found!");
      return null;
    }
  }
