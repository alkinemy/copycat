package al.copycat.domain.download.log.unified.model;

import al.copycat.domain.base.entity.AbstractAuditingEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "download_logs")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "run_type")
public class DownloadLog extends AbstractAuditingEntity {

	@Id
	private long id;

}
