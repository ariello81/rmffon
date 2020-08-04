var app = new Vue({
    el: '#app',
    data: {
        items: [],
        tracks: [],
        isTracksVisible: false,
        isTracksHidden: true
    },
    methods: {
        ftracks(id) {
            axios.get('http://localhost:8080/api/tracks/'+id)
                .then(response => (
                     this.tracks = response.data
                ))
                this.isTracksVisible = true
                this.isTracksHidden = false
        },
        search(author) {
            axios.get('http://localhost:8080/api/search/'+author)
                .then(response => (
                    this.tracks = response.data
                 ))
                 this.isTracksVisible = true
                 this.isTracksHidden = false
        }
    },
    mounted() {
        axios.get('http://localhost:8080/api/stations')
            .then(response => (
                this.items = response.data
            ))
    }

})

