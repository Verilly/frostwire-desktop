package com.limegroup.gnutella.gui.options.panes;

import java.io.IOException;

import com.frostwire.bittorrent.BTConfigurator;
import org.limewire.i18n.I18nMarker;

import com.limegroup.gnutella.gui.BoxPanel;
import com.limegroup.gnutella.gui.I18n;
import com.limegroup.gnutella.gui.LabeledComponent;
import com.limegroup.gnutella.gui.SizedWholeNumberField;
import com.limegroup.gnutella.gui.WholeNumberField;
import com.limegroup.gnutella.settings.BittorrentSettings;

public class TorrentConnectionPaneItem extends AbstractPaneItem {

	public final static String TITLE = I18n.tr("BitTorrent Connection Settings");

	public final static String TEXT = I18n.tr("Adjust connection settings to make better use of your internet connection");
	
	public final static String MAX_ACTIVE_DOWNLOADS = I18n.tr("Maximum active downloads");
	
	public final static String MAX_GLOBAL_NUM_CONNECTIONS = I18n.tr("Global maximum number of connections");
	
	public final static String MAX_PEERS_PER_TORRENT = I18n.tr("Maximum number of connected peers per torrent");
	
	public final static String MAX_UPLOAD_SLOTS = I18n.tr("Number of upload slots per torrent");

	private WholeNumberField MAX_ACTIVE_DOWNLOADS_FIELD = new SizedWholeNumberField(4);
	
	private WholeNumberField MAX_GLOBAL_NUM_CONNECTIONS_FIELD = new SizedWholeNumberField(4);
	
	private WholeNumberField MAX_PEERS_PER_TORRENT_FIELD = new SizedWholeNumberField(4);
	
	private WholeNumberField MAX_UPLOAD_SLOTS_FIELD = new SizedWholeNumberField(4);

	public TorrentConnectionPaneItem() {
		super(TITLE, TEXT);

		BoxPanel panel = new BoxPanel();

		LabeledComponent comp = new LabeledComponent(
				I18nMarker.marktr(MAX_ACTIVE_DOWNLOADS),
				MAX_ACTIVE_DOWNLOADS_FIELD, LabeledComponent.LEFT_GLUE,
				LabeledComponent.LEFT);
		panel.add(comp.getComponent());
		panel.addVerticalComponentGap();
		
		comp = new LabeledComponent(
				I18nMarker.marktr(MAX_GLOBAL_NUM_CONNECTIONS),
				MAX_GLOBAL_NUM_CONNECTIONS_FIELD, LabeledComponent.LEFT_GLUE,
				LabeledComponent.LEFT);
		panel.add(comp.getComponent());
		panel.addVerticalComponentGap();

		comp = new LabeledComponent(
				I18nMarker.marktr(MAX_PEERS_PER_TORRENT),
				MAX_PEERS_PER_TORRENT_FIELD, LabeledComponent.LEFT_GLUE,
				LabeledComponent.LEFT);
		panel.add(comp.getComponent());
		panel.addVerticalComponentGap();

		comp = new LabeledComponent(
				I18nMarker.marktr(MAX_UPLOAD_SLOTS),
				MAX_UPLOAD_SLOTS_FIELD, LabeledComponent.LEFT_GLUE,
				LabeledComponent.LEFT);
		panel.add(comp.getComponent());
		panel.addVerticalComponentGap();

		add(panel);

	}

	@Override
	public boolean isDirty() {
        // TODO:BITTORRENT
        return true;
		//return (BittorrentSettings.TORRENT_MAX_ACTIVE_DOWNLOADS.getValue() != MAX_ACTIVE_DOWNLOADS_FIELD.getValue()) ||
		//	(COConfigurationManager.getIntParameter("Max.Peer.Connections.Total") != MAX_GLOBAL_NUM_CONNECTIONS_FIELD.getValue()) ||
		//	(COConfigurationManager.getIntParameter("Max.Peer.Connections.Per.Torrent") != MAX_PEERS_PER_TORRENT_FIELD.getValue()) ||
		//	(COConfigurationManager.getIntParameter("Max Uploads") != MAX_UPLOAD_SLOTS_FIELD.getValue());
	}

	@Override
	public void initOptions() {
        // TODO:BITTORRENT
		//ConfigurationManager.getInstance().load();
		//MAX_ACTIVE_DOWNLOADS_FIELD.setValue(BittorrentSettings.TORRENT_MAX_ACTIVE_DOWNLOADS.getValue());
		//MAX_GLOBAL_NUM_CONNECTIONS_FIELD.setValue(COConfigurationManager.getIntParameter("Max.Peer.Connections.Total"));
		//MAX_PEERS_PER_TORRENT_FIELD.setValue(COConfigurationManager.getIntParameter("Max.Peer.Connections.Per.Torrent"));
        MAX_UPLOAD_SLOTS_FIELD.setValue(BTConfigurator.getMaxUploads());
	}

	@Override
	public boolean applyOptions() throws IOException {
        // TODO:BITTORRENT
		BittorrentSettings.TORRENT_MAX_ACTIVE_DOWNLOADS.setValue(MAX_ACTIVE_DOWNLOADS_FIELD.getValue());
		//COConfigurationManager.setParameter("Max.Peer.Connections.Total",MAX_GLOBAL_NUM_CONNECTIONS_FIELD.getValue());
		//COConfigurationManager.setParameter("Max.Peer.Connections.Per.Torrent",MAX_PEERS_PER_TORRENT_FIELD.getValue());
        BTConfigurator.setMaxUploads(MAX_UPLOAD_SLOTS_FIELD.getValue());

		return false;
	}

}
