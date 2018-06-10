import React from 'react';
import './Styles/PatientSearcher.css';


class PatientSearcher extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="PatientSearcher">
                <label>Find patients:</label>
                <input id="searchInput" type="text" placeholder="Last Name" />
            </div>
        );
    }
}

export default PatientSearcher;