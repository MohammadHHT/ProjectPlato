function primary(player) {
    var nextPage = function (previous, next) {
        let pre = document.querySelector('section.' + previous);
        let nxt = document.querySelector('section.' + next);

        if (pre != undefined) {
            pre.classList.remove('page');
        }
        nxt.classList.add('page');
    }

    this.player = player;
    var self = this;
    document.querySelectorAll('aside nav')[4].addEventListener('click', function () {
        self.nextPage('primary', 'account');
    });
}