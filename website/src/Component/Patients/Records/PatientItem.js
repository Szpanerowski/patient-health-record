import React from 'react';
import './Styles/PatientItem.css';

class PatientItem extends React.Component {
    onSelect(callback, patientId) {

        if (callback) {
            callback(patientId);
        }
    }

    render() {
        return (
            <li className="PatientItem" onClick={() => this.onSelect(this.props.onSelect, this.props.patient.id)}>
                <table>
                    <tbody>
                        <tr>
                            <td className="info-label">Name:</td>
                            <td className="bold-value">{this.props.patient.firstName} {this.props.patient.lastName}</td>
                        </tr>
                        <tr>
                            <td className="info-label">Birth date:</td>
                            <td className="bold-value">{this.props.patient.birthDate}</td>
                        </tr>
                        <tr>
                            <td className="info-label">Gender:</td>
                            <td className="bold-value">{this.props.patient.gender}</td>
                        </tr>
                    </tbody>
                </table>
            </li>
        );
    }
}

export default PatientItem;