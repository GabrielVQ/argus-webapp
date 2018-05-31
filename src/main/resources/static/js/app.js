var app = angular.module('app', ['ngRoute']);


app.config(function($routeProvider){
    $routeProvider
        .when('/home', {
            templateUrl: 'js/views/index.html',
            controller: 'MainController'
        })
        .when('/BL-Master', {
            templateUrl: 'js/views/BL-Master.html',
            controller: 'BLMasterController'
        })
        .when('/BL-House',{
            templateUrl: 'js/views/BL-House.html',
            controller: 'BLHouseController'
        })
        .when('/buscarBLMaster',{
            templateUrl: 'js/views/buscarBLMaster.html',
            controller: 'buscarBLMasterController'
        })
        .when('/editarBLMaster/:bl',{
            templateUrl: 'js/views/editarBLMaster.html',
            controller: 'editarBLMasterController'
        })
        .when('/descripcion',{
            templateUrl: 'js/views/descripcion.html',
            controller: 'BLHouseController'
        })
        .when('/ingresos',{
            templateUrl: 'js/views/ingresos.html',
            controller: 'BLHouseController'
        })
        .when('/',{
            templateUrl: 'js/views/login.html',
            controller: 'loginController'
        })
                .otherwise({
            redirectTo: '/'
        });
});

app.service('ConsultaService', function($http,$q){
    var urlBase = 'http://localhost:8080/blmasters';

    this.getBLMaster = function(numeroOperacion){
        return $http.get(urlBase +"/numeroOperacion/" +numeroOperacion);
    };

});


app.directive("digitalClock", function($timeout, dateFilter) {
    return {
        restrict: 'E',
        link: function(scope, iElement) {
            (function updateClock() {
                iElement.text(dateFilter(new Date(), 'hh:mm:ss a'));
                $timeout(updateClock, 1000);
            })();
        }
    };
});


