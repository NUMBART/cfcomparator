$(document).ready(function() {
    $.ajax({
        url: "/problemgraph/rankvstime?contest=1374",
        type: "GET",
        success: function (result) {
            var rank = JSON.parse(result).rank;
            var p1 = JSON.parse(result).p1;
            var p2 = JSON.parse(result).p2;
            var p3 = JSON.parse(result).p3;
            var p4 = JSON.parse(result).p4;
            var p5 = JSON.parse(result).p5;
            var p6 = JSON.parse(result).p6;
            var p7 = JSON.parse(result).p7;
            var p8 = JSON.parse(result).p8;
            var p9 = JSON.parse(result).p9;
            var p10 = JSON.parse(result).p10;
            var names = JSON.parse(result).names;
            console.log(p1[0]);
            drawLineChart(rank, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, names);
        }
    });


    function drawLineChart(rank, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, names) {
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
                name: names[0],
                data: p1
            },{
                name: names[1],
                data: p2
            },{
                name: names[2],
                data: p3
            },{
                name: names[3],
                data: p4
            },{
                name: names[4],
                data: p5
            },{
                name: names[5],
                data: p6
            },{
                name: names[6],
                data: p7
            },{
                name: names[7],
                data: p8
            },{
                name: names[8],
                data: p9
            },{
                name: names[9],
                data: p10
            }]
        });
    }
});