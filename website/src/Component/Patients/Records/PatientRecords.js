import React from 'react';
import PatientSearcher from '../../Searcher/PatientSearcher';
import PatientsList from './PatientsList';
import './Styles/PatientRecords.css';

class PatientRecords extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            patients: []
        }
    }

    render() {
        return (
            <div className="PatientRecords">
                <PatientSearcher />
                <PatientsList patients={this.state.patients} />
            </div>
        );
    }
}

export default PatientRecords;