<template>
    <div>
        <h1>Magnet download</h1>
        <b-form @submit="downloadMagnet">
            <b-form-input v-model="magnet" type="text" placeholder="Enter magnet"></b-form-input>
            <b-button type="submit">Download</b-button>
        </b-form>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                magnet: null
            }
        },
        methods: {
            async downloadMagnet(event) {
                event.preventDefault();
                let data = {
                    "magnet": this.magnet
                };
                await this.$axios.$post(process.env.baseUrl + '/downloads/torrents/magnets', data)
                    .then(response => {
                        console.log('success');
                        console.log(response);
                        this.magnet = "";
                    })
                    .catch(error => {
                        console.log('error');
                        console.log(error);
                    });
            }
        }
    }
</script>