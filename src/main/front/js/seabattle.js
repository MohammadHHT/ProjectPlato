const connection = undefined;
var turn = undefined;
var timeout = undefined;
var gameid = undefined;

this._4x2 = [];
this._3x2 = [];
this._4x1 = [];
this._2x1 = [];

let canvas = document.getElementById('canvas');
let ctx = canvas.getContext('2d');
canvas.width = window.innerHeight * 0.97 * 0.560;
canvas.height = window.innerHeight * 0.97 * 0.560;

let cellHeight = window.innerHeight * 0.97 * 0.0560;
let controlls = document.querySelectorAll('section.battle .control .area');
let ax, ay, zx, zy;
let draw = false;

canvas.addEventListener('mousedown', mousedown);
canvas.addEventListener('mouseup', mouseup);
canvas.addEventListener('mousemove', mousemove);

function mousedown() {
    ax = parseInt(window.event.clientX - canvas.getBoundingClientRect().left);
    ay = parseInt(window.event.clientY - canvas.getBoundingClientRect().top);
    draw = true;
}

function mouseup() {
    draw = false;
    ctx.clearRect(0, 0, canvas.getBoundingClientRect().width, canvas.getBoundingClientRect().height);
    select();
}

function mousemove() {
    zx = parseInt(window.event.clientX - canvas.getBoundingClientRect().left);
    zy = parseInt(window.event.clientY - canvas.getBoundingClientRect().top);
    if (draw) {
        ctx.clearRect(0, 0, canvas.getBoundingClientRect().width, canvas.getBoundingClientRect().height);
        ctx.beginPath();
        let width = zx - ax;
        let height = zy - ay;
        ctx.fillStyle = 'rgba(0, 0, 0, 0.2)';
        ctx.fillRect(ax, ay, width, height);
    }
}

function select() {
    let width = Math.abs(Math.floor(zx / cellHeight) - Math.floor(ax / cellHeight)) + 1;
    let height = Math.abs(Math.floor(zy / cellHeight) - Math.floor(ay / cellHeight)) + 1;
    let product = width * height;

    if (product == 8 && Math.abs(width - height) == 2) {
        while (_4x2.length != 0) {
            document.querySelector('section.battle .board.me .cell[num = \"' + _4x2[0] + '\"]').classList.remove('selected');
            _4x2.shift();
        }
        if (selectCell(1)) controlls[0].classList.add('used');
        else controlls[0].classList.remove('used');
    } else if (product == 6 && Math.abs(width - height) == 1) {
        while (_3x2.length != 0) {
            document.querySelector('section.battle .board.me .cell[num = \"' + _3x2[0] + '\"]').classList.remove('selected');
            _3x2.shift();
        }
        if (selectCell(2)) controlls[1].classList.add('used');
        else controlls[1].classList.remove('used');
    } else if (product == 4 && Math.abs(width - height) == 3) {
        while (_4x1.length != 0) {
            document.querySelector('section.battle .board.me .cell[num = \"' + _4x1[0] + '\"]').classList.remove('selected');
            _4x1.shift();
        }
        if (selectCell(3)) controlls[2].classList.add('used');
        else controlls[2].classList.remove('used');
    } else if (product == 2 && Math.abs(width - height) == 1) {
        if (_2x1.length == 2) {
            while (_2x1.length != 0) {
                document.querySelector('section.battle .board.me .cell[num = \"' + _2x1[0][0] + '\"]').classList.remove('selected');
                document.querySelector('section.battle .board.me .cell[num = \"' + _2x1[0][1] + '\"]').classList.remove('selected');
                _2x1.shift();
            }
            controlls[3].classList.remove('used');
            controlls[4].classList.remove('used');
        }
        if (selectCell(4)) controlls[3 + _2x1.length - 1].classList.add('used');
        else if (_2x1.length == 2) {
            controlls[3].classList.remove('used');
            controlls[4].classList.remove('used');
        }
    } else {
        let messageBox = document.querySelector('section.battle .board.me .message');
        if (!messageBox.classList.contains('show')) {
            messageBox.style.visibility = 'visible';
            messageBox.classList.add('show');
            setTimeout(function () {
                messageBox.classList.remove('show');
                setTimeout(function () {
                    messageBox.style.visibility = null;
                }, 300);
            }, 2000);
        }
    }
}

