angular.module('SpringMusic', ['albums', 'errors', 'status', 'info', 'ngRoute', 'ui.directives', 'ngSanitize']).
    config(function ($locationProvider, $routeProvider) {
        // $locationProvider.html5Mode(true);

        $routeProvider.when('/errors', {
            controller: 'ErrorsController',
            templateUrl: 'templates/errors.html'
        });
        $routeProvider.otherwise({
            controller: 'AlbumsController',
            templateUrl: 'templates/albums.html'
        });
    }
);
