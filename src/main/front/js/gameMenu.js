var audio = document.getElementById('click-button-audio');
function showBattleSeaGamePage() {
    document.getElementById("allGameMenu").style.display = "none";
    document.getElementById("seaBattleGamePage").style.display = "block";
    gameMenuInit('battle');
}

function showDotsAndBoxesGamePage() {
    document.getElementById("allGameMenu").style.display = "none";
    document.getElementById("dotsAndBoxesGamePage").style.display = "block";
    gameMenuInit('dots');
}

function showSeaBattleGamesDiv() {
    audio.play();
    document.getElementById("SB-HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-LeaderboardRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-GamesRibbon").style.backgroundColor = "#bb2d44";

    document.getElementById("SB-HowToPlayDiv").style.display = "none";
    document.getElementById("SB-leaderboardDiv").style.display = "none";
    document.getElementById("SB-historyDiv").style.display = "none";
    document.getElementById("SB-GamesDiv").style.display = "flex";
}

function showSeaBattleHistoryDiv() {
    audio.play();
    document.getElementById("SB-GamesRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-HistoryRibbon").style.backgroundColor = "#bb2d44";
    document.getElementById("SB-HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("SB-GamesDiv").style.display = "none";
    document.getElementById("SB-HowToPlayDiv").style.display = "none";
    document.getElementById("SB-leaderboardDiv").style.display = "none";
    document.getElementById("SB-historyDiv").style.display = "flex";
}

function showSeaBattleLeaderboardDiv() {
    audio.play();
    document.getElementById("SB-GamesRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-LeaderboardRibbon").style.backgroundColor = "#bb2d44";

    document.getElementById("SB-GamesDiv").style.display = "none";
    document.getElementById("SB-HowToPlayDiv").style.display = "none";
    document.getElementById("SB-historyDiv").style.display = "none";
    document.getElementById("SB-leaderboardDiv").style.display = "flex";
}

function showSeaBattleHowToPlayDiv() {
    audio.play();
    document.getElementById("SB-GamesRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-HowToPlayRibbon").style.backgroundColor = "#bb2d44";
    document.getElementById("SB-LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("SB-GamesDiv").style.display = "none";
    document.getElementById("SB-historyDiv").style.display = "none";
    document.getElementById("SB-leaderboardDiv").style.display = "none";
    document.getElementById("SB-HowToPlayDiv").style.display = "flex";
}

function showDotsAndBoxesGamesDiv() {
    audio.play();
    document.getElementById("GamesRibbon").style.backgroundColor = "#bb2d44";
    document.getElementById("HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("DB-HowToPlayDiv").style.display = "none";
    document.getElementById("DB-leaderboardDiv").style.display = "none";
    document.getElementById("DB-historyDiv").style.display = "none";
    document.getElementById("DB-GamesDiv").style.display = "flex"
}

function showDotsAndBoxesHistoryDiv() {
    audio.play();
    document.getElementById("GamesRibbon").style.backgroundColor = "#122948";
    document.getElementById("HistoryRibbon").style.backgroundColor = "#bb2d44";
    document.getElementById("HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("DB-GamesDiv").style.display = "none";
    document.getElementById("DB-HowToPlayDiv").style.display = "none";
    document.getElementById("DB-leaderboardDiv").style.display = "none";
    document.getElementById("DB-historyDiv").style.display = "flex";
}

function showDotsAndBoxesLeaderboardDiv() {
    audio.play();
    document.getElementById("GamesRibbon").style.backgroundColor = "#122948";
    document.getElementById("HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("LeaderboardRibbon").style.backgroundColor = "#bb2d44";

    document.getElementById("DB-GamesDiv").style.display = "none";
    document.getElementById("DB-HowToPlayDiv").style.display = "none";
    document.getElementById("DB-historyDiv").style.display = "none";
    document.getElementById("DB-leaderboardDiv").style.display = "flex";
}

function showDotsAndBoxesHowToPlay() {
    audio.play();
    document.getElementById("GamesRibbon").style.backgroundColor = "#122948";
    document.getElementById("HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("HowToPlayRibbon").style.backgroundColor = "#bb2d44";
    document.getElementById("LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("DB-GamesDiv").style.display = "none";
    document.getElementById("DB-historyDiv").style.display = "none";
    document.getElementById("DB-leaderboardDiv").style.display = "none";
    document.getElementById("DB-HowToPlayDiv").style.display = "flex";
}

function gameMenuInit(game) {
    let gameName, historyList, leaderBoardList, image, userInfo, userLevel;
    if (game === 'battle') {
        gameName = 'See Battle';
        historyList = document.querySelector('#SB-historyDiv');
        leaderBoardList = document.querySelector('#SB-leaderboardDiv');
        image = 'jpg/battleship.png';
        userInfo = document.querySelector('#SB-userInfo');
        userLevel = document.querySelector('#SB-userLevel');
    } else {
        gameName = 'Dots And Boxes';
        historyList = document.querySelector('#DB-historyDiv');
        leaderBoardList = document.querySelector('#DB-leaderboardDiv');
        image = 'jpg/com.dot.box.game-logo.png';
        userInfo = document.querySelector('#DB-userInfo');
        userLevel = document.querySelector('#DB-userLevel');

    }

    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send("game " + game + " gamelogs " + username + " " + token);
    };
    connection.onmessage = function (e) {
        let user = e.data.split("@")[0].split(' ');
        let leaderBoardUsers = e.data.split("@")[1].split("/");
        if (e.data.split("@").length > 2) {
            let gameLogs = e.data.split("@")[2].split("/");

            historyList.innerHTML = ' ';
            for (let i = 1; i < gameLogs.length; i++)
                historyList.insertAdjacentHTML('beforeend',
                    '  <div class="game-history">\n' +
                    '                            <div class="game-icon">\n' +
                    '                                <img src="' + image + '">\n' +
                    '                            </div>\n' +
                    '                            <div class="history-info">\n' +
                    '                                <span> ' + gameName + '</span>\n' +
                    '                                <div>\n' +
                    '                                    <div class="opponent-name">' + gameLogs[i].split('-')[0] + '</div>\n' +
                    '                                    <div class="game-result">&nbsp; Won!</div>\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                            <div class="game-time">' + gameLogs[i].split('-')[1] + '</div>\n' +
                    '                        </div>');

        }
        leaderBoardList.innerHTML = ' ';
        for (let i = 0; i < leaderBoardUsers.length; i++) {

            leaderBoardList.insertAdjacentHTML('beforeend',
                '<div class="player-score">\n' +
                '                            <div class="rank"><span>' + String(i + 1) + '- </span></div>\n' +
                '                            <div class="player-avatar-rank">\n' +
                '                                <img src="avatar/avatar (' + Math.floor(Math.random() * 50 + 1) + ').svg">\n' +
                '                                <span>' + leaderBoardUsers[i].split(' ')[0] + '</span>\n' +
                '                            </div>\n' +
                '                            <div class="score"><span>' + leaderBoardUsers[i].split(' ')[1] + '</span></div>\n' +
                '                        </div>');

        }

        userInfo.innerHTML = '<div>\n' +
            '                                    <span>Won:</span>\n' +
            '                                    <span>' + user[1] + '</span>\n' +
            '                                </div>\n' +
            '                                <div>\n' +
            '                                    <span>Lost:</span>\n' +
            '                                    <span>' + user[2] + '</span>\n' +
            '                                </div>';
        userLevel.innerHTML = '<span>' + user[0] + '</span>';

        connection.close();
    }
}


navigation[3].addEventListener('click', function () {
    navigation[1].classList.remove('show');
    navigation[2].classList.remove('show');
    navigation[3].classList.remove('show');
    navigation[5].classList.remove('show');
    navigation[6].classList.add('show');

    document.getElementById("allGameMenu").style.display = "block";
    document.getElementById("seaBattleGamePage").style.display = "none";
    document.getElementById("dotsAndBoxesGamePage").style.display = "none";

    next_page('primary', 'games-menu');
});


