<template>
    <div>
        <h1>Url download</h1>
        <b-form @submit="downloadUrl">
            <b-form-input v-model="url" type="text" placeholder="Enter url"></b-form-input>
            <b-button type="submit">Download</b-button>
        </b-form>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                url: null
            }
        },
        methods: {
            async downloadUrl(event) {
                event.preventDefault();
                var data = new FormData();
                data.append("url", this.url);
                await this.$axios.$post(process.env.baseUrl + '/downloads/urls', data)
                    .then(response => {
                        console.log('success');
                        console.log(response);
                        this.url = "";
                    })
                    .catch(error => {
                        console.log('error');
                        console.log(error);
                    });
            }
        }
    }
</script>