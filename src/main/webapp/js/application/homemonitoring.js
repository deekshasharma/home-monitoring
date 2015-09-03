angular.module('homemonitoring', ['chart.js'])

.controller('AlertsController', function($scope, $http, $timeout) {
    $scope.temperatureAlertReceived = false;
    $scope.soundAlertReceived = false;

    // temperature alert
    $scope.getTemperatureAlert = function() {
    // alert api url
//     var url = 'http://localhost:8080/homemonitoring/rest/alert/temperature'
     var url = '/homemonitoring/rest/alert/temperature'
     var data = {};
      $http.get(url).then(function(r){
         console.log('Alert Temp data: ' + r.data)
         $scope.temperatureAlertReceived = r.data;
         // hide alert anyway
         $timeout(function(){$scope.temperatureAlertReceived = false}, 5000);

         $timeout($scope.getTemperatureAlert, 5000);
     });
    }
    $scope.getTemperatureAlert();

    // sound alert
    $scope.getSoundAlert = function() {
    // alert api url
//     var url = 'http://localhost:8080/homemonitoring/rest/alert/sound'
     var url = '/homemonitoring/rest/alert/sound'
     var data = {};
      $http.get(url).then(function(r){
         console.log('Alert Sound data: ' + r.data)
         $scope.soundAlertReceived = r.data;
         // hide alert anyway
         $timeout(function(){$scope.soundAlertReceived = false}, 5000);

         $timeout($scope.getSoundAlert, 5000);
     });
    }
    $scope.getSoundAlert();

})

.controller('GraphsController', function($scope, $http, $timeout){
    // temperature controller
    $scope.temperatureGraph = function(data) {
        $scope.labels = data.dateTimes;
          $scope.temperatureSeries = ['Temperature'];
          $scope.temperatures = [data.readings];
          $scope.onClick = function (points, evt) {
            console.log(points, evt);
          };
    }

    $scope.getTemperatures = function() {
//        var url = 'http://localhost:8080/homemonitoring/rest/graph/temperature'
        var url = '/homemonitoring/rest/graph/temperature'
        var data = {};
         $http.get(url).then(function(r){
                    data = extractTemperatureReadings(r.data);
                    $scope.temperatureGraph(data);
                    $timeout($scope.getTemperatures, 5000);
                });
    }
    $scope.getTemperatures();

    // sound controller
    $scope.soundGraph = function(data) {
        $scope.labels = data.dateTimes;
          $scope.soundSeries = ['Sound'];
          $scope.sounds = [data.readings];
          $scope.onClick = function (points, evt) {
            console.log(points, evt);
          };
    }

    $scope.getSounds = function() {
         // this should come from sound api
//         var url = 'http://localhost:8080/homemonitoring/rest/graph/sound'
         var url = '/homemonitoring/rest/graph/sound'
         var data = {};
         $http.get(url).then(function(r){
                    data = extractTemperatureReadings(r.data);
                    $scope.soundGraph(data);
                    $timeout($scope.getSounds, 5000);
                });
    }
    $scope.getSounds();
})

function extractTemperatureReadings(apiResponse) {
    var readings = {}
    readings['readings'] = apiResponse.map(function(value){return value.reading})
    readings['dateTimes'] = apiResponse.map(function(value){return value.dateTime.substring(0,19)})

    return readings
}