function selectCell(ship) {
    for (let i = Math.floor(Math.min(ay, zy) / cellHeight); i <= Math.floor(Math.max(ay, zy) / cellHeight); i++) {
        for (let j = Math.floor(Math.min(ax, zx) / cellHeight); j <= Math.floor(Math.max(ax, zx) / cellHeight); j++) {
            if (document.querySelector('section.battle .board.me .cell[num = \"' + (i * 10 + j + 1) + '\"]').classList.contains('selected')) return false;
        }
    }
    for (let i = Math.floor(Math.min(ay, zy) / cellHeight); i <= Math.floor(Math.max(ay, zy) / cellHeight); i++) {
        for (let j = Math.floor(Math.min(ax, zx) / cellHeight); j <= Math.floor(Math.max(ax, zx) / cellHeight); j++) {
            let cell = i * 10 + j + 1;
            document.querySelector('section.battle .board.me .cell[num = \"' + cell + '\"]').classList.add('selected');
            if (ship == 1) _4x2.push(cell);
            else if (ship == 2) _3x2.push(cell);
            else if (ship == 3) _4x1.push(cell);
            else {
                if (_2x1.length == 0) _2x1.push([cell]);
                else if (_2x1.length == 1 && _2x1[0].length == 1) _2x1[0].push(cell);
                else if (_2x1.length == 1 && _2x1[0].length == 2) _2x1.push([cell]);
                else _2x1[1].push(cell);
            }
        }
    }
    if (_4x2.length == 8 && _4x1.length == 4 && _3x2.length == 6 && _2x1.length == 2) {
        arranged();
    }
    return true;
}

function arranged() {
    let startButton = document.querySelector('section.battle .board.me .start');
    startButton.style.visibility = 'visible';
    startButton.classList.add('show');
    startButton.disabled = false;
    canvas.removeEventListener('mousedown', mousedown);
    canvas.removeEventListener('mouseup', mouseup);
    canvas.removeEventListener('mousemove', mousemove);
}

document.getElementById('rearrange').addEventListener('click', () => {
    _4x2 = [];
    _3x2 = [];
    _4x1 = [];
    _2x1 = [];

    let selectedCells = document.querySelectorAll('.cell.selected');
    for (let i = 0; i < selectedCells.length; i++) {
        selectedCells[i].classList.remove('selected');
    }

    for (let i = 0; i < controlls.length; i++) {
        controlls[i].classList.remove('used');
    }

    document.querySelector('section.battle .board.me img.xl').classList.remove('show');
    setTimeout(function () { document.querySelector('section.battle .board.me img.xl').style.visibility = 'hidden'; }, 300);
    document.querySelector('section.battle .board.me img.l').classList.remove('show');
    setTimeout(function () { document.querySelector('section.battle .board.me img.l').style.visibility = 'hidden'; }, 300);
    document.querySelector('section.battle .board.me img.m').classList.remove('show');
    setTimeout(function () { document.querySelector('section.battle .board.me img.m').style.visibility = 'hidden'; }, 300);
    document.querySelectorAll('section.battle .board.me img.s')[0].classList.remove('show');
    setTimeout(function () { document.querySelectorAll('section.battle .board.me img.s')[0].style.visibility = 'hidden'; }, 300);
    document.querySelectorAll('section.battle .board.me img.s')[1].classList.remove('show');
    setTimeout(function () { document.querySelectorAll('section.battle .board.me img.s')[1].style.visibility = 'hidden'; }, 300);

    let startButton = document.querySelector('section.battle .board.me .start');
    if (startButton.classList.contains('show')) {
        startButton.classList.remove('show');
        startButton.disabled = true;
        setTimeout(function () {
            startButton.style.visibility = null;
        }, 300);
        canvas.addEventListener('mousedown', mousedown);
        canvas.addEventListener('mouseup', mouseup);
        canvas.addEventListener('mousemove', mousemove);
    }
});

