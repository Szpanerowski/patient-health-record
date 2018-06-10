import React from 'react';
import PatientListItem from './PatientListItem';
import './Styles/PatientsList.css';

class PatientsList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            patients: [],
            lastName: this.props.lastName
        };
    }

    componentWillMount() {
        this.setState({
            patients: this.props.patients.filter(
                patient => patient.lastName.startsWith(this.props.lastName)
            )
            .map(patient => {
                return <PatientListItem patient={patient} onSelect={this.props.onSelect(patient.id)} />;
            })
        });
    }

    render() {
        return (
            <ul>
                {this.state.patients}
            </ul>
        );
    }
}

export default PatientsList;