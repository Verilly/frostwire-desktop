package com.limegroup.gnutella.gui.init;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.limewire.i18n.I18nMarker;
import org.limewire.util.CommonUtils;
import org.limewire.util.StringUtils;

import com.frostwire.components.TorrentSaveFolderComponent;
import com.frostwire.updates.UpdateManager;
import com.limegroup.gnutella.gui.GuiCoreMediator;
import com.limegroup.gnutella.gui.I18n;
import com.limegroup.gnutella.gui.SaveDirectoryHandler;
import com.limegroup.gnutella.gui.library.RecursiveSharingPanel;
import com.limegroup.gnutella.settings.SharingSettings;
/**
 * This class displays a setup window for allowing the user to choose
 * the directory for saving their files.
 */
class SaveWindow extends SetupWindow {

	private static final long serialVersionUID = 4918724013794478084L;

	private static final String LEARN_MORE_URL = "http://www.frostwire.com/faq#fil1";
    
    
	/**
	 * Constant handle to the <tt>LabeledTextField</tt> instance for the 
	 * save directory.
	 */
	private final JTextField SAVE_FIELD = new JTextField(20); 
		
	/**
	 * Variable for the default save directory to use.
	 */    
	private String _defaultSaveDir;
    
    // change for sharing files in saved folder
    private final JCheckBox CHECK_BOX = new JCheckBox();

	private TorrentSaveFolderComponent torrentSaveFolderComponent;

	/**
	 * Creates the window and its components
	 */
	SaveWindow(SetupManager manager, boolean migrate) {
		super(manager, I18nMarker.marktr("Torrent Data Save Folder"), describeText(migrate), LEARN_MORE_URL);
    }
	
	private static String describeText(boolean migrate) {
	    if(!migrate)
	        return I18nMarker.marktr("Please choose a folder where you would like your files to be downloaded.\n");
	    else
	        return I18nMarker.marktr("FrostWire now downloads files to a new, different folder.\nPlease confirm the folder where you would like your files to be downloaded.");
	}
    
