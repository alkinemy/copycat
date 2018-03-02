package al.copycat.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "copycat.download")
public class DownloadProperties {

	private String root;
	private String contentRoot;
	private TorrentProperties torrent;

	@Getter
	@Setter
	public static class TorrentProperties {
		private String root;
		private String suffix;
	}

}
