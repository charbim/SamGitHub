import java.io.File;
import java.io.IOException;

public class GitHashTester {

    public static void main(String[] args) throws IOException {
        // gitRepoInitTester();
        System.out.println(GitHash.generateSHA1Hash(new File("testFile.txt")));
    }

    public static void gitRepoInitTester() throws IOException {
        GitHash.deleteGitDirectory();
        areAllFilesDeleted();
        System.out.println();

        GitHash.gitRepoInit();
        areAllFilesInitialized();
        GitHash.deleteGitDirectory();
        areAllFilesDeleted();
        System.out.println();

        GitHash.createDirectoryIfMissing("git");
        GitHash.gitRepoInit();
        areAllFilesInitialized();
        GitHash.deleteGitDirectory();
        areAllFilesDeleted();
        System.out.println();

        GitHash.createDirectoryIfMissing("git");
        GitHash.createDirectoryIfMissing("git/objects");
        GitHash.gitRepoInit();
        areAllFilesInitialized();
        GitHash.deleteGitDirectory();
        areAllFilesDeleted();
        System.out.println();

        GitHash.gitRepoInit();
        GitHash.gitRepoInit();
        areAllFilesInitialized();
        GitHash.deleteGitDirectory();
        areAllFilesDeleted();
    }

    public static void areAllFilesInitialized() {
        File gitFile = new File("git");
        File objectsFile = new File("git/objects");
        File headFile = new File("git/HEAD");
        File indexFile = new File("git/INDEX");
        if (gitFile.exists() && objectsFile.exists() && headFile.exists() && indexFile.exists()) {
            System.out.println("All Files Are Initialized");
        }
    }

    public static void areAllFilesDeleted() {
        File gitFile = new File("git");
        File objectsFile = new File("git/objects");
        File headFile = new File("git/HEAD");
        File indexFile = new File("git/INDEX");
        if (!(gitFile.exists() || objectsFile.exists() || headFile.exists() || indexFile.exists())) {
            System.out.println("All Files Are Deleted");
        }
    }
}
