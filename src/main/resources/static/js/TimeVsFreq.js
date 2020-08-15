$(document).ready(function() {
    $.ajax({
        url: "/problemgraph/timevsfreq?contest=1374&problem=C",
        type: "GET",
        success: function (result) {
            var bestSubmissionTimeSeconds = JSON.parse(result).x;
            var countOfUsersWithSameTime = JSON.parse(result).y;
            console.log("hi");
            drawLineChart(bestSubmissionTimeSeconds, countOfUsersWithSameTime);
        }
    });

    function drawLineChart(bestSubmissionTimeSeconds, countOfUsersWithSameTime) {
        Highcharts.chart('container', {
            chart: {
                zoomType: 'x',
                type: 'line',
                width: 700
            },
            title: {
                text: 'Line Chart'
            },
            xAxis: {
                categories: bestSubmissionTimeSeconds,
                title: {
                    enabled: true,
                    text: "Best Submission Time of Participant"
                }
            },
            yAxis: {
                title: {
                    enabled: true,
                    text: "Number Of Users"
                },
                labels: {
                    format: '{value} s'
                }
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
});