import React from 'react';
import './Styles/PatientListItem.css';

class PatientListItem extends React.Component {
    render() {
        return (
            <li className="PatientListItem" onClick={this.props.onSelect}>
                <table>
                    <tbody>
                        <tr>
                            <td>Name:</td>
                            <td>{this.props.patient.firstName} {this.props.patient.lastName}</td>
                        </tr>
                        <tr>
                            <td>Birth date:</td>
                            <td>{this.props.patient.birthDate}</td>
                        </tr>
                        <tr>
                            <td>Gender:</td>
                            <td>{this.props.patient.gender}</td>
                        </tr>
                    </tbody>
                </table>
            </li>
        );
    }
}

export default PatientListItem;