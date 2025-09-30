import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GitHash {

    public static void gitRepoInit() throws IOException {
        GitHash.createDirectoryIfMissing("git");
        GitHash.createDirectoryIfMissing("git/objects");
        GitHash.createFileIfMissing("git/HEAD");
        GitHash.createFileIfMissing("git/INDEX");
        System.out.println("Git Repository Created");
    }

    public static void createDirectoryIfMissing(String path) throws IOException {
        File directoryCreator = new File(path);
        if (!directoryCreator.exists()) {
            directoryCreator.mkdir();
        } else {
            System.out.println(path + " Already Exists");
        }
    }

    public static void createFileIfMissing(String path) throws IOException {
        File fileCreator = new File(path);
        if (!fileCreator.exists()) {
            fileCreator.createNewFile();
        } else {
            System.out.println(path + " Already Exists");
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
        System.out.println("Git Repository Deleted");
    }

    public static String generateSHA1Hash(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(file.toPath());
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(bytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createBLOB(File file) throws IOException {
        String hash = generateSHA1Hash(file);
        File blob = new File("git/objects/" + hash);
        blob.createNewFile();
        FileOutputStream blobWriter = new FileOutputStream(blob);
        BufferedReader fileCopier = new BufferedReader(new FileReader(file));
        while (fileCopier.ready()) {
            blobWriter.write(fileCopier.read());
        }
        fileCopier.close();
        blobWriter.close();
    }

    public static void deleteBLOB(File file) throws IOException {
        String hash = generateSHA1Hash(file);
        File blobDeleter = new File("git/objects/" + hash);
        blobDeleter.delete();
    }

    public static void blobExists(File file) throws IOException{
        String hash = generateSHA1Hash(file);
        File blobChecker = new File("git/objects/" + hash);
        if (blobChecker.exists()) {
            System.out.println("BLOB " + hash + " of File " + file.getName() + " is in objects directory");
        } else {
            System.out.println("BLOB " + hash + " of File " + file.getName() + " does not exist or is not in objects directory");
        }
    }
}
