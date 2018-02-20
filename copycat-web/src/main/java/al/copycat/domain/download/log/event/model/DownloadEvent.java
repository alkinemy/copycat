package al.copycat.domain.download.log.event.model;

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
@Table(name = "download_events")
public class DownloadEvent extends AbstractAuditingEntity {

	@Id
	private long id;

}
