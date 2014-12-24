
$(document).ready(function() {

  console.log("ready");

  var evtSource = new EventSource("/somestream");


  var datapoints = Array();
  var n = 1;

  plot = $.plot("#pippo", [], {
    series: {
      shadowSize: 0
    },
    yaxis: {
      min: 0,
      max: 5
    },
    xaxis: {
      min:0,
      max:50,
      show: true
    }
  });




  evtSource.onmessage = function(e) {
    // $("#pippo").append(e.data);
    // $("#pippo").append('<br>');
    datapoints.push([n, parseFloat(e.data)]);
    plot.setData([datapoints]);
    plot.draw();
    n = n+1;
    console.log(e.data);

  }});
