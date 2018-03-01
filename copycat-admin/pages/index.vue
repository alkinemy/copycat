<template>
    <div>
        <FileDownload></FileDownload>
        <UrlDownload></UrlDownload>
        <TorrentDownload></TorrentDownload>
    </div>
</template>

<script>
    import FileDownload from '../components/download/FileDownload.vue'
    import UrlDownload from '../components/download/UrlDownload.vue'
    import TorrentDownload from '../components/download/TorrentDownload.vue'

    export default {
        components: {
            'FileDownload': FileDownload,
            'UrlDownload': UrlDownload,
            'TorrentDownload': TorrentDownload,
        }
    }
</script>
