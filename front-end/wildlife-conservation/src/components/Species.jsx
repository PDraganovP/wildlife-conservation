import React from 'react';
import { Link } from 'react-router-dom'
import ModalComponent from './ModalComponent';

import SpeciesDataService from '../services/SpeciesDataService';
import { ALL_SPECIES, DELETE_SPECIES } from '../constants/UrlConstants';

class Species extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            species: [],
            kingdoms: [],
            conservationStatus: [],
            show: false,
            delete: ''

        }
    }

    getSpecies = () => {
        let url = ALL_SPECIES;
        SpeciesDataService.fetchData(url).then(response => {
            this.setState({
                species: response
            });
        })
    }

    deleteSpecies = () => {
        let id = this.state.delete;
        let url = DELETE_SPECIES + id;

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then(response => {
                console.log('Success', JSON.stringify(response));
                this.setState({
                    species: response
                });
                console.log(response);
            })
            .catch(error => console.error('Error:', error));

    }

    handleClose = () => {
        this.setState({
            show: false
        });
    };
    handleShow = (event) => {
        let id = event.target.id;
        this.setState({
            show: true,
            delete: id
        });

    };
    handleDelete = () => {
        this.setState({
            show: false
        });
        this.deleteSpecies();
    }

    render() {
        const props = {
            show: this.state.show,
            handleDelete: this.handleDelete,
            handleClose: this.handleClose,
            handleShow: this.handleShow
        }

        const speciesTable = this.state.species.map((species, index) =>
            <tr key={species.id}>
                <td>{index + 1}</td>
                <td>{species.name}</td>
                <td>{species.habitat}</td>
                <td>{species.kingdom.type}</td>
                <td><Link to={'/edit-species/' + species.id} type="button" className="btn btn-primary">Edit</Link></td>
                <td><button id={species.id} type="button" className="btn btn-primary" onClick={this.handleShow} >Delete</button></td>
            </tr>
        )

        return (
            <div className="text-center">
                <h1>Species </h1>
                <button className="btn btn-success mb-2" onClick={this.getSpecies}>Show</button>
                <table className="table w-75 m-auto">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Habitat</th>
                            <th>Kingdom</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        {speciesTable}
                    </tbody>
                </table>
                <ModalComponent {...props} />
            </div>
        )
    }
}

export default Species;

