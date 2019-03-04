package com.jd.seed.util.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * <p>
 * zip文件操作工具类，提供zip、unzip等特性
 * </p>
 * 
 * <p>
 * 该类来自于网上的整理：<br>
 * YouAreStupid 集合网上几个比较好的例子，使用org.apache.tools.zip包下的类解决zip压缩中的 中文文件名问题<br>
 * </p>
 * 
 * @author metanoia.lang 2012-5-24
 */
public class ZipFileUtils {
	/**
	 * 
	 * <p>
	 * 压缩目录或文件
	 * </p>
	 * 
	 * @param inputFile
	 *            File 压缩的目录或文件
	 * @param zipFileName
	 *            String 输出压缩文件
	 * @throws ZipFileException
	 */
	private static void doZip(File inputFile, String zipFileName)
			throws ZipFileException {
		ZipOutputStream out = null;
		try {
			File f = new File(zipFileName);
			File parentFile = f.getParentFile();
			if (parentFile != null) {
				if (!parentFile.mkdirs()) {
					throw new ZipFileException(
							"ZipFileUtil.doZip(inputFile,zipFileName) mkdirs error:"
									+ parentFile.getName());
				}
			}
			if (f.createNewFile()) {
				out = new ZipOutputStream(new FileOutputStream(f));
				doZip(out, inputFile, "");
			} else {
				throw new ZipFileException(
						"ZipFileUtil.doZip(inputFile,zipFileName) create file error:"
								+ f.getName());
			}

			if (out != null) {
				out.close();
			}
		} catch (FileNotFoundException ex) {
			throw new ZipFileException(ex);
		} catch (IOException e) {
			throw new ZipFileException(e);
		}
	}

	/**
	 * 
	 * <p>
	 * 压缩目录或文件
	 * </p>
	 * 
	 * @param filesDirPath
	 *            String 原目录 e.g "d:\\aaa"
	 * @param zipFilePath
	 *            String 输出zip文件 e.g "d:\\a\\b\\c\\aaa.zip"
	 * @throws ZipFileException
	 */
	public static void doZip(String filesDirPath, String zipFilePath)
			throws ZipFileException {
		doZip(new File(filesDirPath), zipFilePath);
	}

	private static void doZip(ZipOutputStream out, File f, String base)
			throws ZipFileException {
		try {
			if (f.isDirectory()) {
				File[] fl = f.listFiles();
				out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));
				base = base.length() == 0 ? "" : base + "/";
				for (int i = 0; i < fl.length; i++) {
					doZip(out, fl[i], base + fl[i].getName());
				}
			} else {
				out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
				FileInputStream in = new FileInputStream(f);
				int b;
				while ((b = in.read()) != -1) {
					out.write(b);
				}
				in.close();
			}
		} catch (IOException ex) {
			throw new ZipFileException(ex);
		}
	}

	/**
	 * 
	 * <p>
	 * 解压缩文件并输出到指定路径
	 * </p>
	 * 
	 * @param srcFile
	 *            String 压缩文件 e.g "d:\\a\\b\\c\\aaa.zip"
	 * @param dest
	 *            String 输出目标路径 e.g "d:\\a\\b\\c\\unzipaaa\\"
	 * @param deleteFile
	 *            boolean true-jvm停止后删除源文件 srcFile
	 * @throws ZipFileException
	 */
	public static void unZip(String srcFile, String dest, boolean deleteFile)
			throws ZipFileException {
		try {
			File file = new File(srcFile);
			if (!file.exists()) {
				throw new ZipFileException(
						"ZipFileUtil.unZip(srcFile,dest,deleteFile) zipFile not found:"
								+ file.getName());
			}
			ZipFile zipFile = new ZipFile(file);
			Enumeration<?> e = zipFile.getEntries();
			while (e.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) e.nextElement();
				if (zipEntry.isDirectory()) {
					String name = zipEntry.getName();
					name = name.substring(0, name.length() - 1);
					File f = new File(dest + name);
					if (!f.mkdirs()) {
						throw new ZipFileException(
								"ZipFileUtil.unZip(srcFile,dest,deleteFile) mkdirs error:"
										+ f.getName());
					}
				} else {
					File f = new File(dest + zipEntry.getName());
					File parentFile = f.getParentFile();
					if (parentFile != null) {
						if (!parentFile.mkdirs()) {
							throw new ZipFileException(
									"ZipFileUtil.unZip(srcFile,dest,deleteFile) mkdirs error:"
											+ parentFile.getName());
						}
					}

					if (f.createNewFile()) {
						InputStream is = zipFile.getInputStream(zipEntry);
						FileOutputStream fos = new FileOutputStream(f);
						int length = 0;
						byte[] b = new byte[4096];
						while ((length = is.read(b)) != -1) {
							fos.write(b, 0, length);
						}
						is.close();
						fos.close();
					} else {
						throw new ZipFileException(
								"ZipFileUtil.unZip(srcFile,dest,deleteFile) create file error:"
										+ f.getName());
					}
				}
			}

			if (zipFile != null) {
				zipFile.close();
			}

			if (deleteFile) {
				file.deleteOnExit();
			}
		} catch (IOException ex) {
			throw new ZipFileException(ex);
		}
	}

	// public static void main(String[] args) throws Exception {
	// // 压缩文件夹
	// boolean resultOfZip = ZipFileUtil
	// .doZip("d:\\aaa", "d:\\a\\b\\c\\啊.zip");
	//
	// // 解压缩
	// boolean resultOfUnZip = ZipFileUtil.unZip("d:\\a\\b\\c\\啊.zip",
	// "d:\\a\\b\\c\\unzipaaa\\", false);
	//
	// System.out.println("压缩结果：" + resultOfZip + "\n解压缩结果：" + resultOfUnZip);
	// }
}
