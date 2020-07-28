var app = new Vue({
    el: '#app',
    data: {
        items: [],
        tracks: []
    },
    methods: {
        ftracks(id) {
            axios.get('http://localhost:8080/api/tracks/'+id)
              .then(response => (
                     this.tracks = response.data
              ))
        },
        search(author) {
                    axios.get('http://localhost:8080/api/search/'+author)
                      .then(response => (
                             this.tracks = response.data
                      ))
        }
    },
    mounted() {
        axios.get('http://localhost:8080/api/stations')
            .then(response => (
                this.items = response.data
            ))
    }

})

