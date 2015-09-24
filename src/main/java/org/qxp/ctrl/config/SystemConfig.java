package org.qxp.ctrl.config;

public class SystemConfig {

	public static final String RELATIVE_PATH = System.getProperty("user.dir"); 
	
	public static final String PROJECT_NAME = "simple-project";
	
	public static final String PROJECT_TYPE = "maven";
	
	public static final String JAVA_CODE_PATH = "src/main/java";
	
	public static final String JAVA_RESOURCES_PATH = "src/main/resources";
	
	public static final String TEST_CODE_PATH = "src/test/java";
	
	public static final String TEST_RESOURCES_PATH = "src/test/resources";
	
	public static final String PROJECT_CONFIG_OUTPUT = "project-output";
	
	public static final String PROJECT_CONFIG_OUTPUT_NAME = PROJECT_NAME + "-config";
	
	public static final String PROJECT_OUTPUT = RELATIVE_PATH + "/" + TEST_RESOURCES_PATH + "/output";
	
	public static final String PACKAGE_NAME = "org/apex";
	
	public static final String MYBATIS_PACKAGE_NAME = "mapper";
	
	public static final String DAO_PACKAGE_NAME = "dao";
	
	private String projectName;
	
	private String projectType;
	
	private String javaCodePath;
	
	private String javaResourcesPath;
	
	private String testCodePath;
	
	private String testResourcesPath;
	
	private String projectConfigOutputName = this.projectName + "-config";
	
	private String projectOutput;
	
	private String packageName;
	
	private String mybatisPackageName;
	
	private String daoPackageName;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getJavaCodePath() {
		return javaCodePath;
	}

	public void setJavaCodePath(String javaCodePath) {
		this.javaCodePath = javaCodePath;
	}

	public String getJavaResourcesPath() {
		return javaResourcesPath;
	}

	public void setJavaResourcesPath(String javaResourcesPath) {
		this.javaResourcesPath = javaResourcesPath;
	}

	public String getTestCodePath() {
		return testCodePath;
	}

	public void setTestCodePath(String testCodePath) {
		this.testCodePath = testCodePath;
	}

	public String getTestResourcesPath() {
		return testResourcesPath;
	}

	public void setTestResourcesPath(String testResourcesPath) {
		this.testResourcesPath = testResourcesPath;
	}

	public String getProjectOutput() {
		return projectOutput;
	}

	public void setProjectOutput(String projectOutput) {
		this.projectOutput = projectOutput;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getMybatisPackageName() {
		return mybatisPackageName;
	}

	public void setMybatisPackageName(String mybatisPackageName) {
		this.mybatisPackageName = mybatisPackageName;
	}

	public String getDaoPackageName() {
		return daoPackageName;
	}

	public void setDaoPackageName(String daoPackageName) {
		this.daoPackageName = daoPackageName;
	}

	public String getProjectConfigOutputName() {
		return projectConfigOutputName;
	}
	
	
}
