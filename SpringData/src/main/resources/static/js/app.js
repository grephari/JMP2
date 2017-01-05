var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/',{
            templateUrl: '/views/accident.html',
            controller: 'accident'
        })
        .when('/user',{
            templateUrl: '/views/accidentUserData.html',
            controller: 'accident'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});


