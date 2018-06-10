import React from 'react';
import './Styles/PatientListItem.css';

class PatientListItem extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            patient: this.props.patient
        }
    }

    render() {
        return (
            <li className="PatientListItem">
                <p>First name: {this.state.patient.firstName}</p>
                <p>Last name: {this.state.patient.lastName}</p>
                <p>Gender: {this.state.patient.gender}</p>
            </li>
        );
    }
}

export default PatientListItem;