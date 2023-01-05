var urlApi = 'http://rmffon.herokuapp.com/api'
//var urlApi = 'http://localhost:8080/api'

var app = new Vue({
    el: '#app',
    data: {
        stations: [],
        tracks: [],
        isTracksVisible: false,
        isTracksHidden: true
    },
    methods: {
        ftracks(id, radioService) {
            axios.get(urlApi+'/tracks/'+radioService+'/'+id)
                .then(response => (
                     this.tracks = response.data
                ))
                this.isTracksVisible = true
                this.isTracksHidden = false
        },
        search(author) {
            axios.get(urlApi+'/search/'+author)
                .then(response => (
                    this.tracks = response.data
                 ))
                 this.isTracksVisible = true
                 this.isTracksHidden = false
        }
    },
    mounted() {
        axios.get(urlApi+'/stations')
            .then(response => (
                this.stations = response.data
            ))
    }

})

