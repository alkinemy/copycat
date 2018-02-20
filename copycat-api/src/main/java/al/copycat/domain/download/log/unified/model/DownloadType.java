package al.copycat.domain.download.log.unified.model;

import lombok.Getter;

public enum DownloadType {

	AUTOMATIC(Values.AUTOMATIC),
	MANUAL(Values.MANUAL);

	@Getter
	private String name;

	DownloadType(String name) {
		this.name = name;
	}

	public static class Values {
		public static final String AUTOMATIC = "AUTOMATIC";
		public static final String MANUAL = "MANUAL";
	}

}
