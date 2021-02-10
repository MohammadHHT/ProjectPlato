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


