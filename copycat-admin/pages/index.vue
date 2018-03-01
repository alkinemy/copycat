<template>
    <div>
        <FileDownload></FileDownload>
        <UrlDownload></UrlDownload>
    </div>
</template>

<script>
    import FileDownload from '../components/download/FileDownload.vue'
    import UrlDownload from '../components/download/UrlDownload.vue'

    export default {
        components: {
            'FileDownload': FileDownload,
            'UrlDownload': UrlDownload,
        }
    }
</script>
