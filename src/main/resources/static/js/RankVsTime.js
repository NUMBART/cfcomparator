$(document).ready(function() {
    $.ajax({
        url: "/problemgraph/rankvstime?contest=1374&problem=C",
        type: "GET",
        success: function (result) {
            var rank = JSON.parse(result).x;
            var time = JSON.parse(result).y;
            console.log("hi");
            drawLineChart(rank, time);
        }
    });


    function drawLineChart(rank, time) {
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
            series: [{
                name: 'Time',
                data: time
            }]
        });
    }
});