document.querySelector('section.battle .board.me .start').addEventListener('click', () => {
    if (!document.querySelector('section.battle .board.me .start').disabled) {
        connection.send('game battle arrange' + username + ' ' + token + ' ' + _4x2[0] + ' ' + _4x2[1] + ' ' + _4x2[2] + ' ' + _4x2[3]
            + ' ' + _4x2[4] + ' ' + _4x2[5] + ' ' + _4x2[6] + ' ' + _4x2[7] + ' ' + _3x2[0] + ' ' + _3x2[1]
            + ' ' + _3x2[2] + ' ' + _3x2[3] + ' ' + _3x2[4] + ' ' + _3x2[5] + ' ' + _4x1[0] + ' ' + _4x1[1] + ' ' + _4x1[2] + ' ' + _4x1[3]
            + ' ' + _2x1[0][0] + ' ' + _2x1[0][1] + ' ' + _2x1[1][0] + ' ' + _2x1[1][1]);

        place_ship('4x2', _4x2);
        place_ship('3x2', _3x2);
        place_ship('4x1', _4x1);
        place_ship('2x1.0', _2x1[0]);
        place_ship('2x1.1', _2x1[1]);

        setTimeout(function () {
            document.querySelector('section.battle .board.me').style.transform = 'rotateX(57deg)';
            document.querySelector('section.battle .board.me').style.boxShadow = '0 5px 5px rgba(0, 0, 0, 0.2)';
        }, 300);
        canvas.parentNode.removeChild(canvas);
        document.querySelector('section.battle .board.me .start').parentNode.removeChild(document.querySelector('section.battle .board.me .start'));
    }
});

function place_ship(square, array) {
    let top = 10, bottom = 1, left = 10, right = 1;

    for (let i = 0; i < array.length; i++) {
        if ((array[i] % 10) < left && (array[i] % 10) != 0) {
            left = array[i] % 10;
        }
        if ((array[i] % 10) > right) {
            if ((array[i] % 10) == 0) { right = 10; }
            else { right = array[i] % 10; }
        }
        if (Math.ceil(array[i] / 10) < top) top = Math.ceil(array[i] / 10);
        if (Math.ceil(array[i] / 10) > bottom) bottom = Math.ceil(array[i] / 10);
    }

    if (square.localeCompare('4x2') == 0) {
        if (bottom - top > right - left) {
            if (left <= 5) {
                document.querySelector('section.battle .board.me img.xl').style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - bottom) + 'px) rotate(-90deg)';
            } else {
                document.querySelector('section.battle .board.me img.xl').style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - top - 1) + 'px) rotate(90deg)';
            }
        } else {
            document.querySelector('section.battle .board.me img.xl').style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - bottom) + 'px)';
        }
        document.querySelector('section.battle .board.me img.xl').style.visibility = 'visible';
        document.querySelector('section.battle .board.me img.xl').classList.add('show');
    } else if (square.localeCompare('3x2') == 0) {
        if (bottom - top > right - left) {
            if (left <= 5) {
                document.querySelector('section.battle .board.me img.l').style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - bottom) + 'px) rotate(-90deg)';
            } else {
                document.querySelector('section.battle .board.me img.l').style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - top - 1) + 'px) rotate(90deg)';
            }
        } else {
            document.querySelector('section.battle .board.me img.l').style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - bottom) + 'px)';
        }
        document.querySelector('section.battle .board.me img.l').style.visibility = 'visible';
        document.querySelector('section.battle .board.me img.l').classList.add('show');
    } else if (square.localeCompare('4x1') == 0) {
        if (bottom - top > right - left) {
            if (left <= 5) {
                document.querySelector('section.battle .board.me img.m').style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - bottom) + 'px) rotate(-90deg)';
            } else {
                document.querySelector('section.battle .board.me img.m').style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - top) + 'px) rotate(90deg)';
            }
        } else {
            document.querySelector('section.battle .board.me img.m').style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - bottom) + 'px)';
        }
        document.querySelector('section.battle .board.me img.m').style.visibility = 'visible';
        document.querySelector('section.battle .board.me img.m').classList.add('show');
    } else if (square.localeCompare('2x1.0') == 0) {
        if (bottom - top > right - left) {
            if (left <= 5) {
                document.querySelectorAll('section.battle .board.me img.s')[0].style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - bottom) + 'px) rotate(-90deg)';
            } else {
                document.querySelectorAll('section.battle .board.me img.s')[0].style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - top) + 'px) rotate(90deg)';
            }
        } else {
            document.querySelectorAll('section.battle .board.me img.s')[0].style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - bottom) + 'px)';
        }
        document.querySelectorAll('section.battle .board.me img.s')[0].style.visibility = 'visible';
        document.querySelectorAll('section.battle .board.me img.s')[0].classList.add('show');
    } else if (square.localeCompare('2x1.1') == 0) {
        if (bottom - top > right - left) {
            if (left <= 5) {
                document.querySelectorAll('section.battle .board.me img.s')[1].style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - bottom) + 'px) rotate(-90deg)';
            } else {
                document.querySelectorAll('section.battle .board.me img.s')[1].style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - top) + 'px) rotate(90deg)';
            }
        } else {
            document.querySelectorAll('section.battle .board.me img.s')[1].style.transform = 'translate(' + cellHeight * (left - 1) + 'px, -' + cellHeight * (10 - bottom) + 'px)';
        }
        document.querySelectorAll('section.battle .board.me img.s')[1].style.visibility = 'visible';
        document.querySelectorAll('section.battle .board.me img.s')[1].classList.add('show');
    }
}

