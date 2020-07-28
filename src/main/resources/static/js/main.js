var app = new Vue({
    el: '#app',
    data: {
        items: [],
        tracks: [],
        isTable: false,
        isHidden: true
    },
    methods: {
        ftracks(id) {
            axios.get('http://localhost:8080/api/tracks/'+id)
                .then(response => (
                     this.tracks = response.data
                ))
                this.isTable = true
                this.isHidden = false
        },
        search(author) {
            axios.get('http://localhost:8080/api/search/'+author)
                .then(response => (
                    this.tracks = response.data
                 ))
                 this.isTable = true
                 this.isHidden = false
        }
    },
    mounted() {
        axios.get('http://localhost:8080/api/stations')
            .then(response => (
                this.items = response.data
            ))
    }

})

