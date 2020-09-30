$(document).ready(function() {
    $.ajax({
        url: "/problemgraph/rankvstime?contest=1374",
        type: "GET",
        success: function (result) {
            var rank = JSON.parse(result).rank;
            var seriesData = JSON.parse(result).seriesData;
            drawLineChart(rank, seriesData);
        }
    });


    function drawLineChart(rank, seriesData) {
        Highcharts.chart('container', {
            chart: {
                zoomType: 'x',
                inverted:true,
                type: 'spline',
                width: 700
            },
            title: {
                text: 'Rank of Participant vs Submission Time'
            },
            xAxis: {
                categories: rank,
                title: {
                    enabled: true,
                    text: "Rank of Participant"
                },
                reversed: true
            },
            yAxis: {
                title: {
                    enabled: true,
                    text: "Best Submission Time of Participant"
                },
                labels: {
                    format: '{value} s'
                }
            },
            tooltip: {
                formatter: function () {
                    return '<strong>' + this.x + ': <strong>' + Math.round(this.y/60) + ' m ' + Math.round(this.y%60) + ' s';
                }
            },
            series: seriesData
        });
    }
});