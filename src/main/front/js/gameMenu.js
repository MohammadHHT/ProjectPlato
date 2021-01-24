function showBattleSeaGamePage() {
    document.getElementById("allGameMenu").style.display = "none";
    document.getElementById("seaBattleGamePage").style.display = "block";
}

function showDotsAndBoxesGamePage() {
    document.getElementById("allGameMenu").style.display = "none";
    document.getElementById("dotsAndBoxesGamePage").style.display = "block";
}

function showSeaBattleHistoryDiv() {
    document.getElementById("SB-HistoryRibbon").style.backgroundColor = "#bb2d44";
    document.getElementById("SB-HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("SB-HowToPlayDiv").style.display = "none"
    document.getElementById("SB-leaderboardDiv").style.display = "none";
    document.getElementById("SB-historyDiv").style.display = "flex";
}

function showSeaBattleLeaderboardDiv() {
    document.getElementById("SB-HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-LeaderboardRibbon").style.backgroundColor = "#bb2d44";

    document.getElementById("SB-HowToPlayDiv").style.display = "none"
    document.getElementById("SB-historyDiv").style.display = "none";
    document.getElementById("SB-leaderboardDiv").style.display = "flex";
}

function showSeaBattleHowToPlayDiv() {
    document.getElementById("SB-HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-HowToPlayRibbon").style.backgroundColor = "#bb2d44";
    document.getElementById("SB-LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("SB-historyDiv").style.display = "none";
    document.getElementById("SB-leaderboardDiv").style.display = "none";
    document.getElementById("SB-HowToPlayDiv").style.display = "flex"
}

function showDotsAndBoxesHistoryDiv() {
    document.getElementById("HistoryRibbon").style.backgroundColor = "#bb2d44";
    document.getElementById("HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("DB-HowToPlayDiv").style.display = "none";
    document.getElementById("DB-leaderboardDiv").style.display = "none";
    document.getElementById("DB-historyDiv").style.display = "flex";
}

function showDotsAndBoxesLeaderboardDiv() {
    document.getElementById("HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("LeaderboardRibbon").style.backgroundColor = "#bb2d44";

    document.getElementById("DB-HowToPlayDiv").style.display = "none";
    document.getElementById("DB-historyDiv").style.display = "none";
    document.getElementById("DB-leaderboardDiv").style.display = "flex";
}

function showDotsAndBoxesHowToPlay() {
    document.getElementById("HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("HowToPlayRibbon").style.backgroundColor = "#bb2d44";
    document.getElementById("LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("DB-historyDiv").style.display = "none";
    document.getElementById("DB-leaderboardDiv").style.display = "none";
    document.getElementById("DB-HowToPlayDiv").style.display = "flex";
}