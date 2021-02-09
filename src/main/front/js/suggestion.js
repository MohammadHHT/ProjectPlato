function loadAllPlayer() {
    let playerList = document.querySelector('section.suggestion .all-players .players-area');

    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user allUsers' + username + ' ' + token);
    };

    connection.onmessage = function (e) {
        let users = e.data.split('/');

        let username = [];
        for (let i = 0; i < users.length; i++) {
            let tmp = users[i].split(' ');
            username[i] = tmp[2] ;
        }

        for (let i = 0; i < users.length; i++) {
            playerList.insertAdjacentHTML('beforeend',
                '                    <div class="player-box">\n' +
                '                        <div onclick="chatWithPlayer()" class="message-icon">\n' +
                '                            <img src="jpg/message.png" alt="message icon">\n' +
                '                        </div>\n' +
                '                        <div class="player-avatar">\n' +
                '                            <img src="avatar/avatar (' + Math.floor(Math.random() * 50 + 1) + ').svg">\n' +
                '                            <span>' + username[i] + '</span>\n' +
                '                        </div>\n' +
                '                        <div class="suggestion-checkbox">\n' +
                '                            <input type="checkbox" class="regular-checkbox" value="' + username[i] + '" />\n' +
                '                        </div>\n' +
                '                    </div>');
        }
        connection.close();
    };
}

var isSeaBattleSelected = false;
var isDotsAndBoxesSelected = false;
function selectSeaBattle() {
    if (!isSeaBattleSelected) {
        document.getElementById('sea-battle-select').style.backgroundColor = '#34f16e';
        document.getElementById('SB-select-button').innerHTML = 'Selected';
        isSeaBattleSelected = true;
    } else {
        document.getElementById('sea-battle-select').style.backgroundColor = '#ddddd9';
        document.getElementById('SB-select-button').innerHTML = 'Select';
        isSeaBattleSelected = false;
    }
}

function selectDotsAndBoxes() {
    if (!isDotsAndBoxesSelected) {
        document.getElementById('dots-and-boxes-select').style.backgroundColor = '#34f16e';
        document.getElementById('DB-select-button').innerHTML = 'Selected';
        isDotsAndBoxesSelected = true;
    } else {
        document.getElementById('dots-and-boxes-select').style.backgroundColor = '#ddddd9';
        document.getElementById('DB-select-button').innerHTML = 'Select';
        isDotsAndBoxesSelected = false;
    }
}

function sendSuggestion() {
    let checkedValue;
    let usernameForSendSuggestion = [];

    if (!(isSeaBattleSelected && isDotsAndBoxesSelected)) {
        for (let i = 0; i < document.querySelectorAll('.regular-checkbox:checked').length; i++) {
            checkedValue = document.querySelectorAll('.regular-checkbox:checked')[i].value;
            usernameForSendSuggestion[i] = checkedValue;
        }



        for (let i = 0; i < usernameForSendSuggestion.length; i++) {
            console.log(usernameForSendSuggestion[i]);
        }

    } else {
        alert("Please select one game for suggestion!");
    }



    const connection = new WebSocket('ws://127.0.0.1:4444');

    if (isSeaBattleSelected) {
        connection.onopen = function () {
            for (let i = 0; i< usernameForSendSuggestion.length; i++) {
                connection.send('user sendSuggestion' + self.username + ' ' + self.token + ' ' + usernameForSendSuggestion[i] + ' ' + 'BattleSea');

            }
        };
    } else if (isDotsAndBoxesSelected) {
        connection.onopen = function () {
            for (let i = 0; i< usernameForSendSuggestion.length; i++) {
                connection.send('user sendSuggestion' + self.username + ' ' + self.token + ' ' + usernameForSendSuggestion[i] + ' ' + 'DotsAndBoxes');

            }
        };
    } else {
        alert('Please select a game first!');
    }


    connection.onmessage = function (e) {
        let data = e.data.split(' ');

        if (data.length === 2) {
            alert('All suggestions sent :)');
        } else {
            alert('Server can not send suggestion!');
        }
        connection.close();
    }
}

function loadSuggestedGames() {
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user loadSuggestedGame' + username + ' ' + token);
    };

    connection.onmessage = function (e) {
        let data = e.data.split(' ');
        if (data.length === 4) {
            document.getElementById('suggested-game-situation').style.display = 'block';
        } else if (data.length === 5) {
            document.getElementById('sea-battle-suggestion-pic').style.display = 'block';
            document.getElementById('dots-and-boxes-suggestion-pic').style.display = 'block';
            document.getElementById('suggested-game-situation').style.display = 'none';
        } else if (data.length === 2) {
            document.getElementById('sea-battle-suggestion-pic').style.display = 'block';
            document.getElementById('suggested-game-situation').style.display = 'none';
        } else if (data.length === 3) {
            document.getElementById('dots-and-boxes-suggestion-pic').style.display = 'block';
            document.getElementById('suggested-game-situation').style.display = 'none';
        }
        connection.close();
    }

    loadFriendsRequest();
}

function loadFriendsRequest() {
    let friendsRequestList = document.querySelector('.section .player-message .request .friend-request-container');
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user loadFriendsRequest' + username + ' ' + token);
    };

    connection.onmessage = function (e) {
        let friendUsername = e.data.split(' ');

        for (let i = 0; i < friendUsername.length; i++) {
            friendsRequestList.insertAdjacentHTML('beforeend',
                '                    <div class="friend-request" id="' + friendUsername[i] +'">\n' +
                '                        <div class="friend-avatar">\n' +
                '                            <img src="avatar/avatar%20(1).svg">\n' +
                '                            <span>' + friendUsername[i] + '</span>\n' +
                '                        </div>\n' +
                '                        <div class="request-buttons">\n' +
                '                            <div onclick="acceptFriend(' + friendUsername[i] + ')" class="accept-button"><span>accept</span></div>\n' +
                '                            <div onclick="declineFriend(' + friendUsername[i] + ')" class="decline-button"><span>decline</span></div>\n' +
                '                        </div>\n' +
                '                    </div>');
        }

        connection.close();
    }
}

function acceptFriend(playerUsername) {
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user acceptFriend' + username + ' ' + token + ' ' + playerUsername);
    };

    connection.onmessage = function (e) {
        let data = e.data.split(' ');

        if (data.length === 2) {
            document.getElementById(playerUsername).style.display = 'none';
            alert(playerUsername + ' now is your friend!');
        }

        connection.close();
    }
}

function declineFriend(playerUsername) {
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user declineFriend' + username + ' ' + token + ' ' + playerUsername);
    };

    connection.onmessage = function (e) {
        let data = e.data.split(' ');

        if (data.length === 2) {
            document.getElementById(playerUsername).style.display = 'none';
            alert(playerUsername + ' removed from your requests.');
        }

        connection.close();
    }
}