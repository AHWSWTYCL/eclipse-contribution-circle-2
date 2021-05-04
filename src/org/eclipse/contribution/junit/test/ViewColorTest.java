/*
 * Created on 2021-4-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.contribution.junit.test;

import org.eclipse.contribution.junit.ResultView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import junit.framework.TestCase;

/**
 * @author Cocoa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ViewColorTest extends TestCase {
	private ResultView view;
	
	public void showView() throws PartInitException {
		view = (ResultView) getPage().showView(
				"org.eclipse.contribution.junit.resultView");
	}
	
	public void hideView() {
		getPage().hideView(view);
	}
	
	private IWorkbenchPage getPage() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		return window.getActivePage();
	}
	
	public void setUp() throws PartInitException {
		view = (ResultView) getPage().showView(
				"org.eclipse.contribution.junit.resultView");
	}
	
	public void tearDown() {
		getPage().hideView(view);
	}
	
	public void testResultViewGreen() throws PartInitException {
		// Run tests
		view.getListener().testsStarted(0);
		view.getListener().testsFinished();
		Display display = view.getControl().getDisplay();
		Color green = display.getSystemColor(SWT.COLOR_GREEN);
		assertEquals(green, view.getControl().getBackground());
	}
	
	public void testResultViewRed() throws PartInitException {
		view.getListener().testsStarted(0);
		view.getListener().testFailed("class", "method", "trace");
		Display display = view.getControl().getDisplay();
		Color red = display.getSystemColor(SWT.COLOR_RED);
		assertEquals(red, view.getControl().getBackground());
		view.getListener().testsFinished();
		assertEquals(red, view.getControl().getBackground());
	}
}
