var navigation = document.querySelectorAll('aside nav');
var player, username, token, first_name, last_name, phone, email, year, month, day, level, money;

let menus = [], thisMenu;


function next_page(previous, next) {
    let pre = document.querySelector('section.' + previous);
    let nxt = document.querySelector('section.' + next);

    if (pre != undefined) {
        menus.push(pre);
        pre.classList.remove('page');
    }
    thisMenu = nxt;
    nxt.classList.add('page');
}

window.onload = function(e) {
    e.preventDefault();
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user reload ' + username + ' ' + token);
    };

    connection.onmessage = function (e) {

        connection.close();
    }
}

/*
function back() {

    if (menus.length > 0) {
        let pre = menus.pop();

        thisMenu.classList.remove('page');
        pre.classList.add('page');
    }
}

document.getElementById('backButton').addEventListener("click", back);
*/


