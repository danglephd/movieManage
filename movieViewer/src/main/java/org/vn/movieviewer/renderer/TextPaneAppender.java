/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.renderer;

import java.awt.Color;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import org.apache.log4j.Level;

import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.vn.movieviewer.view.main.NewJFrame;

public class TextPaneAppender extends WriterAppender {

    private static JTextPane jTextPanel;
//    private static long currentCursor = 0;
    private static StyledDocument document;
    private static long lineNumber = 0;
    private static  String threadName;

    /**
     * Set the target JTextArea for the logging information to appear.
     *
     * @param jTextPanel
     */
    public static void setTextArea(JTextPane jTextPanel) {
        TextPaneAppender.jTextPanel = jTextPanel;
        document = (StyledDocument) TextPaneAppender.jTextPanel.getDocument();
//        currentCursor = 0;
        lineNumber = 0;
    }

    //  private Color currentColor;
    /**
     * Format and then append the loggingEvent to the stored JTextArea.
     *
     * @param loggingEvent
     */
    @Override
    public void append(LoggingEvent loggingEvent) {

        final String message = this.layout.format(loggingEvent);
        final AttributeSet att = createAttribute(loggingEvent.getLevel());
//        System.err.println(threadName + " --- " + loggingEvent.getThreadName());
        //loggingEvent.getLoggerName()
        
        if (TextPaneAppender.threadName == null || TextPaneAppender.threadName.isEmpty()
                || TextPaneAppender.threadName.equals("ALL") || TextPaneAppender.threadName.equals(loggingEvent.getThreadName())) {
            {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (lineNumber > 3000) {
                                document.remove(0, document.getLength());
                               // document.
                                lineNumber = 0;
                            }
                            document.insertString(document.getLength(), message, att);
                            lineNumber++;
//                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+lineNumber);
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                            Logger.getLogger(TextPaneAppender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        }

        // Append formatted message to textarea using the Swing Thread.
    }

    /**
	 * @uml.property  name="styleContext"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    final StyleContext styleContext = StyleContext.getDefaultStyleContext();

    /**
	 * @uml.property  name="blackColor"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    final AttributeSet blackColor = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
    /**
	 * @uml.property  name="blueColor"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    final AttributeSet blueColor = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.BLUE);
    /**
	 * @uml.property  name="orangeColor"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    final AttributeSet orangeColor = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.ORANGE);
    /**
	 * @uml.property  name="redColor"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    final AttributeSet redColor = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.RED);
    /**
	 * @uml.property  name="grayColor"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    final AttributeSet grayColor = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.GRAY);
    /**
	 * @uml.property  name="defaultColor"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    final AttributeSet defaultColor = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.BLACK);

    private AttributeSet createAttribute(Level level) {
        if (level == Level.DEBUG) {
            //return styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.BLUE);
            return blueColor;
        } else if (level == Level.INFO) {
            return blackColor;
        } else if (level == Level.ERROR) {
            return redColor;
        } else if (level == Level.WARN) {
            return orangeColor;
        } else if (level == Level.TRACE) {
            return grayColor;
        } else {
            return defaultColor;
        }

    }

    public String getThreadName() {
        return threadName;
    }

    public static synchronized void setThreadName(String threadName) {
        TextPaneAppender.threadName = threadName;
    }

	/**
	 * @uml.property  name="mainApp"
	 * @uml.associationEnd  inverse="textPaneAppender:org.ict.simulator.MainApp"
	 */
	private NewJFrame mainApp;

	/**
	 * Getter of the property <tt>mainApp</tt>
	 * @return  Returns the mainApp.
	 * @uml.property  name="mainApp"
	 */
	public NewJFrame getMainApp() {
		return mainApp;
	}

	/**
	 * Setter of the property <tt>mainApp</tt>
	 * @param mainApp  The mainApp to set.
	 * @uml.property  name="mainApp"
	 */
	public void setMainApp(NewJFrame mainApp) {
		this.mainApp = mainApp;
	}

}
