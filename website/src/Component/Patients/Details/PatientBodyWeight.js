import React from 'react';
import DatePicker from 'react-datepicker';
import moment from 'moment';
import {Line} from 'react-chartjs-2';
import 'react-datepicker/dist/react-datepicker.css';
import './Styles/PatientBodyWeight.css';


class PatientBodyWeight extends React.Component {
    constructor(props) {
        super(props);

        this.onDateSelected = this.onDateSelected.bind(this);
        this.onChartUpdate = this.onChartUpdate.bind(this);
        this.filterBodyWeights = this.filterBodyWeights.bind(this);

        let bodyWeights = this.extractBodyWeight(this.props.observations);
        let now = moment();
        let lastYear = moment(now);
        lastYear.subtract(25, 'years');

        console.log(bodyWeights);

        this.state = {
            bodyWeights: bodyWeights,
            chartData: {
                labels: [lastYear, now],
                datasets: [{
                    data: []
                }]
            },
            dateFrom: lastYear,
            dateTo: now
        };
    }

    componentDidMount() {
        this.onChartUpdate();
    }

    componentWillReceiveProps(newProps) {
        let bodyWeights = this.extractBodyWeight(this.props.observations);

        this.setState({
            bodyWeights: bodyWeights
        }, this.onChartUpdate);
    }

    onChartUpdate() {
        let chartData = this.state.chartData;
        let bodyWeightsFiltered = this.filterBodyWeights(this.state.bodyWeights);

        console.log(this.bodyWeightsFiltered);

        chartData.labels = bodyWeightsFiltered.map(weight => weight.time);
        chartData.datasets = [{
            label: 'Body weight',
            data: bodyWeightsFiltered.map(weight => weight.weight)
        }];

        this.setState({
            chartData: chartData
        });
    }

    extractBodyWeight(observations) {

        return observations.filter(
            observation => observation.code === this.bodyWeightCode()
        ).map(
            bodyWeight => {
                return {
                    time: moment(bodyWeight.time),
                    weight: bodyWeight.value
                }
            }
        );
    }

    filterBodyWeights(bodyWeights) {
        let filtered = bodyWeights.filter(weight => !(weight.time.isBefore(this.state.dateFrom) || weight.time.isAfter(this.state.dateTo)));

        console.log('Filtered:');
        console.log(filtered);

        return filtered;
    }

    bodyWeightCode() {
        return '29463-7';
    }

    onDateSelected(date, which) {

        if (which === 'to') {
            this.setState({ dateTo: date }, this.onChartUpdate);
        }
        else {
            this.setState({ dateFrom: date }, this.onChartUpdate);
        }
    }

    renderChart(chartData) {
        let chartOptions = {
            scales: {
                xAxes: [{
                    type: 'time',
                    time: {
                        displayFormats: {
                            'millisecond': 'DD-MM-YYYY',
                            'second': 'DD-MM-YYYY',
                            'minute': 'DD-MM-YYYY',
                            'hour': 'DD-MM-YYYY',
                            'day': 'DD-MM-YYYY',
                            'week': 'DD-MM-YYYY',
                            'month': 'DD-MM-YYYY',
                            'quarter': 'DD-MM-YYYY',
                            'year': 'DD-MM-YYYY'
                        }
                    }
                }]
            }
        };

        return (
            <Line data={chartData} options={chartOptions} />
        )
    }

    render() {
        return (
            <div className="PatientBodyWeight">
                <div className="date-range-picker">
                    <label>Select period from:</label>
                    <DatePicker selected={this.state.dateFrom} onChange={date => this.onDateSelected(date, 'from')} />
                    <label>to:</label>
                    <DatePicker selected={this.state.dateTo} onChange={date => this.onDateSelected(date, 'to')} />
                </div>
                <div className="patient-data-chart">
                    { this.renderChart(this.state.chartData) }
                </div>
            </div>
        );
    }
}

export default PatientBodyWeight;