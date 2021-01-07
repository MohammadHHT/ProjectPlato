function login() {
    this.items = document.querySelectorAll('.login form ol .item');
    this.inputs = document.querySelectorAll('.login form ol .item input');
    this.next = document.querySelector('.login form .next');
    this.message = document.querySelector('.login form .message');
    this.question = 1;
    this.init();
}

login.prototype.init = function () {
    this.items[0].classList.add('show');
    var self = this;

    for (let i = 0; i < 2; i++) {
        this.inputs[i].addEventListener('keydown', function (event) {
            if (event.keyCode == 13) {
                event.preventDefault();
                self.next_question();
            }
        });
    }

    this.next.addEventListener('click', function (event) {
        self.next_question();
    });

    this.inputs[0].addEventListener('focus', function (event) {
        self.next.classList.add('show');
    });

    document.querySelector('.login h1 span').addEventListener('click', function(event) {
        nextPage('login', 'register');
    });
}

login.prototype.next_question = function () {
    if ((this.question == 1 && this.q1()) || (this.question == 2 && this.q2())) {
        document.querySelector('.login form .progress').style.transform = 'scaleX(' + this.question / 2 + ')';
        this.items[this.question - 1].classList.remove('show');
        this.items[this.question].classList.add('show');
        this.items[this.question].querySelector('input').focus();
        this.message.style.opacity = 0;
        this.question++;
    } else {
        this.items[this.question - 1].querySelector('input').focus();
    }
}

login.prototype.q1 = function () {
    var e = new RegExp('^[a-zA-Z]+$');
    if (e.test(this.inputs[0].value)) {
        return true
    } else {
        this.message.innerHTML = "Just english letters!";
        this.message.style.opacity = 1;
    }
    return false;
}

login.prototype.q2 = function () {
    var e = new RegExp('^[a-zA-Z]+$');
    if (e.test(this.inputs[1].value)) {
        return true
    } else {
        this.message.innerHTML = "Just english letters!";
        this.message.style.opacity = 1;
    }
    return false;
}