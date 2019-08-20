import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';

import SpeciesDataService from '../services/SpeciesDataService';
import { SAVE_SPECIES, EDIT_SPECIES, CONSERVATION_STATUSES, KINGDOMS } from '../constants/UrlConstants';

class SpeciesForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            kingdoms: [],
            conservationStatuses: [],
            name: '',
            habitat: '',
            kingdom: '',
            conservationStatus: '',
        }
    }

    componentDidMount() {
        this.getConservationStatuses();
        this.getKingdoms();
        let id = this.props.match.params.id;
        if (id != undefined) {
            this.getSpecies(id);
        }
    }

    getSpecies = (id) => {
        let url = EDIT_SPECIES + id;
        SpeciesDataService.fetchData(url).then(response => {
            let species = response;
            this.setState({
                name: species.name,
                habitat: species.habitat,
                kingdom: species.kingdom.type,
                conservationStatus: species.conservationStatus.status
            });
        })
    }

    getConservationStatuses = () => {
        let url = CONSERVATION_STATUSES;
        SpeciesDataService.fetchData(url).then(response => {
            console.log(response);
            this.setState({
                conservationStatuses: response
            });
        })
    }

    getKingdoms = () => {
        let url = KINGDOMS;
        SpeciesDataService.fetchData(url).then(response => {
            console.log(response);
            this.setState({
                kingdoms: response,
            });
        });
    }

    parseToEnum = (value) => {
        if (value != null) {
            let enumValue = value.replace(/ /g, '_').toUpperCase();
            return enumValue;
        }
        return null;
    }

    validate(values) {
        let errors = {}
        if (values.name.length < 3) {
            errors.name = 'Name length has to be atleast 3 characters';
        } else if (values.habitat.length < 3) {
            errors.habitat = 'Habitat length has to be atleast 3 characters';
        } else if (values.kingdom == '') {
            errors.kingdom = 'Please choose kingdom';
        } else if (values.conservationStatus == '') {
            errors.conservationStatus = 'Please choose conservation status';
        }

        return errors
    }

    handleSubmit = (values) => {

        let species = {
            name: values.name,
            habitat: values.habitat,
            kingdom: this.parseToEnum(values.kingdom),
            conservationStatus: this.parseToEnum(values.conservationStatus)
        }

        let url = SAVE_SPECIES;

        let id = this.props.match.params.id;
        if (id != undefined) {
            species.id = id;
            url = EDIT_SPECIES + id;
        }
        fetch(url, {
            method: 'POST',
            body: JSON.stringify(species),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
            .then(response => {
                console.log('Success', JSON.stringify(response));
            })
            .catch(error => console.error('Error:', error));
        console.log(values);
        document.getElementById('species-form').reset();


    }

    render() {
        let { name, habitat, kingdom, conservationStatus } = this.state;

        return (
            <div>
                <h3 style={{ textAlign: 'center' }}>Species</h3>
                <div className="container">
                    <Formik
                        initialValues={{ name, habitat, kingdom, conservationStatus }}
                        onSubmit={this.handleSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form id="species-form">
                                    <div className="form-group">
                                        <label htmlFor="name">Name</label>
                                        <Field type="text" name="name" className="form-control" id="name" aria-describedby="name" placeholder="Enter name" />                         </div>
                                    <ErrorMessage name="name" component="div"
                                        style={{ color: 'red' }} />
                                    <div className="form-group">

                                        <label htmlFor="habitat">Habitat</label>
                                        <Field type="text" name="habitat" className="form-control" id="habitat" aria-describedby="habitat" placeholder="Enter habitat" />
                                        <ErrorMessage name="habitat" component="div"
                                            style={{ color: 'red' }} />
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="selectKingdom">Choose kingdom</label>
                                        <Field component="select" name="kingdom" className="form-control" id="selectKingdom">
                                            <option>--Select--</option>
                                            {this.state.kingdoms.map(kingdom =>
                                                <option value={kingdom.type} key={kingdom.type}>{kingdom.type}</option>
                                            )}
                                        </Field>
                                        <ErrorMessage name="kingdom" component="div"
                                            style={{ color: 'red' }} />
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="selectConservationStatus">Choose conservation status</label>
                                        <Field component="select" name="conservationStatus" className="form-control" id="selectConservationStatus">
                                            <option>--Select--</option>
                                            {this.state.conservationStatuses.map(conservationStatus =>
                                                <option value={conservationStatus.status} key={conservationStatus.status}>{conservationStatus.status}</option>
                                            )}
                                        </Field>
                                        <ErrorMessage name="conservationStatus" component="div"
                                            style={{ color: 'red' }} />
                                    </div>
                                    <button className="btn btn-success" type="submit">Save</button>
                                </Form>
                            )
                        }
                    </Formik>
                </div>
            </div>
        )
    }
}

export default SpeciesForm;