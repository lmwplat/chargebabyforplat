package com.charge.config.utils;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.charge.config.state.AppConstants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.alibaba.fastjson.JSON;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// String repositoryPath;
	//private String uploadPath;//磁盘绝对路径
	//private String savePath;//url路径
	private String saveName = AppConstants.APK_NAME;//保存名称


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Integer chunk = null;// 分割块数
		Integer chunks = null;// 总分割数
		String tempFileName = null;// 临时文件名
		String newFileName = null;// 最后合并后的新文件名
		BufferedOutputStream outputStream = null;
		
		String pathParam = request.getParameter("path");

		String dir = AppConstants.DISK_BASE_DIR_APK;//pic

		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1024);
				// factory.setRepository(new File(repositoryPath));// 设置临时目录
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				// upload.setSizeMax(5 * 1024 * 1024);// 设置附件最大大小，超过这个大小上传会不成功
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) {// 是文本域 //1name  null
						if (item.getFieldName().equals("name")) {
							tempFileName = item.getString();//p1a9hdhgrm10ftvag6k81jt21sg63.apk
							// System.out.println("临时文件名：" + tempFileName);
						} else if (item.getFieldName().equals("chunk")) {
							chunk = Integer.parseInt(item.getString());
							// System.out.println("当前文件块：" + (chunk + 1));
						} else if (item.getFieldName().equals("chunks")) {
							chunks = Integer.parseInt(item.getString());
							// System.out.println("文件总分块：" + chunks);
						}
					} else {// 如果是文件类型 //2file  app-release.apk
						if (tempFileName != null) {
							String chunkName = tempFileName;//p1a9hdhgrm10ftvag6k81jt21sg63.apk
							if (chunk != null) {
								chunkName = chunk + "_" + tempFileName;
							}
							//saveName = chunkName;//p1a9hdhgrm10ftvag6k81jt21sg63.apk
							//File savedFile = new File(uploadPath, chunkName);
							//Iitem.getInputStream();
							/***********************kashi******************************/
							//String filename = null;
				    		//assertAllowed(file, allowedExtension, maxSize);//524
							String host = ConfigUtils.get(AppConstants.FTP_HOST);
							Integer port =Integer.parseInt(ConfigUtils.get(AppConstants.FTP_PORT));
							String username = ConfigUtils.get(AppConstants.FTP_UERNAME);
							String password = ConfigUtils.get(AppConstants.FTP_PASSWORD);
							FTPClient ftpClient = new FTPClient();
							InputStream inputStream = null;
							try {
								inputStream = new BufferedInputStream(item.getInputStream());
								ftpClient.connect(host, port);
								ftpClient.login(username, password);
								ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
								ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
								ftpClient.enterLocalPassiveMode();
								//System.out.println("ftpClient.getReplyCode()-------->"+ftpClient.getReplyCode());//200
								if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
									//String directory = StringUtils.substringBeforeLast(path, "/");
									//String filename = StringUtils.substringAfterLast(path, "/");
									//saveName = UUID.randomUUID().toString().replace("-", "") + extension;
									if (!ftpClient.changeWorkingDirectory(dir)) {//如果不存在则创建
										String[] paths = StringUtils.split(dir, "//");
										String p = "/";
										ftpClient.changeWorkingDirectory(p);
										for (String s : paths) {
											p += s + "/";
											if (!ftpClient.changeWorkingDirectory(p)) {
												ftpClient.makeDirectory(s);
												ftpClient.changeWorkingDirectory(p);
											}
										}
										
									}
									ftpClient.storeFile(saveName, inputStream);
									ftpClient.logout();
								}
							} catch (SocketException e) {
								throw new RuntimeException(e.getMessage(), e);
							} catch (IOException e) {
								throw new RuntimeException(e.getMessage(), e);
							} finally {
								IOUtils.closeQuietly(inputStream);
								try {
									if (ftpClient.isConnected()) {
										ftpClient.disconnect();
									}
								} catch (IOException e) {
								}
							}

							/*************************jieshu****************************/
							
							//item.write(savedFile);
						}
					}
				}

				if (chunk != null && chunk + 1 == chunks) {
					newFileName = UUID.randomUUID().toString().replace("-", "").concat(".").concat(FilenameUtils.getExtension(tempFileName));//3
					//saveName = newFileName;
					outputStream = new BufferedOutputStream(new FileOutputStream(new File(dir, newFileName)));
					// 遍历文件合并
					for (int i = 0; i < chunks; i++) {
						File tempFile = new File(dir, i + "_" + tempFileName);
						byte[] bytes = FileUtils.readFileToByteArray(tempFile);
						outputStream.write(bytes);
						outputStream.flush();
						tempFile.delete();
					}
					outputStream.flush();
				}
				System.out.println(saveName);
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("status", true);
				m.put("saveUrl", dir + "/" + saveName);
				response.getWriter().write(JSON.toJSONString(m));
			} catch (FileUploadException e) {
				e.printStackTrace();
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("status", false);
				response.getWriter().write(JSON.toJSONString(m));
			} catch (Exception e) {
				e.printStackTrace();
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("status", false);
				response.getWriter().write(JSON.toJSONString(m));
			} finally {
				try {
					if (outputStream != null)
						outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


}

