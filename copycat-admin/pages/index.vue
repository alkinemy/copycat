<template>
    <div>
        <FileDownload></FileDownload>
        <UrlDownload></UrlDownload>
        <TorrentDownload></TorrentDownload>
        <MagnetDownload></MagnetDownload>
    </div>
</template>

<script>
    import FileDownload from '../components/download/FileDownload.vue'
    import UrlDownload from '../components/download/UrlDownload.vue'
    import TorrentDownload from '../components/download/TorrentDownload.vue'
    import MagnetDownload from '../components/download/MagnetDownload.vue'

    export default {
        components: {
            'FileDownload': FileDownload,
            'UrlDownload': UrlDownload,
            'TorrentDownload': TorrentDownload,
            'MagnetDownload': MagnetDownload,
        }
    }
</script>
