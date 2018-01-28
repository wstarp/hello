package com.cennavi.server.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerUtils {

	private static Configuration cfg = null;

	/**
	 * 获取freemarker的配置 freemarker本身支持classpath,目录和从ServletContext获取.
	 * 
	 * @return 返回Configuration对象
	 * @throws IOException
	 */
	private static Configuration getConfiguration() throws IOException {
		if (null == cfg) {
			cfg = new Configuration();
			// cfg.setDirectoryForTemplateLoading(new File(absPath));
			cfg.setClassForTemplateLoading(FreemarkerUtils.class, "/templates");
			// setEncoding这个方法一定要设置国家及其编码，不然在flt中的中文在生成html后会变成乱码
			cfg.setEncoding(Locale.getDefault(), "UTF-8");
			// 设置对象的包装器
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			// 设置异常处理器//这样的话就可以${a.b.c.d}即使没有属性也不会出错
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		}
		return cfg;
	}

	/**
	 * 
	 * @param ftl
	 *            模板文件名,相对上面的模版根目录templates路径,例如/module/view.ftl
	 *            templates/module/view.ftl
	 * @param data
	 *            填充数据
	 * @param targetFile
	 *            要生成的静态文件的路径,相对设置中的根路径,例如 "jsp/user/1.html"
	 * @return
	 */
	public static boolean createFile(String ftl, Map<String, Object> data, String targetFile) {
		try {
			File _folder = new File(new File(targetFile).getParent());
			if (!_folder.exists()) {
				_folder.mkdirs();
			}
			// 创建Template对象
			Configuration cfg = FreemarkerUtils.getConfiguration();
			Template template = cfg.getTemplate(ftl);
			template.setEncoding("UTF-8");
			// 生成静态页面
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));
			template.process(data, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (TemplateException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("生产" + targetFile + "成功！");
		return true;
	}
}