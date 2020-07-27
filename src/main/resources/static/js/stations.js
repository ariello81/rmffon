var app = new Vue({
    el: '#app',
    data: {
        items: [],
    },
    mounted() {
        axios
            .get('http://localhost:8080/api/stations')
            .then(response => (
                this.items = response.data
            ))
    }

})