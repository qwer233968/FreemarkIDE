package org.qxp.ctrl.maven;

import org.junit.Test;
import org.qxp.ctrl.maven.po.Dependency;
import org.qxp.ctrl.maven.po.PomProperty;

public class PomActionTest {

	public static String RELATIVE_PATH = System.getProperty("user.dir");
	@Test
	public void testAddProperties(){
		String path = RELATIVE_PATH + "/src/test/java/org/qxp/ctrl/maven/pom.xml";
		System.out.println(path);
		
		PomProperty pomProperty = new PomProperty();
		pomProperty.setKey("spring");
		pomProperty.setValue("4.1.7.RELEASE");
		boolean result1 = PomAction.addProperties(path, pomProperty);
		System.out.println(result1);
		
		Dependency d1 = new Dependency();
		d1.setGroupId("org.springframework");
		d1.setArtifactId("spring-core");
		d1.setVersion("${spring.version}");
		d1.setScope("compile");
		Dependency d2 = new Dependency();
		d2.setGroupId("org.springframework");
		d2.setArtifactId("spring-beans");
		d2.setVersion("${spring.version}");
		d2.setScope("compile");
		Dependency[] dependencyArray = {d1, d2};
		boolean result2 = PomAction.addDependencys(path, dependencyArray);
		System.out.println(result2);
	}
}
