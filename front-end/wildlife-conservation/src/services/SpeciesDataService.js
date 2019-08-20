class SpeciesDataService {

    sendData() {
        let data = { "id": 13, "content": "Hello, World!" };

        let url = 'http://localhost:8080/send';
        return fetch(url, {
            method: 'POST', // or 'PUT'
            body: JSON.stringify(data), // data can be `string` or {object}!
            headers: {
                'Content-Type': 'application/json'
            }
        })
    }


    fetchData = (url) => {
        //  let url = 'https://api.github.com/users/' + user + '/repos';
        // let url = 'http://localhost:8080/all-species';
        return fetch(url).then(response => response.json());

        /*.then((response) => {
            console.log(response);
            console.log('length', response.length);

        });*/

    }
}
export default new SpeciesDataService();