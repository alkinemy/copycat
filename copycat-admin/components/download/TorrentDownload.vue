<template>
    <div>
        <h1>Torrent download(file link)</h1>
        <b-form @submit="downloadTorrent">
            <b-form-input v-model="torrent" type="text" placeholder="Enter url"></b-form-input>
            <b-button type="submit">Download</b-button>
        </b-form>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                torrent: null
            }
        },
        methods: {
            async downloadTorrent(event) {
                event.preventDefault();
                let data = {
                    "torrent": this.torrent,
                };
                await this.$axios.$post(process.env.baseUrl + '/downloads/torrents', data, {
                    'Content-Type': 'application/json'
                })
                    .then(response => {
                        console.log('success');
                        console.log(response);
                        this.torrent = "";
                    })
                    .catch(error => {
                        console.log('error');
                        console.log(error);
                    });
            }
        }
    }
</script>