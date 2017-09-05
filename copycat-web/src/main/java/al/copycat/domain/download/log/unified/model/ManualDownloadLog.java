package al.copycat.domain.download.log.unified.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "manual_download_logs")
@DiscriminatorValue(DownloadType.Values.MANUAL)
public class ManualDownloadLog extends DownloadLog {
}
