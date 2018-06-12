import React from 'react';
import './Styles/DataTable.css';

class PatientObservations extends React.Component {
    renderObservations(observations) {
        return (
            observations.map(observation => {
                return (
                    <tr key={observation.id}>
                        <td>{observation.description}</td>
                        <td>{observation.time}</td>
                        <td>{observation.code}</td>
                        <td>{observation.value}</td>
                        <td>{observation.comment}</td>
                    </tr>
                )
            })
        );
    }

    render() {
        return (
            <div className="PatientObservations">
                <table className="patient-data-table">
                    <thead>
                        <tr>
                            <td>Description</td>
                            <td>Time</td>
                            <td>Code</td>
                            <td>Value</td>
                            <td>Comment</td>
                        </tr>
                    </thead>
                    <tbody>
                        {this.renderObservations(this.props.observations)}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default PatientObservations;