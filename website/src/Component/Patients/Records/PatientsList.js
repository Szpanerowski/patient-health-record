import React from 'react';
import PatientListItem from './PatientListItem';
import './Styles/PatientsList.css';

class PatientsList extends React.Component {
    constructor(props) {
        super(props);

        this.renderPatients = this.renderPatients.bind(this);

        this.state = {
            patients: []
        };
    }

    componentWillReceiveProps(props) {

        console.log('List received patients:');
        console.log(props.patients);
    }

    renderPatients(patients) {
        return patients.map(patient =>
            <PatientListItem key={patient.id} patient={patient} onSelect={this.props.onSelect(patient.id)} />
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