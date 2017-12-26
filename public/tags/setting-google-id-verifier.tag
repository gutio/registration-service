<setting-google-id-verifier>

    <p each="{ settings }">
        <setting-google-id-verifier-condition name="{ name }" id="{ id }"></setting-google-id-verifier-condition>
    </p>

    <script>
        this.settings = [{name : 'Loading...', id : 'Loading...'}]

        var self = this

        fetch( 'http://localhost:9000/api/google_setting' )
            .then( function ( data ) {
                return data.json()
            } )
            .then( function ( json ) {
                self.settings = [
                    {name : 'iosClientId', id : json.iosClientId},
                    {name : 'androidClientId', id : json.androidClientId},
                    {name : 'webClientId', id : json.webClientId}
                ]
                self.update()
            } )

    </script>

</setting-google-id-verifier>

