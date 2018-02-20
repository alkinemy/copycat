package al.copycat.domain.download.log.unified.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "automatic_download_logs")
@DiscriminatorValue(DownloadType.Values.AUTOMATIC)
public class AutomaticDownloadLog extends DownloadLog {

	@Id
	private long id;

}
