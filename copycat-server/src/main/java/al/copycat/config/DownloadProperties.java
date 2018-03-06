package al.copycat.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "copycat.download")
public class DownloadProperties {

	private String root;
	private ContentProperties content;
	private TorrentProperties torrent;

	@Getter
	@Setter
	public static class ContentProperties {
		private String root;
	}

	@Getter
	@Setter
	public static class TorrentProperties {
		private String root;
		private String suffix;
	}

	public String getContentRoot() {
		return content.root;
	}

}
