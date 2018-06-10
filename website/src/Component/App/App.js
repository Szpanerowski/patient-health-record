import React, { Component } from 'react';
import PatientRecords from '../Patients/Records/PatientRecords';
import PatientDetails from '../Patients/Details/PatientDetails';
import './Styles/App.css';

class App extends Component {
  constructor(props) {
    super(props);

    this.state = {
      onPatientSelected: null
    }
  }

  render() {
    return (
      <div className="App">
        <header>
          <h1>Patient Health Record</h1>
        </header>
        <main>
          <div className="patient-details">
            <PatientDetails />
          </div>
          <div className="patient-records">
            <PatientRecords />
          </div>
        </main>
      </div>
    );
  }
}

export default App;