    protected void createWindow() {
        super.createWindow();
        
		File saveDir = SharingSettings.getSaveDirectory();
		try {
		    _defaultSaveDir = saveDir.getCanonicalPath();
		} catch(IOException e) {
		    _defaultSaveDir = saveDir.getAbsolutePath();
		}

		JPanel mainPanel = new JPanel(new GridBagLayout());
		
//		JPanel saveFolderPanel = new JPanel(new GridBagLayout());
//		saveFolderPanel.setBorder(BorderFactory.createTitledBorder(I18n.tr("Gnutella Save Folder")));
//		
//		// "Save Folder" text field
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.weightx = 1;
//		gbc.insets = new Insets(0, 0, ButtonRow.BUTTON_SEP, 0);
//		gbc.gridwidth = GridBagConstraints.REMAINDER;
//		saveFolderPanel.add(SAVE_FIELD, gbc);
//		
//		gbc = new GridBagConstraints();
//		gbc.anchor = GridBagConstraints.NORTHWEST;
//		gbc.gridwidth = 1;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        saveFolderPanel.add(createOptionForShareInSavedFolderComponent(), gbc);
//		
//		// "Save Folder" buttons "User Default", "Browse..."
//		gbc = new GridBagConstraints();
//		gbc.anchor = GridBagConstraints.NORTHWEST;
//		gbc.gridwidth = GridBagConstraints.REMAINDER;
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		saveFolderPanel.add(new ButtonRow(new Action[] { new DefaultAction(), new BrowseAction() },
//				ButtonRow.X_AXIS, ButtonRow.LEFT_GLUE), gbc);
//		
//		gbc = new GridBagConstraints();
//        gbc.anchor = GridBagConstraints.NORTHWEST;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
//        gbc.weightx = 1;
//        gbc.weighty = 1;
//		mainPanel.add(saveFolderPanel, gbc);
		
        // "Saved Torrent Data" container
		torrentSaveFolderComponent = new TorrentSaveFolderComponent(true);
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainPanel.add(torrentSaveFolderComponent,gbc);        

        
//        // "Shared Folders" panel
//        JPanel sharingPanelContainer = new JPanel(new GridBagLayout());
//        gbc = new GridBagConstraints();
//        gbc.weightx = 1;
//        gbc.weighty = 1;
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.anchor = GridBagConstraints.NORTHWEST;
//        gbc.gridheight = GridBagConstraints.REMAINDER;
//        gbc.gridwidth = GridBagConstraints.RELATIVE;
//        recursiveSharingPanel.setBorder(BorderFactory.createTitledBorder(I18n.tr("Shared Folders")));
//        sharingPanelContainer.add(recursiveSharingPanel, gbc);
//        
//        // "Shared Folders" actions
//        gbc = new GridBagConstraints();
//        gbc.weightx = 0;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.anchor = GridBagConstraints.NORTHWEST;
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
//        gbc.insets = new Insets(0, ButtonRow.BUTTON_SEP, 0, 0);
//        sharingPanelContainer.add(new JButton(new SelectSharedDirectoryAction(recursiveSharingPanel, _manager.getOwnerComponent())), gbc);
//        gbc.insets = new Insets(ButtonRow.BUTTON_SEP, ButtonRow.BUTTON_SEP, 0, 0);
//        sharingPanelContainer.add(new JButton(new RemoveSharedDirectoryAction(recursiveSharingPanel)), gbc); 
        
        // "Shared Folders" container
//        gbc = new GridBagConstraints();
//        gbc.anchor = GridBagConstraints.NORTHWEST;
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
//        gbc.weightx = 1;
//        gbc.weighty = 1;
//        mainPanel.add(sharingPanelContainer, gbc);
        
        try {
		    SAVE_FIELD.setText(_defaultSaveDir);
        } catch(NullPointerException npe) {
            // internal swing error -- no biggie if it happens,
            // just means the user has to manually click 'Use Default'.
        }
		
		setSetupComponent(mainPanel);
	}
    
    
	/**
	 * Overrides applySettings method in SetupWindow.
	 *
	 * This method applies any settings associated with this setup window.
	 */
	public void applySettings(boolean loadCoreComponents) throws ApplySettingsException {
	    List<String> errors = new ArrayList<String>(2);
		try {
			String saveDirString = SAVE_FIELD.getText();
			File saveDir = new File(saveDirString);
            
			// Only do strict checks if we're loading...
			if(loadCoreComponents) {
    			if (!SaveDirectoryHandler.showVistaWarningIfNeeded(saveDir))
    			    throw new ApplySettingsException();
                
                if (!saveDir.isDirectory() && !saveDir.mkdirs())
                    throw new IOException();
			}
            
            // updates Incomplete directory etc...
            SharingSettings.setSaveDirectory(saveDir); 
		} catch(IOException ioe) {
		    errors.add(I18n.tr("FrostWire was unable to use the specified folder for saving files. Please try a different folder."));
		}
        
        if(loadCoreComponents) {
        //    GuiCoreMediator.getFileManager().loadWithNewDirectories(roots, recursiveSharingPanel.getFoldersToExclude(), false);
            UpdateManager.scheduleUpdateCheckTask(0);
        }
        
        String saveDirString = SAVE_FIELD.getText();
		File saveDir = new File(saveDirString);
		
		Set<File> saveDirs = new HashSet<File>();
		saveDirs.add(saveDir);
		
        
        SharingSettings.SHARE_DOWNLOADED_FILES_IN_NON_SHARED_DIRECTORIES.
            setValue(CHECK_BOX.isSelected());
        
        SharingSettings.SEED_FINISHED_TORRENTS.setValue(torrentSaveFolderComponent.isSeedingSelected());
        File folder = new File(torrentSaveFolderComponent.getTorrentSaveFolderPath());
        if (folder.exists() && folder.isDirectory() && folder.canWrite()) {
            SharingSettings.TORRENT_DATA_DIR_SETTING.setValue(folder);
        } else {
            if (!folder.mkdirs()) {
                errors.add(I18n.tr("FrostWire could not create the Torrent Data Folder {0}", folder));
            } else {
                SharingSettings.TORRENT_DATA_DIR_SETTING.setValue(folder);
            }
        }
        
        if (!errors.isEmpty()) {
            throw new ApplySettingsException(StringUtils.explode(errors, "\n\n"));
        }
	}
}




