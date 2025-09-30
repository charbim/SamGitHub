import java.io.File;
import java.io.IOException;

public class GitHashTester {

    static File testFile = new File("testFile.txt");

    public static void main(String[] args) throws IOException {
        gitRepoInitTester();
        System.out.println();
        GitHash.gitRepoInit();
        System.out.println();
        System.out.println(
                "From SHA1 website should output:\n688eb17c23409c9f2d853b63475d5614d90293cf\nActually outputs:\n"
                        + GitHash.generateSHA1Hash(new File("testFile.txt")));
        System.out.println();
        doBLOBMethodsWork();

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
        } else {
            System.out.println("Some Files Missing");
        }
    }

    public static void areAllFilesDeleted() {
        File gitFile = new File("git");
        File objectsFile = new File("git/objects");
        File headFile = new File("git/HEAD");
        File indexFile = new File("git/INDEX");
        if (!(gitFile.exists() || objectsFile.exists() || headFile.exists() || indexFile.exists())) {
            System.out.println("All Files Are Deleted");
        } else {
            System.out.println("Some Files Still Exist");
        }
    }

    public static void doBLOBMethodsWork() throws IOException {
        GitHash.blobExists(testFile);
        GitHash.createBLOB(testFile);
        GitHash.blobExists(testFile);
        GitHash.deleteBLOB(testFile);
        GitHash.blobExists(testFile);
    }
}
