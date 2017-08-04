var gamesiteApp = angular.module('gamesiteApp', ['ui.router']);
    
gamesiteApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

        // For any unmatched url, redirect to /
        $urlRouterProvider.otherwise("/home");

        $stateProvider.state("home", {
            url: "/home",
            templateUrl: "app/features/home/home.html"
        }).state("game", {
            url: "/game",
            templateUrl: "app/features/game/game.html"
        });
    }
]);
