import React from 'react';
import PatientObservations from './PatientObservations';
import PatientMedicationRequests from './PatientMedicationRequests';
import PatientBodyWeight from './PatientBodyWeight';
import $ from 'jquery';
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import 'react-tabs/style/react-tabs.css';
import './Styles/PatientDetails.css';

class PatientDetails extends React.Component {
    constructor(props) {
        super(props);

        this.onPatientLoaded = this.onPatientLoaded.bind(this);
        this.onPatientRefresh = this.onPatientRefresh.bind(this);

        this.state = {
            patient: null
        }
    }

    componentDidMount() {
        this.onPatientRefresh(this.props.patientId);
    }

    componentWillReceiveProps(newProps) {
        this.onPatientRefresh(newProps.patientId);
    }

    onPatientRefresh(patientId) {
        if (patientId != null) {
            this.setState({
                patient: null
            });

            const patientUrl = 'http://localhost:8090/health/patient/' + patientId;
            $.ajax({
                url: patientUrl,
                success: this.onPatientLoaded
            });
        }
    }

    onPatientLoaded(patient) {
        this.setState({
            patient: patient
        });
    }

    renderAddress(address) {
        let addressLines = address.lines.split('\n').map(
            (line, no) => <span key={no}>{line}<br /></span>
        );

        return (
            <div>
                {addressLines}
                <span>{address.postalCode}, {address.city}</span>
            </div>
        );
    }

    render() {
        if (this.state.patient == null) {
            return (
                <div className="PatientDetails no-selection">
                    <h2>{this.props.patientId == null ? 'Select patient from the list...' : 'Loading...'}</h2>
                </div>
            );
        }
        return (
            <div className="PatientDetails">
                <div className="general-details">
                    <div className="gender-details">
                        <img src={'/' + (this.state.patient.gender === 'Female' ? 'female-patient.png' : 'male-patient.png')} alt="Patient" />
                    </div>
                    <div className="personal-details">
                        <h2>{this.state.patient.firstName + ' ' + this.state.patient.lastName}</h2>
                        <h3>Born {this.state.patient.birthDate}</h3>
                        <h4>Address:</h4>
                        {this.renderAddress(this.state.patient.address)}
                    </div>
                </div>
                <div className="health-info">
                    <Tabs>
                        <TabList>
                            <Tab>Observations</Tab>
                            <Tab>Medication requests</Tab>
                            <Tab>Body weight</Tab>
                        </TabList>
                        <TabPanel className="health-info-tab">
                            <PatientObservations observations={this.state.patient.observations} />
                        </TabPanel>
                        <TabPanel className="health-info-tab">
                            <PatientMedicationRequests requests={this.state.patient.medicationRequests} />
                        </TabPanel>
                        <TabPanel className="health-info-tab">
                            <PatientBodyWeight observations={this.state.patient.observations} />
                        </TabPanel>
                    </Tabs>
                </div>
            </div>
        );
    }
}

export default PatientDetails;