var urlApi = 'http://rmffon.herokuapp.com/api'

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
            axios.get(urlApi+'/tracks/'+id)
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
                this.items = response.data
            ))
    }

})

