package al.copycat.domain.download.torrent.service;

import com.turn.ttorrent.client.Client;

import java.util.Observable;
import java.util.Observer;

public class TorrentProgressObserver implements Observer {

	@Override
	public void update(Observable observable, Object argument) {
		Client client = (Client) observable;
		float progress = client.getTorrent().getCompletion();
	}

}
