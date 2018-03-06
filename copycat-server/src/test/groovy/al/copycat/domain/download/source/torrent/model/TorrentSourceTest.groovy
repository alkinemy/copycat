package al.copycat.domain.download.source.torrent.model

import spock.lang.Specification
import spock.lang.Unroll


@Unroll
class TorrentSourceTest extends Specification {

	def "UrlTorrentSource 생성 시에 생성되는 TorrentMetadata 확인"() {
		expect:
		UrlTorrentSource.fromUrl(source).getMetadata().getId().equalsIgnoreCase(id)

		where:
		source                                                                            || id
		"http://leopard-raws.org/download.php?hash=ac0ba762d8bf3fd7a9e059be2ac8449c"      || "b8018c9331ea4174b8c2e0ff45f9f1ecf69ea2c5"
		"https://dl.dmhy.org/2018/03/01/b6dcb1cb78d61acc05490f4587aa5bca6cb33c4e.torrent" || "b6dcb1cb78d61acc05490f4587aa5bca6cb33c4e"
	}


	def "MagnetTorrentSource 생성 시에 생성되는 TorrentMetadata 확인"() {
		expect:
		MagnetTorrentSource.fromMagnet(source).getMetadata().getId().equalsIgnoreCase(id)

		where:
		source || id
		"magnet:?xt=urn:btih:C88CA2210CBE65E1B1467892B4D79EA39DD201BA" || "C88CA2210CBE65E1B1467892B4D79EA39DD201BA"
	}

}
