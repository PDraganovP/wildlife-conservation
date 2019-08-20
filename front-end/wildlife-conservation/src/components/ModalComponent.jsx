import React from 'react';
import { Modal, Button } from 'react-bootstrap';

class ModalComponent extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <React.Fragment>
                <Modal show={this.props.show} onHide={this.props.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Delete species</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>Are you sure you want to delete this species!</Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.props.handleClose}>
                            Cancel
                </Button>
                        <Button variant="primary" onClick={this.props.handleDelete}>
                            Delete
                </Button>
                    </Modal.Footer>
                </Modal>
            </React.Fragment>
        );
    }
}

export default ModalComponent;