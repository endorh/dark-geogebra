package org.geogebra.desktop.gui.util;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.geogebra.common.awt.GColor;
import org.geogebra.common.io.MyXMLio;
import org.geogebra.common.util.Charsets;
import org.geogebra.common.util.debug.Log;
import org.geogebra.desktop.awt.GGraphics2DD;
import org.geogebra.desktop.gui.MyImageD;
import org.geogebra.desktop.gui.theme.ColorKeys;
import org.geogebra.desktop.gui.theme.ThemeD;
import org.geogebra.desktop.io.MyXMLioD;
import org.geogebra.desktop.main.AppD;

/**
 * An enhanced file chooser for GeoGebra which can be used to load images or ggb
 * files with a preview image.
 * 
 * @author Florian Sonner
 * @version 1.0
 */
public class GeoGebraFileChooser extends JFileChooser
		implements ComponentListener {
	private static final long serialVersionUID = 1L;

	/**
	 * The with at which the accessory is displayed.
	 */
	private static final int ACCESSORY_WIDTH = 600;

	/**
	 * The file chooser is used to load images at the moment.
	 */
	public static final int MODE_IMAGES = 0;

	/**
	 * The file chooser is used to load ggb files at the moment.
	 */
	public static final int MODE_GEOGEBRA = 1;

	/**
	 * The file chooser is used to save ggb files at the moment.
	 */
	public static final int MODE_GEOGEBRA_SAVE = 2;

	/**
	 * The file chooser is used to load data files at the moment.
	 */
	public static final int MODE_DATA = 3;

	/**
	 * An instance of the GeoGebra application.
	 */
	AppD app;

	/**
	 * The current mode of the file chooser.
	 */
	private int currentMode = -1;

	/**
	 * The accessory panel which displays the preview of the selected file.
	 */
	private final PreviewPanel previewPanel;

	/**
	 * Whether to show the accessory or not. The accessory is not displayed in
	 * case the dialog is too small.
	 */
	private boolean showAccessory = true;

	/**
	 * Construct a file chooser without a restricted file system view.
	 * 
	 * May throw IOException: Could not get shell folder ID list (Java bug
	 * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6544857)
	 * 
	 * If an exception is catched, the constructor with a restricted file system
	 * view should be used.
	 * 
	 * @param app
	 *            applivcation
	 * 
	 * @param currentDirectory
	 *            directory
	 */
	public GeoGebraFileChooser(AppD app, File currentDirectory) {
		this(app, currentDirectory, false);
	}

	/**
	 * Construct a file chooser which may have a restricted file system view if
	 * the second parameter is set to true.
	 * 
	 * @param app
	 *            application
	 * 
	 * @param currentDirectory
	 *            directory
	 * @param restricted
	 *            whether to use RestrictedFileSystemView
	 */
	public GeoGebraFileChooser(AppD app, File currentDirectory,
			boolean restricted) {
		super(currentDirectory,
				restricted ? new RestrictedFileSystemView() : null);

		this.app = app;

		previewPanel = new PreviewPanel(this);
		setAccessory(previewPanel);
		addPropertyChangeListener(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY,
				previewPanel);

		addComponentListener(this);

		setMode(MODE_GEOGEBRA); // default mode is the mode to load geogebra
								// files

		Dimension d = this.getPreferredSize();
		d.width = Math.max(ACCESSORY_WIDTH, d.width);
		this.setPreferredSize(d);
	}

	/**
	 * Get the current mode of the file chooser.
	 * 
	 * @return current mode
	 */
	public int getMode() {
		return currentMode;
	}

	/**
	 * Set a new mode for the file chooser. Use the constants defined in this
	 * class for the different modes.
	 * 
	 * @param mode0
	 *            file selection mode
	 */
	public void setMode(int mode0) {
		// invalid mode?
		int mode = mode0;
		if (mode != MODE_IMAGES && mode != MODE_GEOGEBRA
				&& mode != MODE_GEOGEBRA_SAVE && mode != MODE_DATA) {
			Log.debug(
					"Invalid file chooser mode, MODE_GEOGEBRA used as default.");
			mode = MODE_GEOGEBRA;
		}

		// do not perform any unnecessary actions
		if (this.currentMode == mode) {
			return;
		}

		if (mode == MODE_GEOGEBRA) { // load/save ggb, ggt etc. files
			setMultiSelectionEnabled(true);
		} else { // load images
			setMultiSelectionEnabled(false);
		}

		// set the preview panel type: image, data or ?
		previewPanel.setPreviewPanelType(mode);

		// TODO apply mode specific settings..

		this.currentMode = mode;
	}

	/**
	 * Check if we have to show / hide the accessory
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		if (getSize().width < ACCESSORY_WIDTH && showAccessory) {
			setAccessory(null);
			removePropertyChangeListener(
					JFileChooser.SELECTED_FILE_CHANGED_PROPERTY, previewPanel);

			showAccessory = false;
			validate();
		} else if (getSize().width > ACCESSORY_WIDTH && !showAccessory) {
			setAccessory(previewPanel);
			addPropertyChangeListener(
					JFileChooser.SELECTED_FILE_CHANGED_PROPERTY, previewPanel);

			// fire an event that the selected file has changed to update the
			// preview image
			previewPanel.propertyChange(null);

			showAccessory = true;
			validate();
		}
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// nothing to do
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// nothing to do
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// nothing to do
	}

	/**
	 * Component to preview image files in a file chooser.
	 * 
	 * This file is based on Hack #31 in
	 * "Swing Hacks - Tips & Tools for Building Killer GUIs" by Joshua Marinacci
	 * and Chris Adamson.
	 * 
	 * Modified & commented by Florian Sonner for GeoGebraFileChooser
	 * 
	 * @author Joshua Marinacci
	 * @author Chris Adamson
	 * @author Philipp Weissenbacher (materthron@users.sourceforge.net)
	 * @author Florian Sonner
	 */

	private class PreviewPanel extends JPanel
			implements PropertyChangeListener {
		private static final long serialVersionUID = 1L;

		/**
		 * The maximum file size of images in kb.
		 */
		private static final int maxImageSize = 300;

		private final GeoGebraFileChooser fileChooser;

		/**
		 * The image to draw in the preview area.
		 */
		private MyImageD img = null;

		/**
		 * The panel on which the image is drawn.
		 */
		private final ImagePanel imagePanel;

		/**
		 * Panel for data preview.
		 */
		private JTextArea dataPreviewPanel = null;

		/**
		 * CardLayout panel to hold different preview panels
		 */
		private final JPanel cards;

		/**
		 * A label to describe the properties of the selected file.
		 */
		private final Label fileLabel;

		public PreviewPanel(GeoGebraFileChooser fileChooser) {
			this.fileChooser = fileChooser;

			setLayout(new BorderLayout());
			setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // border at
																	// the left
																	// & right

			imagePanel = new ImagePanel();

			cards = new JPanel(new CardLayout());
			cards.add("imagePanel", imagePanel);
			cards.add("dataPanel", buildDataPanel());
			add(BorderLayout.CENTER, cards);

			fileLabel = new Label();
			add(BorderLayout.SOUTH, fileLabel);
		}

		public void setPreviewPanelType(int mode) {
			CardLayout layout = (CardLayout) cards.getLayout();
			if (mode == GeoGebraFileChooser.MODE_DATA) {
				layout.show(cards, "dataPanel");
				dataPreviewPanel.setText(
						app.getLocalization().getMenu("PreviewUnavailable"));
			} else {
				layout.show(cards, "imagePanel");
				setImg(null);
			}
			fileLabel.setText(null);
		}

		public JScrollPane buildDataPanel() {
			dataPreviewPanel = new JTextArea();
			dataPreviewPanel.setEditable(false);
			dataPreviewPanel.setWrapStyleWord(false);
			dataPreviewPanel.setLineWrap(false);
			dataPreviewPanel.setPreferredSize(imagePanel.getPreferredSize());
			dataPreviewPanel.setMargin(new Insets(5, 5, 5, 5));
			dataPreviewPanel.setText(
					app.getLocalization().getMenu("PreviewUnavailable"));
			JScrollPane scroller = new JScrollPane(dataPreviewPanel);
			scroller.setHorizontalScrollBarPolicy(
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scroller.setVerticalScrollBarPolicy(
					ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

			return scroller;
		}

		/**
		 * A new file was selected -> update the panel if necessary.
		 */
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			try {
				File file = fileChooser.getSelectedFile();

				if (file != null && file.exists()) {
					// change
					if (fileChooser.getMode() == GeoGebraFileChooser.MODE_DATA) {
						updateDataPreview(file);
					} else {
						updateImage(file);
					}
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		/**
		 * Updates the data preview panel
		 * 
		 * @param file file to be previewed
		 * @throws IOException if file cannot be opened
		 */
		private void updateDataPreview(File file) throws IOException {

			fileLabel.setText("");
			String fileName = file.getName();

			StringBuilder contents = new StringBuilder();
			BufferedReader reader = null;

			try {
				reader = new BufferedReader(new InputStreamReader(
						new FileInputStream(file), Charsets.getUtf8()));
				String text;
				int lineCount = 0;
				// read at most 20 lines
				while ((text = reader.readLine()) != null && lineCount < 20) {
					contents.append(text)
							.append(System.getProperty("line.separator"));
					lineCount++;
				}

				StringBuilder fileInfo = new StringBuilder();

				if (fileName.length() > 20) {
					fileInfo.append(fileName.substring(0, 20));
					fileInfo.append("..");
				} else {
					fileInfo.append(fileName);
				}

				fileLabel.setText(fileInfo.toString());

				if (contents.length() == 0) {
					contents.append(app.getLocalization()
							.getMenu("PreviewUnavailable"));
				}

				dataPreviewPanel.setText(contents.toString());
				dataPreviewPanel.setCaretPosition(0);
			} catch (FileNotFoundException e) {
				e.printStackTrace();

			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		/**
		 * Update the preview image if it's possible to load one.
		 * 
		 * @param file file to preview
		 * @throws IOException if file cannot be opened
		 */
		private void updateImage(File file) throws IOException {
			fileLabel.setText("");

			try {
				MyImageD tmpImage = null;
				String fileName = file.getName();

				// Update preview for opening ggb files
				if (fileChooser
						.getMode() == GeoGebraFileChooser.MODE_GEOGEBRA) {

					if (fileName.endsWith(".ggb")) {
						tmpImage = new MyImageD(MyXMLioD.getPreviewImage(file)); // load
																					// preview
																					// from
																					// zip

						StringBuilder fileInfo = new StringBuilder();

						if (fileName.length() > 20) {
							fileInfo.append(fileName.substring(0, 20));
							fileInfo.append("..");
						} else {
							fileInfo.append(fileName);
						}

						fileInfo.append(" : ");
						fileInfo.append(file.length() / 1024);
						fileInfo.append(" kB");
						fileLabel.setText(fileInfo.toString());
					}
				}

				// Update preview for saving a ggb file
				else if (fileChooser
						.getMode() == GeoGebraFileChooser.MODE_GEOGEBRA_SAVE) {
					tmpImage = (MyImageD)
							app.getExportImage(MyXMLio.THUMBNAIL_PIXELS_X,
									MyXMLio.THUMBNAIL_PIXELS_Y);
					// TODO: show file size info?
					fileLabel.setText(null);
				}

				// Update preview for images
				else {
					// fails for a few JPEGs so turn off preview for large files
					if (file.length() < 1024 * maxImageSize) {

						tmpImage = MyImageD.fromFile(file, fileName);
						StringBuilder imgInfo = new StringBuilder();

						if (fileName.length() > 20) {
							imgInfo.append(fileName.substring(0, 20));
							imgInfo.append("..");
						} else {
							imgInfo.append(fileName);
						}

						if (tmpImage != null) {
							imgInfo.append(" : ");
							imgInfo.append(tmpImage.getWidth());
							imgInfo.append(" x ");
							imgInfo.append(tmpImage.getHeight());
						}
						fileLabel.setText(imgInfo.toString());
					}
				}

				// resize tmp image if necessary
				if (tmpImage != null) {

					int oldWidth = tmpImage.getWidth();
					int oldHeight = tmpImage.getHeight();

					int newWidth;
					int newHeight;

					if (oldWidth > ImagePanel.SIZE
							|| oldHeight > ImagePanel.SIZE) {
						if (oldWidth > oldHeight) {
							newWidth = ImagePanel.SIZE;
							newHeight = (ImagePanel.SIZE * oldHeight)
									/ oldWidth;
						} else {
							newWidth = (ImagePanel.SIZE * oldWidth) / oldHeight;
							newHeight = ImagePanel.SIZE;
						}
						// Create a new image for the scaled preview image
						setImg(new MyImageD(new BufferedImage(newWidth,
								newHeight, BufferedImage.TYPE_INT_RGB)));

						GGraphics2DD graphics2D = (GGraphics2DD) getImg()
								.createGraphics();
						if (tmpImage.isSVG()) {
							graphics2D.setColor(GColor.WHITE);
							graphics2D.fillRect(0, 0, newWidth, newHeight);
						}
						graphics2D.drawImageScaled(tmpImage, newWidth, newHeight);
						graphics2D.dispose();
					} else {
						setImg(tmpImage);
					}
				} else {
					setImg(null);
				}

				repaint();
			} catch (IllegalArgumentException iae) {
				// This is thrown if you select .ico files
				setImg(null);
			} catch (Throwable t) {
				t.printStackTrace();
				Log.debug(t.getClass() + "");
				setImg(null);
			}
		}

		MyImageD getImg() {
			return img;
		}

		private void setImg(MyImageD img) {
			this.img = img;
		}

		/**
		 * The panel at which the real preview image is drawn.
		 * 
		 * @author Florian Sonner
		 */
		private class ImagePanel extends JPanel {
			private static final long serialVersionUID = 1L;

			/**
			 * The size of the image panel.
			 */
			public final static int SIZE = 200;

			public ImagePanel() {
				setPreferredSize(new Dimension(SIZE, SIZE));
				setBorder(BorderFactory.createEtchedBorder());
			}

			/**
			 * Paint the preview area.
			 */
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;

				// fill background
				g2.setColor(ThemeD.color(ColorKeys.BACKGROUND));
				g2.fillRect(0, 0, getWidth(), getHeight());

				GGraphics2DD.setAntialiasing(g2);

				// draw preview image if possible
				if (getImg() != null) {
					// calculate the scaling factor
					int width = getImg().getWidth();
					int height = getImg().getHeight();

					// center image
					int x = (getWidth() - width) / 2;
					int y = (getHeight() - height) / 2;

					// draw the image
					new GGraphics2DD(g2).drawImage(getImg(), x, y);
				}

				// draw "no preview" message
				else {
					String message = app.getLocalization()
							.getMenu("PreviewUnavailable");

					FontMetrics fm = g2.getFontMetrics();
					Rectangle2D bounds = fm.getStringBounds(message, g2);

					g2.setColor(Color.darkGray);
					g2.drawString(message,
							(float) (getWidth() - bounds.getWidth()) / 2,
							(float) (getHeight() - bounds.getHeight()) / 2);
				}
			}
		}
	}

	/*
	 * TODO: override to make File Chooser non-modal for VirtualKeyboard
	 * protected JDialog createDialog(Component parent) throws HeadlessException
	 * { JDialog dialog = super.createDialog(parent); dialog.setModal(false);
	 * return dialog; }
	 */

}
