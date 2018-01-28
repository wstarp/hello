package com.cennavi.server;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.cennavi.server.db.DBConnectionFactory;
import com.cennavi.server.model.TableBean;
import com.cennavi.server.util.FreemarkerUtils;

public class Run_cmppos {
	public static final String sp = File.separator;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {

		new ClassPathXmlApplicationContext("classpath:beans.xml");// 初始化spring配置

		String dabase = "cmp_pos";// 数据库名称
		String table = "USERS";// 表名称
		List<TableBean> tableList = DBConnectionFactory.getTableBean_mysql(dabase, table);// 查询表结构

		String codePath = "D:" + sp + "wanglei" + sp + "workspace" + sp + "cmp_pos" + sp;// 生产代码的目录，绝对路径
		String foder = "report";// 为业务单独生成一个目录,请小写
		String modleName = "Demo";// 业务名称,大写开头
		String modleCCName = "报表查询DEMO";// 业务中文描述

		/** 准备模板数据 **/
		HashMap data = new HashMap();
		data.put("tableList", tableList);// 表结构数据
		data.put("foder", foder);// 为业务单独生成一个目录
		data.put("modleName", modleName);// 业务名称,大写开头
		data.put("modleCCName", modleCCName);// 业务中文描述
		data.put("author", "wanglei");// 作者信息
		data.put("table", table);// 表名称

		FreemarkerUtils.createFile("amazePage.jsp", data, codePath + "WebRoot" + sp + "WEB-INF" + sp + "views" + sp + foder + sp + modleName + ".jsp");
		FreemarkerUtils.createFile("Template.js", data, codePath + "WebRoot" + sp + "js" + sp + foder + sp + modleName + ".js");
		FreemarkerUtils.createFile("TemplateController.txt", data, codePath + "src" + sp + "net" + sp + "oicp" + sp + "controller" + sp + foder + sp + modleName + "Controller.java");
		FreemarkerUtils.createFile("TemplateService.txt", data, codePath + "src" + sp + "net" + sp + "oicp" + sp + "service" + sp + foder + sp + modleName + "Service.java");
		FreemarkerUtils.createFile("TemplateInfo.txt", data, codePath + "src" + sp + "net" + sp + "oicp" + sp + "model" + sp + foder + sp + modleName + "Info.java");
		FreemarkerUtils.createFile("TemplateMapper.txt", data, codePath + "src" + sp + "net" + sp + "oicp" + sp + "dao" + sp + foder + sp + modleName + "Mapper.java");
		FreemarkerUtils.createFile("TemplateMapper.xml", data, codePath + "src" + sp + "net" + sp + "oicp" + sp + "mapping" + sp + foder + sp + modleName + "Mapper.xml");
	}
}
