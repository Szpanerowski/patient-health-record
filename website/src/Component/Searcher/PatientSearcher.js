import React from 'react';
import $ from 'jquery';
import './Styles/PatientSearcher.css';


class PatientSearcher extends React.Component {
    constructor(props) {
        super(props);

        this.updateLastName = this.updateLastName.bind(this);
        this.onChange = this.onChange.bind(this);

        this.state = {
            filter: {
                lastName: ''
            }
        }
    }

    updateLastName() {

        let newLastName = $("#searchInput")[0].value;
        if (newLastName == null) {
            newLastName = '';
        }
        
        let newFilter = this.state.filter;
        newFilter.lastName = newLastName;

        this.setState({
            filter: newFilter
        }, () => this.props.onFilterChanged(this.state.filter));

        this.props.onFilterChanged(this.state.filter);
    }

    componentDidMount() {
        this.updateLastName();
    }

    onChange() {
        this.updateLastName();
    }

    render() {
        return (
            <div className="PatientSearcher">
                <label>Find patients:</label>
                <input id="searchInput" type="text" placeholder="Last Name" onChange={this.onChange} />
            </div>
        );
    }
}

export default PatientSearcher;