$(document).ready(function() {
    $.ajax({
        url: "/solvesbyprbratings?user=sergei_popov",
        type: "GET",
        success: function (result) {
            var rating = JSON.parse(result).rating;
            var practice = JSON.parse(result).practice;
            var virtual = JSON.parse(result).virtual;
            var contest = JSON.parse(result).contest;
            var outofcomp = JSON.parse(result).outofcomp;

            console.log("hi");
            drawBarChart(rating, practice, virtual, contest, outofcomp);
        }
    });

    function drawBarChart(rating, practice, virtual, contest, outofcomp) {
        Highcharts.chart('container', {
            chart: {
                type: 'column'
            },
            title: {
                text: 'Number of problem solves in different modes vs rating of the problems'
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100
            },
            xAxis: {
                categories: rating,
                title: {
                    enabled: true,
                    text: "Number of solves"
                }
            },
            yAxis: {
                title: {
                    enabled: true,
                    text: "Problem Rating"
                }
            },
            plotOptions: {
                series: {
                    stacking: 'normal',
                    pointPadding: 0,
                    groupPadding: 0,
                    borderWidth: 0.1,
                    shadow: false
                },
            },
            series: [{
                name: 'Practice',
                data: practice
            }, {
                name: 'Virtual',
                data: virtual
            }, {
                name: 'Contest',
                data: contest
            },{
                name: 'Out of Competition',
                data: outofcomp
            }]
        });
    }
});