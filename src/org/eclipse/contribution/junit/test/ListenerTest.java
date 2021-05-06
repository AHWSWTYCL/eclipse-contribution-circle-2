/*
 * Created on 2021-4-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.contribution.junit.test;

import org.eclipse.contribution.junit.ITestRunListener;
import org.eclipse.contribution.junit.JUnitPlugin;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;

import junit.framework.TestCase;

/**
 * @author Cocoa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ListenerTest extends TestCase {
	private TestProject project;
	
	public static class Listener implements ITestRunListener {

		String testFailed;
		/* (non-Javadoc)
		 * @see org.eclipse.contribution.junit.ITestRunListener#testsStarted(int)
		 */
		public void testsStarted(IJavaProject project, int testCount) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see org.eclipse.contribution.junit.ITestRunListener#testsFinished()
		 */
		public void testsFinished(IJavaProject project) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see org.eclipse.contribution.junit.ITestRunListener#testStarted(java.lang.String, java.lang.String)
		 */
		public void testStarted(IJavaProject project, String klass, String method) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see org.eclipse.contribution.junit.ITestRunListener#testFailed(java.lang.String, java.lang.String, java.lang.String)
		 */
		public void testFailed(IJavaProject project, String klass, String method, String trace) {
			// TODO Auto-generated method stub
			testFailed = method + " " + klass;
		}
		
	}
	
	protected void setUp() throws Exception {
		project = new TestProject();
	}
	
	protected void tearDown() throws Exception {
		project.dispose();
	}
	
	public void testFailure() throws Exception {
		IPackageFragment pack = project.createPackage("pack1");
		IType type = project.createType(pack, "FailTest.java", 
				"public class FailTest extends junit.framework.TestCase {" +
				"public void testFailure() {fail();}}");
		
		project.addJar("org.junit", "junit.jar");
		Listener listener = new Listener();
		JUnitPlugin.getPlugin().addTestListener(listener);
		JUnitPlugin.getPlugin().run(type);
		assertEquals("testFailure pack1.FailTest", listener.testFailed);
	}
}
