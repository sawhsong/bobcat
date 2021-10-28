package zebra.util;

import java.io.File;

import zebra.data.DataSet;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtil extends FileUtils {
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(FileUtil.class);

	public static File getFileByPath(String path) {
		File file = new File(path);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		return file;
	}

	public static void saveTo(File file, String path) throws Exception {
		File pathDir = getFileByPath(path);
		FileUtils.copyFileToDirectory(file, pathDir);
	}

	public static void moveFile(DataSet dsFile) throws Exception {
		for (int i=0; i<dsFile.getRowCnt(); i++) {
			String tempPath = dsFile.getValue(i, "TEMP_PATH")+"/"+dsFile.getValue(i, "NEW_NAME");
			String repositoryPath = dsFile.getValue(i, "REPOSITORY_PATH")+"/"+dsFile.getValue(i, "NEW_NAME");
			moveFile(new File(tempPath), new File(repositoryPath));
		}
	}

	public static void copyFile(DataSet dsFile, String path) throws Exception {
		for (int i=0; i<dsFile.getRowCnt(); i++) {
			String tempPath = dsFile.getValue(i, "TEMP_PATH")+"/"+dsFile.getValue(i, "NEW_NAME");
			copyFile(new File(tempPath), new File(path));
		}
	}

	public static void moveFile(DataSet dsFile, String path) throws Exception {
		for (int i=0; i<dsFile.getRowCnt(); i++) {
			String tempPath = dsFile.getValue(i, "TEMP_PATH")+"/"+dsFile.getValue(i, "NEW_NAME");
			moveFile(new File(tempPath), new File(path));
		}
	}

	public static void deleteFile(DataSet dsFile) throws Exception {
		for (int i=0; i<dsFile.getRowCnt(); i++) {
			String repositoryPath = dsFile.getValue(i, "REPOSITORY_PATH")+"/"+dsFile.getValue(i, "NEW_NAME");
			deleteQuietly(new File(repositoryPath));
		}
	}

	public static void deleteTempFile(DataSet dsFile) throws Exception {
		for (int i=0; i<dsFile.getRowCnt(); i++) {
			String repositoryPath = dsFile.getValue(i, "TEMP_PATH")+"/"+dsFile.getValue(i, "NEW_NAME");
			deleteQuietly(new File(repositoryPath));
		}
	}

	public static void deleteRepositoryFile(DataSet dsFile) throws Exception {
		deleteFile(dsFile);
	}

	public static void createFolder(String folderPath) throws Exception {
		File dir = new File(folderPath);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
	}

	public static boolean isEmptyDir(File file) throws Exception {
		if (file == null || !file.isDirectory()) {
			return true;
		} else {
			if (file.listFiles() == null || file.listFiles().length <= 0) {
				return true;
			} else {
				return false;
			}
		}
	}
}