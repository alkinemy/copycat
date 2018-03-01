<template>
    <div>
        <h1>SimpleFile download</h1>
        <b-form @submit="downloadFile">
            <b-form-file v-model="file" :state="Boolean(file)" placeholder="Choose a file..."></b-form-file>
            <b-button type="submit">Download</b-button>
        </b-form>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                file: null
            }
        },
        methods: {
            async downloadFile(event) {
                event.preventDefault();
                var data = new FormData();
                data.append("file", this.file);
                await this.$axios.$post(process.env.baseUrl + '/downloads/files', data)
                    .then(response => {
                        console.log('success');
                        console.log(response);
                    })
                    .catch(error => {
                        console.log('error');
                        console.log(error);
                    });
            }
        }
    }
</script>