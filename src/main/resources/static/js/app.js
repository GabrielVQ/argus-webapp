var app = angular.module('app', ['ngRoute']);

app.config(function($routeProvider){
    $routeProvider
        .when('/', {
            templateUrl: 'js/views/index.html',
            controller: 'MainController'
        })
        .when('/BL-Master', {
            templateUrl: 'js/views/BL-Master.html',
            controller: 'BLMasterController',
        })
        .when('/BL-House',{
            templateUrl: 'js/views/BL-House.html',
            controller: 'BLHouseController',
        })
        .when('/buscarBLMaster',{
            templateUrl: 'js/views/buscarBLMaster.html',
            controller: 'buscarBLMasterController',
        })
        .when('/editarBLMaster',{
            templateUrl: 'js/views/editarBLMaster.html',
            controller: 'editarBLMasterController',
        })
        .when('/descripcion',{
            templateUrl: 'js/views/descripcion.html',
            controller: 'descripcionController',
        })
        .when('/ingresos',{
            templateUrl: 'js/views/ingresos.html',
            controller: 'BLHouseController',
        })
        .otherwise({
            redirectTo: '/'
        });
});