import React from 'react';
import PatientItem from './PatientItem';
import './Styles/PatientsList.css';

class PatientsList extends React.Component {
    constructor(props) {
        super(props);

        this.renderPatients = this.renderPatients.bind(this);

        this.state = {
            patients: []
        };
    }

    renderPatients(patients) {
        return patients.map(patient =>
            <PatientItem key={patient.id} patient={patient} onSelect={this.props.onSelect} />
        );
    }

    render() {
        return (
            <div className="PatientsList">
                <ul>
                    {this.renderPatients(this.props.patients)}
                </ul>
            </div>
        );
    }
}

export default PatientsList;