<template>
    <div>
        <b-form @submit="onSubmit">
            <b-form-file v-model="file" :state="Boolean(file)" placeholder="Choose a file..."></b-form-file>
            <b-button type="submit">Upload</b-button>
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
            async onSubmit(event) {
                event.preventDefault();
                console.log(this.file);
                var data = new FormData();
                data.append("file", this.file);
                await this.$axios.$post(process.env.baseUrl + '/uploads/files', data)
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