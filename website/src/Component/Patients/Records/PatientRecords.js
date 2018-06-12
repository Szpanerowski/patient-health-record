import React from 'react';
import PatientSearcher from '../../Searcher/PatientSearcher';
import PatientsList from './PatientsList';
import $ from 'jquery';
import './Styles/PatientRecords.css';

class PatientRecords extends React.Component {
    constructor(props) {
        super(props);

        this.onPatientsLoaded = this.onPatientsLoaded.bind(this);
        this.onFilterChanged = this.onFilterChanged.bind(this);

        this.state = {
            patients: [],
            filteredPatients: [],
            filter: null
        };
    }

    componentDidMount() {
        this.loadPatients();
    }

    loadPatients() {
        const serverUrl = 'http://localhost:8090/health/patients/';
        $.ajax({
            url: serverUrl,
            success: this.onPatientsLoaded
        });
    }
    
    filterPatients(patients, filter) {
        if (filter == null) {
            return patients;
        }
        
        console.log()
        console.log(filter);
        console.log(`Filtering patients with last name not like "${filter.lastName}"...`);

        let filteredPatients = patients;
        if (filter != null) {
            
            filteredPatients = patients.filter(
                patient => patient.lastName.toLowerCase().startsWith(this.state.filter.lastName.toLowerCase())
            );
        }

        console.log(`Filtered ${patients.length - filteredPatients.length} patients.`);

        return filteredPatients;
    }

    onPatientsLoaded(loadedPatients) {
        console.log('Patients loaded!');
        console.log(loadedPatients);

        this.setState({
            patients: loadedPatients,
            filteredPatients: this.filterPatients(loadedPatients, this.state.filter)
        });
    }

    onFilterChanged(filter) {
        this.setState({
            filteredPatients: this.filterPatients(this.state.patients, filter),
            filter: filter
        });
    }

    render() {
        return (
            <div className="PatientRecords">
                <PatientSearcher onFilterChanged={this.onFilterChanged} />
                <PatientsList patients={this.state.filteredPatients} onSelect={this.props.onPatientSelected} />
            </div>
        );
    }
}

export default PatientRecords;