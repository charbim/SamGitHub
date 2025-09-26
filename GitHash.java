import java.io.File;
import java.io.IOException;

public class GitHash{

    public static void gitRepoInit() {
        try {
            GitHash.createDirectoryIfMissing("git");
            GitHash.createDirectoryIfMissing("git/objects");
            GitHash.createFileIfMissing("git/HEAD");
            GitHash.createFileIfMissing("git/INDEX");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void createDirectoryIfMissing (String path) throws IOException {
        File directoryCreator = new File(path);
        if (!directoryCreator.exists()) {
            directoryCreator.mkdir();
        }
    }

    public static void createFileIfMissing (String path) throws IOException {
        File fileCreator = new File(path);
        if (!fileCreator.exists()) {
            fileCreator.createNewFile();
        }
    }

    public static void deleteGitDirectory() throws IOException {
        File indexDeleter = new File("git/INDEX");
        indexDeleter.delete();
        File headDeleter = new File("git/HEAD");
        headDeleter.delete();
        File objectsDeleter = new File("git/objects");
        objectsDeleter.delete();
        File gitDeleter = new File("git");
        gitDeleter.delete();
    }
}
