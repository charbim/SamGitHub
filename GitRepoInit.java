import java.io.File;
import java.io.IOException;

public class GitRepoInit {

    public static void createDirectoryIfMissing (String path) throws IOException {
        File creator = new File(path);
        if (!creator.exists()) {
            creator.mkdir();
        }
    }

    public static void createFileIfMissing (String path) throws IOException {
        File creator = new File(path);
        if (!creator.exists()) {
            creator.createNewFile();
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

    public static void main(String[] args) {
        try {
            GitRepoInit.createDirectoryIfMissing("git");
            GitRepoInit.createDirectoryIfMissing("git/objects");
            GitRepoInit.createFileIfMissing("git/HEAD");
            GitRepoInit.createFileIfMissing("git/INDEX");
            GitRepoInit.deleteGitDirectory();
        } catch (IOException e){
            e.printStackTrace();
        }
        
    }
}
