/*
 * Created on 2021-5-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.contribution.junit.test;

import junit.framework.TestCase;

import org.eclipse.contribution.junit.TestSearcher;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;

/**
 * @author Cocoa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchTest extends TestCase {

	private TestProject testProject;
	private IPackageFragment pack;
	
	protected void setUp() throws Exception {
		testProject = new TestProject();
		testProject.addJar("org.junit", "junit.jar");
		pack = testProject.createPackage("pack1");
	}
	
	protected void tearDown() throws Exception {
		testProject.dispose();
	}
	
	public void testSearch() throws Exception {
		testProject.createType(pack, "AbstractTest.java",
				"public abstract class AbstractTest " +
				" extends junit.framework.TestCase { }");
		testProject.createType(pack, "NotATest.java",
				"public class NotATest { }");
		IType type = testProject.createType(pack, "ATest.java",
				"public class ATest extends junit.framework.TestCase { }");
		TestSearcher searcher = new TestSearcher();
		IType[] tests = searcher.findAll(testProject.getJavaProject(), null);
		assertEquals(1, tests.length);
		assertEquals(type, tests[0]);
	}
}
