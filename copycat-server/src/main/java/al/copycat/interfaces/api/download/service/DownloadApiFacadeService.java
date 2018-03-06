package al.copycat.interfaces.api.download.service;

import al.copycat.config.DownloadProperties;
import al.copycat.domain.download.execution.common.service.DownloadFacadeService;
import al.copycat.domain.download.execution.simple.model.MultipartFileDownloadForm;
import al.copycat.domain.download.execution.simple.model.UrlDownloadForm;
import al.copycat.domain.download.execution.torrent.model.MagnetTorrentDownloadForm;
import al.copycat.domain.download.execution.torrent.model.UrlTorrentDownloadForm;
import al.copycat.domain.download.source.simple.model.MultipartFileSource;
import al.copycat.domain.download.source.simple.model.UrlSource;
import al.copycat.domain.download.source.torrent.model.MagnetTorrentSource;
import al.copycat.domain.download.source.torrent.model.UrlTorrentSource;
import al.copycat.interfaces.api.download.dto.MagnetDownloadDto;
import al.copycat.interfaces.api.download.dto.MultipartFileDownloadDto;
import al.copycat.interfaces.api.download.dto.TorrentDownloadDto;
import al.copycat.interfaces.api.download.dto.UrlDownloadDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class DownloadApiFacadeService {

	private final DownloadFacadeService downloaderDelegateService;

	private final DownloadProperties downloadProperties;

	@Autowired
	public DownloadApiFacadeService(DownloadFacadeService downloaderDelegateService, DownloadProperties downloadProperties) {
		this.downloaderDelegateService = downloaderDelegateService;
		this.downloadProperties = downloadProperties;
	}

	public Mono<Void> download(MultipartFileDownloadDto downloadDto) {
		Mono<MultipartFileSource> sourceMono = Mono.fromCallable(() -> MultipartFileSource.of(downloadDto.getFile()));
		Mono<Path> downloadPathMono = Mono.fromCallable(() -> Paths.get(downloadProperties.getContentRoot()))
			.map(path -> path.resolve(downloadDto.getFile().getOriginalFilename()));
		return Mono.defer(() -> Mono.zip(sourceMono, downloadPathMono))
			.map(tuple -> MultipartFileDownloadForm.of(tuple.getT1(), tuple.getT2()))
			.map(downloaderDelegateService::download)
			.then();
	}

	//FIXME: tuple(t1, t2) is implicit indicator, apply explicit indicator
	public Mono<Void> download(UrlDownloadDto downloadDto) {
		Mono<UrlSource> sourceMono = Mono.fromCallable(() -> UrlSource.of(downloadDto.getUrl()));
		Mono<Path> downloadPathMono = Mono.fromCallable(() -> FilenameUtils.getName(downloadDto.getUrl()))
			.map(fileName -> Paths.get(downloadProperties.getRoot(), fileName));
		return Mono.defer(() -> Mono.zip(sourceMono, downloadPathMono))
			.map(tuple -> UrlDownloadForm.of(tuple.getT1(), tuple.getT2()))
			.map(downloaderDelegateService::download)
			.then();
	}

	public Mono<Void> download(MagnetDownloadDto downloadDto) {
		Mono<MagnetTorrentSource> sourceMono = Mono.fromCallable(
			() -> MagnetTorrentSource.fromMagnet(downloadDto.getTorrent()));
		Mono<Path> downloadPathMono = Mono.fromCallable(() -> Paths.get(downloadProperties.getContentRoot()));
		return Mono.defer(() -> Mono.zip(sourceMono, downloadPathMono))
			.map(tuple -> MagnetTorrentDownloadForm.of(tuple.getT1(), tuple.getT2()))
			.map(downloaderDelegateService::download)
			.then();
	}

	//FIXME: tuple(t1, t2) is implicit indicator, apply explicit indicator
	public Mono<Void> download(TorrentDownloadDto downloadDto) {
		return Mono.defer(() -> Mono.fromCallable(() -> UrlTorrentSource.fromUrl(downloadDto.getTorrent())))
			.flatMap(source -> Mono.just(source).zipWith(urlTorrentPathMono(source.getMetadata().getName())))
			.map(tuple -> UrlTorrentDownloadForm.of(tuple.getT1(), tuple.getT2().getT1(), tuple.getT2().getT2()))
			.map(downloaderDelegateService::download)
			.then();
	}

	private Mono<Tuple2<Path, Path>> urlTorrentPathMono(String torrentName) {
		return Mono.just(torrentName)
			.flatMap(name -> Mono.zip(
				Mono.fromCallable(() -> Paths.get(downloadProperties.getTorrent().getRoot()))
					.map(path -> path.resolve(torrentName + downloadProperties.getTorrent().getSuffix())),
				Mono.fromCallable(() -> Paths.get(downloadProperties.getContentRoot()))
			));
	}

}
