$.ajax({
    url: 'problemgraph',
    data: {problem: 'problemId', contest: 'contestId'},
    success: function(result){
        var bestSubmissionTimeSeconds = JSON.parse(result).bestSubmissionTimeSeconds;
        var countOfUsersWithSameTime = JSON.parse(result).countOfUsersWithSameTime;
        drawLineChart(bestSubmissionTimeSeconds, countOfUsersWithSameTime);
    }
})

function drawLineChart(bestSubmissionTimeSeconds, countOfUsersWithSameTime) {
    Highcharts.chart('container', {
        chart: {
            type: 'line',
            width: 3000
        },
        title: {
            text: 'Line Chart'
        },
        xAxis: {
            categories: bestSubmissionTimeSeconds
        },
        tooltip: {
            formatter: function () {
                return '<strong>' + this.x + ': <strong>' + this.y;
            }
        },
        series: [{
            data: countOfUsersWithSameTime
        }]
    });
}