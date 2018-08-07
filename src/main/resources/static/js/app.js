var app = angular.module('app', ['ngRoute']);

app.service('servicioNumeroBL', function () {
    /*
    this.numeroBL= -1;
    this.numeroBL2 = function() {
        this.ultimo;
        $http.get('http://localhost:8080/blmasters').then(function (response) {
            this.ultimo = response.data;
            //console.log($scope.BLMaster[0].numeroOperacion);
            //console.log($scope.BLMaster.pop().numeroOperacion);
            //this.numeroBL = (this.ultimo.pop().numeroOperacion + 1);
            //$scope.numeroOperacion = servicioNumeroBL.numeroBL;
            console.log('data1:', this.ultimo);
            return this.ultimo;
        })
        return this.ultimo;
    }
    //console.log('data2:', this.numeroBL2());
    */
    this.numeroBL = -1;
    this.setNumeroBLMaster = function (numeroBL) {
        this.numeroBL = numeroBL;
    }
    //return this.numeroBL2;
});

app.service('servicioNumeroBLHouse', function () {

    this.numeroBLHouse = 1;
    this.setNumeroBLHouse = function (numeroBLHouse) {
        this.numeroBLHouse = numeroBLHouse;
    }
    //return this.numeroBL2;
});
app.config(function($routeProvider){
    $routeProvider
        .when('/home/:user_id', {
            templateUrl: 'js/views/index.html',
            controller: 'MainController'
        })
        .when('/BL-Master', {
            templateUrl: 'js/views/BL-Master.html',
            controller: 'BLMasterController'
        })
        .when('/BL-House/:bl',{
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
        .when('/editarBLHouse/:bl/:blhouse',{
            templateUrl: 'js/views/editarBLHouse.html',
            controller: 'editarBLHouseController'
        })
        .when('/descripcion',{
            templateUrl: 'js/views/descripcion.html',
            controller: 'descripcionController'
        })
        .when('/ingresos',{
            templateUrl: 'js/views/ingresos.html',
            controller: 'ingresosController'
        })
        .when('/agregarCiudad',{
            templateUrl: 'js/views/agregarCiudad.html',
            controller: 'agregarCiudadController'
        })
        .when('/agregarCobro',{
            templateUrl: 'js/views/agregarCobro.html',
            controller: 'agregarCobroController'
        })
        .when('/agregarEmpresa',{
            templateUrl: 'js/views/agregarEmpresa.html',
            controller: 'agregarEmpresaController'
        })
        .when('/agregarNave',{
            templateUrl: 'js/views/agregarNave.html',
            controller: 'agregarNaveController'
        })
        .when('/agregarNaviera',{
            templateUrl: 'js/views/agregarNaviera.html',
            controller: 'agregarNavieraController'
        })
        .when('/agregarPuerto',{
            templateUrl: 'js/views/agregarPuerto.html',
            controller: 'agregarPuertoController'
        })
        .when('/partnerHome/:user_id',{
            templateUrl: 'js/views/vistaPartner.html',
            controller: 'partnerController'
        })
        .when('/partnerBuscar',{
            templateUrl: 'js/views/vistaPartnerbuscar.html',
            controller: 'partnerController'
        })
        .when('/partnerEditar/:bl',{
            templateUrl: 'js/views/vistaPartnerEditarBLMaster.html',
            controller: 'partnerControllerEditar'
        })
        .when('/login',{
            templateUrl: 'js/views/login.html',
            controller: 'loginController'
        })
        .when('/buscarBLHouse',{
            templateUrl: 'js/views/buscarBLHouse.html',
            controller: 'buscarBLHouseController'
        })
                .otherwise({
            redirectTo: '/login'
        });
});

app.service('ConsultaService', function($http,$q){
    var urlBase = 'http://localhost:8080/blmasters';

    this.getBLMaster = function(numeroOperacion){
        return $http.get(urlBase +"/numeroOperacion/" +numeroOperacion);
    };

});
/*
app.service('BLMasterService', function ($http) {
     $http.get('http://localhost:8080/blmasters').then(function(response){
         this.ultimo = response.data;
        //console.log($scope.BLMaster[0].numeroOperacion);
        //console.log($scope.BLMaster.pop().numeroOperacion);
        //servicioNumeroBL.setNumeroBLMaster($scope.BLMaster.pop().numeroOperacion + 1);
        //$scope.numeroOperacion = servicioNumeroBL.numeroBL;
        console.log('data:', this.ultimo);
    })
    return this.ultimo
});
*/
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


