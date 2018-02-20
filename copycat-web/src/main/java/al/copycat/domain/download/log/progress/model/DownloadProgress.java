package al.copycat.domain.download.log.progress.model;

import al.copycat.domain.base.entity.AbstractAuditingEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "download_progresses")
public class DownloadProgress extends AbstractAuditingEntity {

	@Id
	private long id;

}
