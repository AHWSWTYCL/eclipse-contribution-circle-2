/*
 * Created on 2021-4-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.contribution.junit.test;

import junit.framework.TestCase;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @author Cocoa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ViewTest extends TestCase {
	private IWorkbenchPage getPage() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		return window.getActivePage();
	}
	
	public void testShowHide() throws PartInitException {
		IViewPart view = getPage().showView("org.eclipse.contribution.junit.resultView");
		getPage().hideView(view);
	}
}
