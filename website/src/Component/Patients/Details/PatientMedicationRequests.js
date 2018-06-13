import React from 'react';


class PatientMedicationRequests extends React.Component {
    renderMedicationRequests(medicationRequests) {
        return (
            medicationRequests.map(medicationRequest => {
                return (
                    <tr key={medicationRequest.id}>
                        <td>{medicationRequest.time}</td>
                        <td>{medicationRequest.description}</td>
                    </tr>
                )
            })
        );
    }

    render() {
        return (
            <div className="PatientMedicationRequests">
                <table className="patient-data-table" border="1">
                    <thead>
                        <tr>
                            <td>Time</td>
                            <td>Description</td>
                        </tr>
                    </thead>
                    <tbody>
                        {this.renderMedicationRequests(this.props.requests)}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default PatientMedicationRequests;