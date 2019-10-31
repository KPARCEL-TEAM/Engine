package com.kpe.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @description:
 * @author: LCN
 * @date: 2019-10-30 21:45
 */

@Slf4j
public class ClassUtil {

  /**
   * 查找的文件后缀
   */
  private final static String TARGET_SUFFIX = ".class";

  /**
   * .class 字符串的长度
   */
  private final static int TARGET_SUFFIX_LENGTH = TARGET_SUFFIX.length();

  /**
   * 通过包路径获取下面所以的Class对象
   * @param packageName 扫描的包路径
   * @param recursive 是否循环迭代扫描
   * @return
   */
  public static Set<Class> getClasses(String packageName, boolean recursive) {

    // 第一个class类的集合
    Set<Class> classes = new HashSet<>();
    // 获取包的名字 并进行替换
    String packageDirName = packageName.replace(".", File.separator);
    Enumeration<URL> dirs;
    try {
      dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
      // 循环迭代下去
      while (dirs.hasMoreElements()) {
        URL url = dirs.nextElement();
        // 得到协议的名称
        String protocol = url.getProtocol();
        if ("jar".equals(protocol)) {
          findAndAddClassesInPackageByJar(url, packageName, packageDirName, recursive, classes);
        }
        if ("file".equals(protocol)) {
          // 获取包的物理路径, 然后 以文件的方式扫描整个包下的文件
          String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
          findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
        }
      }
    } catch (IOException | ClassNotFoundException e) {
      log.error("scan class in the filePath encounter with exception ----->{}", e);
    }
    return classes;
  }

  /**
   * 通过包路径的 url 进行查找
   * @param url  包路径的 url
   * @param packageName 查找的包名
   * @param packageDirName 包名对应的路径
   * @param recursive 是否循环递归
   * @param classes 存放的Set集合
   * @return
   * @throws IOException
   * @throws ClassNotFoundException
   */
  private static Set<Class> findAndAddClassesInPackageByJar(URL url, String packageName, String packageDirName, boolean recursive, Set<Class> classes) throws IOException, ClassNotFoundException {

    JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
    Enumeration<JarEntry> entries = jar.entries();
    while (entries.hasMoreElements()) {
      // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
      JarEntry entry = entries.nextElement();
      String name = entry.getName();
      // 如果是以/开头的
      if (name.charAt(0) == '/') {
        // 获取后面的字符串
        name = name.substring(1);
      }
      // 如果前半部分和定义的包名相同
      if (name.startsWith(packageDirName)) {
        int idx = name.lastIndexOf('/');
        // 如果以"/"结尾 是一个包
        if (idx != -1) {
          // 获取包名 把"/"替换成"."
          packageName = name.substring(0, idx).replace('/', '.');
        }
        // 如果可以迭代下去 并且是一个包
        if ((idx != -1) || recursive) {
          // 如果是一个.class文件 而且不是目录
          if (name.endsWith(TARGET_SUFFIX) && !entry.isDirectory()) {
            // 去掉后面的".class" 获取真正的类名
            String className = name.substring(packageName.length() + 1, name.length() - TARGET_SUFFIX_LENGTH);
            classes.add(Class.forName(packageName + '.' + className));
          }
        }
      }
    }
    return classes;
  }

  /**
   * 以文件的形式来获取包下的所有Class
   *
   * @param packageName
   * @param packagePath
   * @param recursive
   */
  public static Set<Class> findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class> classes) throws ClassNotFoundException {

    // 获取此包的目录 建立一个File
    File dir = new File(packagePath);
    // 如果不存在或者 也不是目录就直接返回
    if (!dir.exists() || !dir.isDirectory()) {
      return classes;
    }
    // 如果存在 就获取包下的所有文件 包括目录
    File[] dirFiles = dir.listFiles(file -> {
      // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
      return (recursive && file.isDirectory()) || (file.getName().endsWith(TARGET_SUFFIX));
    });

    // 循环所有文件
    for (File file : dirFiles) {
      // 如果是目录 则继续扫描
      if (file.isDirectory()) {
        findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
      } else {
        // 如果是java类文件 去掉后面的.class 只留下类名
        String className = file.getName().substring(0, file.getName().length() - TARGET_SUFFIX_LENGTH);
        // 添加到集合中去
        classes.add(Class.forName(packageName + '.' + className));
      }
    }
    return classes;
  }
}
