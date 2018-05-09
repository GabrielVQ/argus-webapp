var app = angular.module('app', ['ngRoute']);

app.config(function($routeProvider){
    $routeProvider
        .when('/', {
            templateUrl: 'js/views/index.html',
            controller: 'MainController'
        })
        .when('/servicios', {
            templateUrl: 'js/views/services.html',
            controller: 'MainController'
        })
        .when('/nosotros', {
            templateUrl: 'js/views/about.html',
            controller: 'MainController'
        })
        .when('/contacto', {
            templateUrl: 'js/views/contact.html',
            controller: 'MainController'
        })
        .when('/BL-Master', {
            templateUrl: 'js/views/BL-Master.html',
            controller: 'BLController',
        })
        .when('/BL-House',{
            templateUrl: 'js/views/BL-House.html',
            controller: 'BLHouseController',
        })
        .when('/buscarBLMaster',{
            templateUrl: 'js/views/buscarBLMaster.html',
            controller: 'buscarBLMaster',
        })
        .otherwise({
            redirectTo: '/'
        });
});