package com.yoxi.jgframework.common.dao.impl;

/**
 * 公共扩展方法
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yoxi.jgframework.common.dao.ICommonDao;
import com.yoxi.jgframework.common.dao.IGenericBaseCommonDao;
import com.yoxi.jgframework.common.model.common.UploadFile;
import com.yoxi.jgframework.common.model.json.ComboTree;
import com.yoxi.jgframework.common.model.json.ComparatorTreeGrid;
import com.yoxi.jgframework.common.model.json.ImportFile;
import com.yoxi.jgframework.common.model.json.TreeGrid;
import com.yoxi.jgframework.extend.swftools.SwfToolsUtil;
import com.yoxi.jgframework.extend.template.DataSourceMap;
import com.yoxi.jgframework.extend.template.Template;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.system.entity.TSRoleUser;
import com.yoxi.jgframework.system.entity.TSUser;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.ui.tag.vo.easyui.ComboTreeModel;
import com.yoxi.jgframework.ui.tag.vo.easyui.TreeGridModel;
import com.yoxi.jgframework.util.DateUtils;
import com.yoxi.jgframework.util.FileUtils;
import com.yoxi.jgframework.util.GenericsUtils;
import com.yoxi.jgframework.util.PasswordUtil;
import com.yoxi.jgframework.util.ReflectHelper;
import com.yoxi.jgframework.util.ResourceUtil;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public class CommonDao extends GenericBaseCommonDao implements ICommonDao, IGenericBaseCommonDao {
	private static final Logger LOGGER = Logger.getLogger(CommonDao.class);

	/**
	 * 检查用户是否存在
	 * */
	public TSUser getUserByUserIdAndUserNameExits(TSUser user) {
		String password = PasswordUtil.encrypt(user.getUserName(), user.getPassword(), PasswordUtil.getStaticSalt());
		List<TSUser> users = findByQueryString("from TSUser u where u.userName = '" + user.getUserName()
				+ "' and u.password='" + password + "'");
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	/**
	 * admin账户初始化
	 */
	public void pwdInit(TSUser user, String newPwd) {
		List<TSUser> users = findByQueryString("from TSUser u where u.userName = '" + user.getUserName() + "' ");
		if (null != users && users.size() > 0) {
			user = users.get(0);
			String pwd = PasswordUtil.encrypt(user.getUserName(), newPwd, PasswordUtil.getStaticSalt());
			user.setPassword(pwd);
			save(user);
		}

	}

	public String getUserRole(TSUser user) {
		String userRole = "";
		List<TSRoleUser> sRoleUser = findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		for (TSRoleUser tsRoleUser : sRoleUser) {
			userRole += tsRoleUser.getTSRole().getRoleCode() + ",";
		}
		return userRole;
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @throws Exception
	 */
	public Object uploadFile(UploadFile uploadFile) {
		Object object = uploadFile.getObject();
		if (uploadFile.getFileKey() != null) {
			updateEntitie(object);
		} else {
			try {
				uploadFile.getMultipartRequest().setCharacterEncoding("UTF-8");
				MultipartHttpServletRequest multipartRequest = uploadFile.getMultipartRequest();
				ReflectHelper reflectHelper = new ReflectHelper(uploadFile.getObject());
				String uploadbasepath = uploadFile.getBasePath();// 文件上传根目录
				if (uploadbasepath == null) {
					uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
				}
				Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
				// 文件数据库保存路径
				String path = uploadbasepath + "/";// 文件保存在硬盘的相对路径
				String realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/")
						+ path;// 文件的硬盘真实路径
				File file = new File(realPath);
				if (!file.exists()) {
					file.mkdir();// 创建根目录
				}
				if (uploadFile.getCusPath() != null) {
					realPath += uploadFile.getCusPath() + "/";
					path += uploadFile.getCusPath() + "/";
					file = new File(realPath);
					if (!file.exists()) {
						file.mkdir();// 创建文件自定义子目录
					}
				} else {
					realPath += DateUtils.getDataString(DateUtils.yyyyMMdd) + "/";
					path += DateUtils.getDataString(DateUtils.yyyyMMdd) + "/";
					file = new File(realPath);
					if (!file.exists()) {
						file.mkdir();// 创建文件时间子目录
					}
				}
				String entityName = uploadFile.getObject().getClass().getSimpleName();
				// 设置文件上传路径
				if (entityName.equals("TSTemplate")) {
					realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/")
							+ ResourceUtil.getConfigByName("templatepath") + "/";
					path = ResourceUtil.getConfigByName("templatepath") + "/";
				} else if (entityName.equals("TSIcon")) {
					realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/")
							+ uploadFile.getCusPath() + "/";
					path = uploadFile.getCusPath() + "/";
				}
				String fileName = "";
				// String swfName = "";
				for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
					MultipartFile mf = entity.getValue();// 获取上传文件对象
					fileName = mf.getOriginalFilename();// 获取文件名
					// swfName = PinyinUtil
					// .getPinYinHeadChar(oConvertUtils.replaceBlank(FileUtils.getFilePrefix(fileName)));//
					// 取文件名首字母作为SWF文件名
					String extend = FileUtils.getExtend(fileName);// 获取文件扩展名
					String myfilename = "";
					String noextfilename = "";// 不带扩展名
					if (uploadFile.isRename()) {

						noextfilename = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8);// 自定义文件名称
						myfilename = noextfilename + "." + extend;// 自定义文件名称
					} else {
						myfilename = fileName;
					}

					String savePath = realPath + myfilename;// 文件保存全路径
					String fileprefixName = FileUtils.getFilePrefix(fileName);
					if (uploadFile.getTitleField() != null) {
						reflectHelper.setMethodValue(uploadFile.getTitleField(), fileprefixName);// 动态调用set方法给文件对象标题赋值
					}
					if (uploadFile.getExtend() != null) {
						// 动态调用 set方法给文件对象内容赋值
						reflectHelper.setMethodValue(uploadFile.getExtend(), extend);
					}
					if (uploadFile.getByteField() != null) {
						// 二进制文件保存在数据库中
						reflectHelper.setMethodValue(uploadFile.getByteField(),
								IOUtils.toByteArray(mf.getInputStream()));
						saveOrUpdate(object);
					}
					File savefile = new File(savePath);
					if (uploadFile.getRealPath() != null) {
						// 设置文件数据库的物理路径
						reflectHelper.setMethodValue(uploadFile.getRealPath(), path + myfilename);
					}
					// 文件拷贝到指定硬盘目录
					FileCopyUtils.copy(mf.getBytes(), savefile);
					if (uploadFile.getSwfpath() != null) {
						// 转SWF 改swf文件的存储路径
						// reflectHelper.setMethodValue(uploadFile.getSwfpath(),
						// path + swfName + ".swf");
						reflectHelper.setMethodValue(uploadFile.getSwfpath(),
								path + FileUtils.getFilePrefix(myfilename) + ".swf");
						SwfToolsUtil.convert2SWF(savePath);
					}
				}
			} catch (Exception e1) {
			}
		}
		return object;
	}

	public <T> List<T> queryList(String hql, Map<String, Object> paramsMap, Integer page, Integer pageSize) {
		Query query = getSession().createQuery(hql);
		if (MapUtils.isNotEmpty(paramsMap)) {
			query.setProperties(paramsMap);
		}
		Integer start = (page - 1) * pageSize;
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		List<T> list = query.list();
		LOGGER.info("queryList  === start=" + start + " , pageSize=" + pageSize + "  listSize=" + list.size());
		return list;
	}

	public Long queryListCount(String hql, Map<String, Object> paramsMap) {
		Query query = getSession().createQuery(hql);
		if (MapUtils.isNotEmpty(paramsMap)) {
			query.setProperties(paramsMap);
		}
		return (Long) query.setMaxResults(1).uniqueResult();
	}

	/**
	 * 文件下载或预览
	 * 
	 * @param request
	 * @throws Exception
	 * @throws Exception
	 */
	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile) {
		uploadFile.getResponse().setContentType("UTF-8");
		uploadFile.getResponse().setCharacterEncoding("UTF-8");
		InputStream bis = null;
		BufferedOutputStream bos = null;
		HttpServletResponse response = uploadFile.getResponse();
		HttpServletRequest request = uploadFile.getRequest();
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		String downLoadPath = "";
		long fileLength = 0;
		if (uploadFile.getRealPath() != null && uploadFile.getContent() == null) {
			downLoadPath = ctxPath + uploadFile.getRealPath();
			fileLength = new File(downLoadPath).length();
			try {
				bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			if (uploadFile.getContent() != null)
				bis = new ByteArrayInputStream(uploadFile.getContent());
			fileLength = uploadFile.getContent().length;
		}
		try {
			if (!uploadFile.isView() && uploadFile.getExtend() != null) {
				if (uploadFile.getExtend().equals("text")) {
					response.setContentType("text/plain;");
				} else if (uploadFile.getExtend().equals("doc")) {
					response.setContentType("application/msword;");
				} else if (uploadFile.getExtend().equals("xls")) {
					response.setContentType("application/ms-excel;");
				} else if (uploadFile.getExtend().equals("pdf")) {
					response.setContentType("application/pdf;");
				} else if (uploadFile.getExtend().equals("jpg") || uploadFile.getExtend().equals("jpeg")) {
					response.setContentType("image/jpeg;");
				} else {
					response.setContentType("application/x-msdownload;");
				}
				response.setHeader(
						"Content-disposition",
						"attachment; filename="
								+ new String((uploadFile.getTitleField() + "." + uploadFile.getExtend())
										.getBytes("GBK"), "ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(fileLength));
			}
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	public Map<Object, Object> getDataSourceMap(Template template) {
		return DataSourceMap.getDataSourceMap();
	}

	/**
	 * 生成XML importFile 导出xml工具类
	 */
	public HttpServletResponse createXml(ImportFile importFile) {
		HttpServletResponse response = importFile.getResponse();
		HttpServletRequest request = importFile.getRequest();
		try {
			// 创建document对象
			Document document = DocumentHelper.createDocument();
			document.setXMLEncoding("UTF-8");
			// 创建根节点
			String rootname = importFile.getEntityName() + "s";
			Element rElement = document.addElement(rootname);
			Class entityClass = importFile.getEntityClass();
			String[] fields = importFile.getField().split(",");
			// 得到导出对象的集合
			List objList = loadAll(entityClass);
			// Class classType = entityClass.getClass();
			for (Object t : objList) {
				Element childElement = rElement.addElement(importFile.getEntityName());
				for (int i = 0; i < fields.length; i++) {
					String fieldName = fields[i];
					// 第一为实体的主键
					if (i == 0) {
						childElement.addAttribute(fieldName, String.valueOf(TagUtil.fieldNametoValues(fieldName, t)));
					} else {
						Element name = childElement.addElement(fieldName);
						name.setText(String.valueOf(TagUtil.fieldNametoValues(fieldName, t)));
					}
				}

			}
			String ctxPath = request.getSession().getServletContext().getRealPath("");
			File fileWriter = new File(ctxPath + "/" + importFile.getFileName());
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileWriter));

			xmlWriter.write(document);
			xmlWriter.close();
			// 下载生成的XML文件
			UploadFile uploadFile = new UploadFile(request, response);
			uploadFile.setRealPath(importFile.getFileName());
			uploadFile.setTitleField(importFile.getFileName());
			uploadFile.setExtend("bak");
			viewOrDownloadFile(uploadFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 解析XML文件将数据导入数据库中
	 */
	public void parserXml(String fileName) {
		try {
			File inputXml = new File(fileName);
			Class entityClass;
			// 读取文件
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputXml);
			Element employees = document.getRootElement();
			// 遍历根节点下的子节点
			for (Iterator i = employees.elementIterator(); i.hasNext();) {
				Element employee = (Element) i.next();
				// 有实体名反射得到实体类
				entityClass = GenericsUtils.getEntityClass(employee.getName());
				// 得到实体属性
				Field[] fields = TagUtil.getFiled(entityClass);
				// 得到实体的ID
				String id = employee.attributeValue(fields[0].getName());
				// 判断实体是否已存在
				Object obj1 = getEntity(entityClass, id);
				// 实体不存在new个实体
				if (obj1 == null) {
					obj1 = entityClass.newInstance();
				}
				// 根据反射给实体属性赋值
				for (Iterator j = employee.elementIterator(); j.hasNext();) {
					Element node = (Element) j.next();
					for (int k = 0; k < fields.length; k++) {
						if (node.getName().equals(fields[k].getName())) {
							String fieldName = fields[k].getName();
							String stringLetter = fieldName.substring(0, 1).toUpperCase();
							String setName = "set" + stringLetter + fieldName.substring(1);
							Method setMethod = entityClass.getMethod(setName, new Class[] { fields[k].getType() });
							String type = TagUtil.getColumnType(fieldName, fields);
							if (type.equals("int")) {
								setMethod.invoke(obj1, new Integer(node.getText()));
							} else if (type.equals("string")) {
								setMethod.invoke(obj1, node.getText().toString());
							} else if (type.equals("short")) {
								setMethod.invoke(obj1, new Short(node.getText()));
							} else if (type.equals("double")) {
								setMethod.invoke(obj1, new Double(node.getText()));
							} else if (type.equals("Timestamp")) {
								setMethod.invoke(obj1,
										new Timestamp(DateUtils.str2Date(node.getText(), DateUtils.datetimeFormat)
												.getTime()));
							}
						}
					}
				}
				if (obj1 != null) {
					saveOrUpdate(obj1);
				} else {
					save(obj1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据模型生成JSON
	 * 
	 * @param all
	 *            全部对象
	 * @param in
	 *            已拥有的对象
	 * @param comboBox
	 *            模型
	 * @return
	 */
	public List<ComboTree> comTree(List<TSDepart> all, ComboTree comboTree) {
		List<ComboTree> trees = new ArrayList<ComboTree>();
		for (TSDepart depart : all) {
			// ----------------------------------------------------------------
			// add-end--Author:liutao Date:20130406 for：需要循环递归查询，否则，组合数在初始化转变不正常
			// ----------------------------------------------------------------
			trees.add(tree(depart, true));
			// ----------------------------------------------------------------
			// add-end--Author:liutao Date:20130406 for：需要循环递归查询，否则，组合数在初始化转变不正常
			// ----------------------------------------------------------------
		}
		return trees;

	}

	public ComboTree tree(TSDepart depart, boolean recursive) {
		ComboTree tree = new ComboTree();
		tree.setId(oConvertUtils.getString(depart.getId()));
		tree.setText(depart.getDepartName());
		List<TSDepart> departsList = findByProperty(TSDepart.class, "TSPDepart.id", depart.getId());
		if (departsList != null && departsList.size() > 0) {
			tree.setState("closed");
			tree.setChecked(false);
			if (recursive) {// 递归查询子节点
				List<TSDepart> departList = new ArrayList<TSDepart>(departsList);
				// Collections.sort(departList, new SetListSort());// 排序
				List<ComboTree> children = new ArrayList<ComboTree>();
				for (TSDepart d : departList) {
					ComboTree t = tree(d, true);
					children.add(t);
				}
				tree.setChildren(children);
			}
		}
		return tree;
	}

	/**
	 * 根据模型生成ComboTree JSON
	 * 
	 * @param all全部对象
	 * @param in已拥有的对象
	 * @param comboTreeModel模型
	 * @return
	 */
	public List<ComboTree> ComboTree(List all, ComboTreeModel comboTreeModel, List in) {
		List<ComboTree> trees = new ArrayList<ComboTree>();
		for (Object obj : all) {
			trees.add(comboTree(obj, comboTreeModel, in, false));
		}
		return trees;

	}

	// 构建ComboTree
	private ComboTree comboTree(Object obj, ComboTreeModel comboTreeModel, List in, boolean recursive) {
		ComboTree tree = new ComboTree();
		Map<String, Object> attributes = new HashMap<String, Object>();
		ReflectHelper reflectHelper = new ReflectHelper(obj);
		String id = oConvertUtils.getString(reflectHelper.getMethodValue(comboTreeModel.getIdField()));
		tree.setId(id);
		tree.setText(oConvertUtils.getString(reflectHelper.getMethodValue(comboTreeModel.getTextField())));
		if (comboTreeModel.getSrcField() != null) {
			attributes.put("href", oConvertUtils.getString(reflectHelper.getMethodValue(comboTreeModel.getSrcField())));
			tree.setAttributes(attributes);
		}
		if (in == null) {
		} else {
			if (in.size() > 0) {
				for (Object inobj : in) {
					ReflectHelper reflectHelper2 = new ReflectHelper(inobj);
					String inId = oConvertUtils.getString(reflectHelper2.getMethodValue(comboTreeModel.getIdField()));
					if (inId.equals(id)) {
						tree.setChecked(true);
					}
				}
			}
		}
		List tsFunctions = (List) reflectHelper.getMethodValue(comboTreeModel.getChildField());
		if (tsFunctions != null && tsFunctions.size() > 0) {
			tree.setState("closed");
			tree.setChecked(false);
			/*
			 * if (recursive) {// 递归查询子节点 List<TSFunction> functionList = new
			 * ArrayList<TSFunction>(tsFunctions);
			 * Collections.sort(functionList, new SetListSort());// 排序
			 * List<ComboTree> children = new ArrayList<ComboTree>(); for
			 * (TSFunction f : functionList) { ComboTree t =
			 * comboTree(f,comboTreeModel,in, true); children.add(t); }
			 * tree.setChildren(children); }
			 */
		}
		return tree;
	}

	/**
	 * 构建树形数据表
	 */
	public List<TreeGrid> treegrid(List all, TreeGridModel treeGridModel) {
		List<TreeGrid> treegrid = new ArrayList<TreeGrid>();
		for (Object obj : all) {
			ReflectHelper reflectHelper = new ReflectHelper(obj);
			TreeGrid tg = new TreeGrid();
			String id = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getIdField()));
			String src = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getSrc()));
			String text = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getTextField()));

			// ----------------------------------------------------------------
			// update-end--Author:wangyang Date:20130402 for：添加排序
			// ----------------------------------------------------------------
			if (StringUtils.isNotEmpty(treeGridModel.getOrder())) {
				String order = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getOrder()));
				order = order == null ? "0" : order;
				order = order.length() == 0 ? "0" : order;
				tg.setOrder(oConvertUtils.getInt(order));
			}
			// ----------------------------------------------------------------
			// update-end--Author:wangyang Date:20130402 for：添加排序
			// ----------------------------------------------------------------
			tg.setId(id);
			if (treeGridModel.getIcon() != null) {
				String iconpath = TagUtil.fieldNametoValues(treeGridModel.getIcon(), obj).toString();
				if (iconpath != null) {
					tg.setIcon(iconpath);
				} else {
					tg.setIcon("");
				}
			}
			tg.setSrc(src);
			tg.setText(text);
			if (treeGridModel.getCode() != null) {
				String code = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getCode()));
				tg.setCode(code);
			}
			if (treeGridModel.getParentId() != null) {
				Object pid = TagUtil.fieldNametoValues(treeGridModel.getParentId(), obj);
				if (pid != null) {
					tg.setParentId(pid.toString());
				} else {
					tg.setParentId("");
				}
			}
			if (treeGridModel.getParentText() != null) {
				Object ptext = TagUtil.fieldNametoValues(treeGridModel.getTextField(), obj);
				if (ptext != null) {
					tg.setParentText(ptext.toString());
				} else {
					tg.setParentText("");
				}

			}
			List childList = (List) reflectHelper.getMethodValue(treeGridModel.getChildList());

			if (childList != null && childList.size() > 0) {
				tg.setState("closed");
			}

			// if (treeGridModel.getRoleid() != null) {
			// String[] opStrings = {};
			// List<TSRoleFunction> roleFunctions =
			// findByProperty(TSRoleFunction.class, "TSFunction.id", id);
			//
			// if (roleFunctions.size() > 0) {
			// for (TSRoleFunction tRoleFunction : roleFunctions) {
			// TSRoleFunction roleFunction = tRoleFunction;
			// if
			// (roleFunction.getTSRole().getId().toString().equals(treeGridModel.getRoleid()))
			// {
			// String bbString = roleFunction.getOperation();
			// if (bbString != null) {
			// opStrings = bbString.split(",");
			// break;
			// }
			// }
			// }
			// }
			// List<TSOperation> operateions = findByProperty(TSOperation.class,
			// "TSFunction.id", id);
			// StringBuffer attributes = new StringBuffer();
			// if (operateions.size() > 0) {
			// for (TSOperation tOperation : operateions) {
			// if (opStrings.length < 1) {
			// attributes.append("<input type=checkbox name=operatons value=" +
			// tOperation.getId() + "_" + id + ">" +
			// tOperation.getOperationName());
			// } else {
			// StringBuffer sb = new StringBuffer();
			// sb.append("<input type=checkbox name=operatons");
			// for (int i = 0; i < opStrings.length; i++) {
			// if (opStrings[i].equals(tOperation.getId().toString())) {
			// sb.append(" checked=checked");
			// }
			// }
			// sb.append(" value=" + tOperation.getId() + "_" + id + ">" +
			// tOperation.getOperationName());
			// attributes.append(sb.toString());
			// }
			// }
			// }
			// tg.setOperations(attributes.toString());
			// }

			treegrid.add(tg);
		}
		ComparatorTreeGrid comparator = new ComparatorTreeGrid();
		Collections.sort(treegrid, comparator);
		return treegrid;
	}

	@Override
	public <T> List<T> findListPageByHQL(String hql, Map<String, ?> paramsMap, Integer pageNo, Integer pageSize) {
		Session session = getSession();
		Query sqlQuery = session.createQuery(hql);
		sqlQuery.setProperties(paramsMap);
		sqlQuery.setFirstResult((pageNo - 1) * pageSize);
		sqlQuery.setMaxResults(pageSize);
		return sqlQuery.list();
	}

	@Override
	public <T> List<T> findListPageByHQL(String hql, Integer pageNo, Integer pageSize, Object... params) {
		Session session = getSession();
		Query sqlQuery = session.createQuery(hql);
		for (int i = 0, length = params.length; i < length; i++) {
			sqlQuery.setParameter(i, params[i]);
		}
		sqlQuery.setFirstResult((pageNo - 1) * pageSize);
		sqlQuery.setMaxResults(pageSize);
		return sqlQuery.list();
	}

	@Override
	public Integer updateBySql(String sql, Map<String, Object> paramsMap) {
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setProperties(paramsMap);
		return sqlQuery.executeUpdate();
	}

	@Override
	public Integer updateBySql(String sql, Object... objects) {
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		if (ArrayUtils.isNotEmpty(objects)) {
			for (int i = 0, length = objects.length; i < length; i++) {
				sqlQuery.setParameter(i, objects[i]);
			}
		}
		return sqlQuery.executeUpdate();
	}

	@Override
	public <T> List<T> findListBySQL(String sql, Class<T> clazz, Map<String, ?> paramsMap) {
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setProperties(paramsMap);
		sqlQuery.addEntity(clazz);
		return sqlQuery.list();
	}

	@Override
	public <T> List<T> findListBySQL(String sql, Class<T> clazz, Object... params) {
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		for (int i = 0, length = params.length; i < length; i++) {
			sqlQuery.setParameter(i, params[i]);
		}
		sqlQuery.addEntity(clazz);
		return sqlQuery.list();
	}

	@Override
	public <T> List<T> findListPageBySQL(String sql, Class<T> clazz, Map<String, ?> paramsMap, Integer pageNo,
			Integer pageSize) {
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setProperties(paramsMap);
		sqlQuery.addEntity(clazz);
		sqlQuery.setFirstResult((pageNo - 1) * pageSize);
		sqlQuery.setMaxResults(pageSize);
		return sqlQuery.list();
	}

	@Override
	public <T> List<T> findListPageBySQL(String sql, Class<T> clazz, Integer pageNo, Integer pageSize, Object... params) {
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		for (int i = 0, length = params.length; i < length; i++) {
			sqlQuery.setParameter(i, params[i]);
		}
		sqlQuery.addEntity(clazz);
		sqlQuery.setFirstResult((pageNo - 1) * pageSize);
		sqlQuery.setMaxResults(pageSize);
		return sqlQuery.list();
	}

	@Override
	public <T> T findEntityBySQL(String sql, Class<T> clazz, Map<String, ?> paramsMap) {
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setProperties(paramsMap);
		sqlQuery.addEntity(clazz);
		return (T) sqlQuery.setMaxResults(1).uniqueResult();
	}

	public <T> T queryObjectBySQL(String sql, Map<String, Object> paramsMap, Class<T> clazz, Map<String, Type> scalarMap) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		if (MapUtils.isNotEmpty(paramsMap)) {
			sqlQuery.setProperties(paramsMap);
		}
		sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz));
		if (MapUtils.isNotEmpty(scalarMap)) {
			for (Entry<String, Type> entry : scalarMap.entrySet()) {
				if (entry.getValue() != null) {
					sqlQuery.addScalar(entry.getKey(), entry.getValue());
				} else {
					sqlQuery.addScalar(entry.getKey());
				}
			}
		}
		return (T) sqlQuery.uniqueResult();
	}

	public Map<String, Object> queryMapBySQL(String sql, Map<String, Object> paramsMap, Map<String, Type> scalarMap) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		if (MapUtils.isNotEmpty(paramsMap)) {
			sqlQuery.setProperties(paramsMap);
		}
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (MapUtils.isNotEmpty(scalarMap)) {
			for (Entry<String, Type> entry : scalarMap.entrySet()) {
				if (entry.getValue() != null) {
					sqlQuery.addScalar(entry.getKey(), entry.getValue());
				} else {
					sqlQuery.addScalar(entry.getKey());
				}
			}
		}
		return (Map<String, Object>) sqlQuery.setMaxResults(1).uniqueResult();
	}

	public <T> List<T> queryListObjectBySQL(String sql, Map<String, Object> paramsMap, Class<T> clazz,
			Map<String, Type> scalarMap, Integer pageNo, Integer pageSize) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		if (MapUtils.isNotEmpty(paramsMap)) {
			sqlQuery.setProperties(paramsMap);
		}
		sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz));
		if (MapUtils.isNotEmpty(scalarMap)) {
			for (Entry<String, Type> entry : scalarMap.entrySet()) {
				if (entry.getValue() != null) {
					sqlQuery.addScalar(entry.getKey(), entry.getValue());
				} else {
					sqlQuery.addScalar(entry.getKey());
				}
			}
		}
		sqlQuery.setFirstResult((pageNo - 1) * pageSize);
		sqlQuery.setMaxResults(pageSize);
		return (List<T>) sqlQuery.list();
	}

	public <T> List<T> queryListObjectBySQL(String sql, Map<String, Object> paramsMap, Class<T> clazz,
			Map<String, Type> scalarMap) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		if (MapUtils.isNotEmpty(paramsMap)) {
			sqlQuery.setProperties(paramsMap);
		}
		sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz));
		if (MapUtils.isNotEmpty(scalarMap)) {
			for (Entry<String, Type> entry : scalarMap.entrySet()) {
				if (entry.getValue() != null) {
					sqlQuery.addScalar(entry.getKey(), entry.getValue());
				} else {
					sqlQuery.addScalar(entry.getKey());
				}
			}
		}
		return (List<T>) sqlQuery.list();
	}

	public List<Map<String, Object>> queryListMapBySQL(String sql, Map<String, Object> paramsMap,
			Map<String, Type> scalarMap) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		if (MapUtils.isNotEmpty(paramsMap)) {
			sqlQuery.setProperties(paramsMap);
		}
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (MapUtils.isNotEmpty(scalarMap)) {
			for (Entry<String, Type> entry : scalarMap.entrySet()) {
				if (entry.getValue() != null) {
					sqlQuery.addScalar(entry.getKey(), entry.getValue());
				} else {
					sqlQuery.addScalar(entry.getKey());
				}
			}
		}
		return (List<Map<String, Object>>) sqlQuery.list();
	}

	@Override
	public <T> T findEntityBySQL(String sql, Class<T> clazz, Object... params) {
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		for (int i = 0, length = params.length; i < length; i++) {
			sqlQuery.setParameter(i, params[i]);
		}
		sqlQuery.addEntity(clazz);
		return (T) sqlQuery.setMaxResults(1).uniqueResult();
	}

	@Override
	public <T> T load(Class<T> clazz, Serializable id) {
		return (T) getSession().load(clazz, id);
	}

	public Long findListPageCountBySQL(String sql, Map<String, ?> paramsMap) {
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		if (MapUtils.isNotEmpty(paramsMap)) {
			sqlQuery.setProperties(paramsMap);
		}
		return ((BigInteger) sqlQuery.setMaxResults(1).uniqueResult()).longValue();
	}
}
