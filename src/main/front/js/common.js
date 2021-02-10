var navigation = document.querySelectorAll('aside nav');
var player, username, token, first_name, last_name, phone, email, year, month, day, level, money;

function next_page(previous, next) {
    let pre = document.querySelector('section.' + previous);
    let nxt = document.querySelector('section.' + next);

    if (pre != undefined) {
        pre.classList.remove('page');
    }
    nxt.classList.add('page');
}