package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.log.progress.repository.DownloadProgressRepository;
import com.turn.ttorrent.client.Client;

import java.util.Observable;
import java.util.Observer;

public class TorrentProgressObserver implements Observer {

	private final DownloadProgressRepository downloadProgressRepository;

	public TorrentProgressObserver(DownloadProgressRepository downloadProgressRepository) {
		this.downloadProgressRepository = downloadProgressRepository;
	}

	@Override
	public void update(Observable observable, Object argument) {
		Client client = (Client) observable;
		float progress = client.getTorrent().getCompletion();
		//FIXME persist
	}

}