function change_turn() {
    if (document.querySelector('section.battle section .guest').classList.contains('turn')) {
        document.querySelector('section.battle section .host').classList.add('turn');
        document.querySelector('section.battle section .guest').classList.remove('turn');
        document.querySelector('section.battle section .guest .timer').classList.remove('start');
    } else {
        document.querySelector('section.battle section .guest').classList.add('turn');
        document.querySelector('section.battle section .host').classList.remove('turn');
        document.querySelector('section.battle section .host .timer').classList.remove('start');
    }
    document.querySelector('section.battle section .turn .timer').classList.add('start');
}

function shot(cell) {
    if (turn) {
        clearTimeout(timeout);
        connection.send('game battle shot ' + username + ' ' + token + ' ' + cell);
        document.querySelector('section.battle .board.me .cell[num = \"' + cell + '\"]').onclick = '';
    }
}

function seabattle(create, logid, user) {
    document.querySelector('section.battle section .host').classList.add('turn');
    document.querySelector('section.battle section .guest').classList.remove('turn');
    document.querySelector('section.battle section .guest .timer').classList.remove('start');

    connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onmessage = function (e) {
        let command = e.data.split(' ')[0];
        if (command.localeCompare('start') == 0) {
            change_turn();
            timeout = setTimeout(function () {
                connection.send('game battle turn ' + username + ' ' + token);
                turn = false;
            }, 5000);
        } else if (command.localeCompare('created') == 0 || command.localeCompare('joined') == 0) {
            gameid = command[1];
        } else if (command.localeCompare('destroyedme') == 0) {
            document.querySelector('section.battle .board.me .cell[num = \"' + command[1] + '\"]').classList.add('destroyed');
            turn = true;
            change_turn();
        } else if (command.localeCompare('emptyme') == 0) {
            document.querySelector('section.battle .board.me .cell[num = \"' + command[1] + '\"]').classList.add('empty');
            turn = true;
            change_turn();
        } else if (command.localeCompare('destroyed') == 0) {
            document.querySelector('section.battle .board.notme .cell[num = \"' + command[1] + '\"]').classList.add('destroyed');
        } else if (command.localeCompare('empty') == 0) {
            document.querySelector('section.battle .board.notme .cell[num = \"' + command[1] + '\"]').classList.add('empty');
        } else if (command.localeCompare('win') == 0) {
            back();
        } else if (command.localeCompare('lose') == 0) {
            back();
        }
    }

    if (create) {
        document.querySelector('section.battle section .host span').textContent = username;
        document.querySelector('section.battle section .guest span').textContent = user;
        turn = true;
        connection.send('game battle create ' + username + ' ' + token);
    } else {
        document.querySelector('section.battle section .host span').textContent = user;
        document.querySelector('section.battle section .guest span').textContent = username;
        turn = false;
        connection.send('game battle join ' + username + ' ' + token + ' ' + logid);
    }
}

