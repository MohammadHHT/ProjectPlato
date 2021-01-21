function seabattle() {
    this.cells = document.getElementsByClassName('cell');
    this._4x2 = [];
    this._3x2 = [];
    this._4x1 = [];
    this._2x1 = [];
    this.init();
}

seabattle.prototype.init = function () {
    initCanvas();

    let self = this;

    function initCanvas() {
        document.getElementById('rearrange').addEventListener('click', rearrange);

        let canvas = document.getElementById('canvas');
        let ctx = canvas.getContext('2d');
        canvas.width = window.innerHeight * 0.97 * 0.560;
        canvas.height = window.innerHeight * 0.97 * 0.560;

        let left = canvas.getBoundingClientRect().left;
        let top = canvas.getBoundingClientRect().top;
        let cellHeight = window.innerHeight * 0.97 * 0.0560;
        let controlls = document.querySelectorAll('section.battle .control .area');
        let ax, ay, zx, zy;
        let draw = false;

        function mousedown() {
            ax = parseInt(window.event.clientX - left);
            ay = parseInt(window.event.clientY - top);
            draw = true;
        }

        function mouseup() {
            draw = false;
            ctx.clearRect(0, 0, canvas.getBoundingClientRect().width, canvas.getBoundingClientRect().height);
            select();
        }

        function mousemove() {
            zx = parseInt(window.event.clientX - left);
            zy = parseInt(window.event.clientY - top);
            if (draw) {
                ctx.clearRect(0, 0, canvas.getBoundingClientRect().width, canvas.getBoundingClientRect().height);
                ctx.beginPath();
                let width = zx - ax;
                let height = zy - ay;
                ctx.fillStyle = 'rgba(0, 0, 0, 0.2)';
                ctx.fillRect(ax, ay, width, height);
            }
        }

        canvas.addEventListener('mousedown', mousedown);
        canvas.addEventListener('mouseup', mouseup);
        canvas.addEventListener('mousemove', mousemove);

        function select() {
            let width = Math.abs(Math.floor(zx / cellHeight) - Math.floor(ax / cellHeight)) + 1;
            let height = Math.abs(Math.floor(zy / cellHeight) - Math.floor(ay / cellHeight)) + 1;
            let product = width * height;

            if (product == 8 && Math.abs(width - height) == 2) {
                while (self._4x2.length != 0) {
                    document.querySelector('section.battle .board .cell[num = \"' + self._4x2[0] + '\"]').classList.remove('selected');
                    self._4x2.shift();
                }
                if (selectCell(1, ax, ay, zx, zy)) controlls[0].classList.add('used');
                else controlls[0].classList.remove('used');
            } else if (product == 6 && Math.abs(width - height) == 1) {
                while (self._3x2.length != 0) {
                    document.querySelector('section.battle .board .cell[num = \"' + self._3x2[0] + '\"]').classList.remove('selected');
                    self._3x2.shift();
                }
                if (selectCell(2, ax, ay, zx, zy)) controlls[1].classList.add('used');
                else controlls[1].classList.remove('used');
            } else if (product == 4 && Math.abs(width - height) == 3) {
                while (self._4x1.length != 0) {
                    document.querySelector('section.battle .board .cell[num = \"' + self._4x1[0] + '\"]').classList.remove('selected');
                    self._4x1.shift();
                }
                if (selectCell(3, ax, ay, zx, zy)) controlls[2].classList.add('used');
                else controlls[2].classList.remove('used');
            } else if (product == 2 && Math.abs(width - height) == 1) {
                if (self._2x1.length == 2) {
                    while (self._2x1.length != 0) {
                        document.querySelector('section.battle .board .cell[num = \"' + self._2x1[0][0] + '\"]').classList.remove('selected');
                        document.querySelector('section.battle .board .cell[num = \"' + self._2x1[0][1] + '\"]').classList.remove('selected');
                        self._2x1.shift();
                    }
                    controlls[3].classList.remove('used');
                    controlls[4].classList.remove('used');
                }
                if (selectCell(4, ax, ay, zx, zy)) controlls[3 + self._2x1.length - 1].classList.add('used');
                else if (self._2x1.length == 2) {
                    controlls[3].classList.remove('used');
                    controlls[4].classList.remove('used');
                }
            } else {
                let messageBox = document.querySelector('section.battle .board .message');
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

        function selectCell(ship, ax, ay, zx, zy) {
            for (let i = Math.floor(Math.min(ay, zy) / cellHeight); i <= Math.floor(Math.max(ay, zy) / cellHeight); i++) {
                for (let j = Math.floor(Math.min(ax, zx) / cellHeight); j <= Math.floor(Math.max(ax, zx) / cellHeight); j++) {
                    if (document.querySelector('section.battle .board .cell[num = \"' + (i * 10 + j + 1) + '\"]').classList.contains('selected')) return false;
                }
            }
            for (let i = Math.floor(Math.min(ay, zy) / cellHeight); i <= Math.floor(Math.max(ay, zy) / cellHeight); i++) {
                for (let j = Math.floor(Math.min(ax, zx) / cellHeight); j <= Math.floor(Math.max(ax, zx) / cellHeight); j++) {
                    let cell = i * 10 + j + 1;
                    document.querySelector('section.battle .board .cell[num = \"' + cell + '\"]').classList.add('selected');
                    if (ship == 1) self._4x2.push(cell);
                    else if (ship == 2) self._3x2.push(cell);
                    else if (ship == 3) self._4x1.push(cell);
                    else {
                        if (self._2x1.length == 0) self._2x1.push([cell]);
                        else if (self._2x1.length == 1 && self._2x1[0].length == 1) self._2x1[0].push(cell);
                        else if (self._2x1.length == 1 && self._2x1[0].length == 2) self._2x1.push([cell]);
                        else self._2x1[1].push(cell);
                    }
                }
            }
            if (self._4x2.length == 8 && self._4x1.length == 4 && self._3x2.length == 6 && self._2x1.length == 2) {
                arranged();
            }
            return true;
        }

        function arranged() {
            let startButton = document.querySelector('section.battle .board .start');
            startButton.style.visibility = 'visible';
            startButton.classList.add('show');
            startButton.disabled = false;
            canvas.removeEventListener('mousedown', mousedown);
            canvas.removeEventListener('mouseup', mouseup);
            canvas.removeEventListener('mousemove', mousemove);
        }

        function rearrange() {
            self._4x2 = [];
            self._3x2 = [];
            self._4x1 = [];
            self._2x1 = [];

            let selectedCells = document.querySelectorAll('.cell.selected');
            for (let i = 0; i < selectedCells.length; i++) {
                selectedCells[i].classList.remove('selected');
            }

            for (let i = 0; i < controlls.length; i++) {
                controlls[i].classList.remove('used');
            }

            let startButton = document.querySelector('section.battle .board .start');
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
        }
    }
}