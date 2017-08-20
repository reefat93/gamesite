(function () {
    
angular.module('gamesiteApp').service('gameService', ['$http', GameService]);
    
function GameService($http) {
    
    var gameApiPath = "rest/gameAPI"
    
    this.getAllGames = function() {
        return $http.get(gameApiPath + "/game");
    }
}

}());