function seabattle() {
    this.cells = document.getElementsByClassName('cell');
    this.init();
}

seabattle.prototype.init = function () {
    let self = this;

    let canvas = document.getElementById('canvas');
    let ctx = canvas.getContext('2d');
    canvas.width = window.innerHeight * 0.97 * 0.560;
    canvas.height = window.innerHeight * 0.97 * 0.560;

    let x = canvas.getBoundingClientRect().left;
    let y = canvas.getBoundingClientRect().top;
    let ax, ay, zx, zy;
    let draw = false;

    canvas.addEventListener('mousedown', function (event) {
        ax = parseInt(event.clientX - x);
        ay = parseInt(event.clientY - y);
        draw = true;
    });

    canvas.addEventListener('mouseup', function (event) {
        draw = false;
        ctx.clearRect(0, 0, canvas.getBoundingClientRect().width, canvas.getBoundingClientRect().height);
        foo();
    });

    canvas.addEventListener('mousemove', function (event) {
        zx = parseInt(event.clientX - x);
        zy = parseInt(event.clientY - y);
        if (draw) {
            ctx.clearRect(0, 0, canvas.getBoundingClientRect().width, canvas.getBoundingClientRect().height);
            ctx.beginPath();
            let width = zx - ax;
            let height = zy - ay;
            ctx.fillStyle = 'rgba(0, 0, 0, 0.2)';
            ctx.fillRect(ax, ay, width, height);
        }
    });

    function foo() {
        for (let i = Math.floor(Math.min(ay, zy) / (window.innerHeight * 0.97 * 0.0560)); i <= Math.floor(Math.max(ay, zy) / (window.innerHeight * 0.97 * 0.0560)); i++) {
            for (let j = Math.floor(Math.min(ax, zx) / (window.innerHeight * 0.97 * 0.0560)); j <= Math.floor(Math.max(ax, zx) / (window.innerHeight * 0.97 * 0.0560)); j++) {
                document.querySelector('.cell[num = \"' + (i * 10 + j + 1) + '\"]').classList.add('selected');
            }
        }
        let controlls = document.querySelectorAll('section.battle .control .size');
        switch (Math.abs(Math.floor(zx / (window.innerHeight * 0.97 * 0.0560)) - Math.floor(ax / (window.innerHeight * 0.97 * 0.0560) - 1)) * Math.abs(Math.floor(zy / (window.innerHeight * 0.97 * 0.0560)) - Math.floor(ay / (window.innerHeight * 0.97 * 0.0560) - 1))) {
            case 8:
                controlls[0].classList.add('used');
                break;
            case 6:
                controlls[1].classList.add('used');
                break;
            case 4:
                controlls[2].classList.add('used');
                break;
            case 2:
                controlls[3].classList.add('used');
                break;
        }
    }
}