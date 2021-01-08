function register() {
    this.items = document.querySelectorAll('section.register form ol .item');
    this.inputs = document.querySelectorAll('section.register form ol .item input');
    this.next = document.querySelector('section.register form .next');
    this.message = document.querySelector('section.register form .message');
    this.question = 1;
    this.init();
}

register.prototype.init = function () {
    var self = this;

    for (let i = 0; i < 5; i++) {
        this.inputs[i].addEventListener('keydown', function (event) {
            if (event.keyCode == 13) {
                event.preventDefault();
                self.submission();
            }
        });
    }

    this.next.addEventListener('click', function (event) {
        self.submission();
    });

    this.inputs[0].addEventListener('focus', function (event) {
        self.next.classList.add('show');
    });
}

register.prototype.submission = function () {
    if ((this.question == 1 && this.q1()) || (this.question == 2 && this.q2()) || (this.question == 3 && this.q3()) || (this.question == 4 && this.q4()) || (this.question == 5 && this.q5())) {
        document.querySelector('section.register form .progress').style.transform = 'scaleX(' + this.question / 6 + ')';
        this.items[this.question - 1].classList.remove('show');
        this.items[this.question].classList.add('show');
        this.items[this.question].querySelector('input').focus();
        this.message.style.opacity = 0;
        this.question++;
    } else if (this.question == 6) {
        this.q6();
    } else {
        this.items[this.question - 1].querySelector('input').focus();
    }
}

register.prototype.q1 = function () {
    var e = new RegExp('^[a-zA-Z]+$');
    if (e.test(this.inputs[0].value)) {
        return true
    } else {
        this.message.innerHTML = "Just english letters!";
        this.message.style.opacity = 1;
    }
    return false;
}

register.prototype.q2 = function () {
    var e = new RegExp('^[a-zA-Z]+$');
    if (e.test(this.inputs[1].value)) {
        return true
    } else {
        this.message.innerHTML = "Just english letters!";
        this.message.style.opacity = 1;
    }
    return false;
}

register.prototype.q3 = function () {
    var e = new RegExp('\\w{3}\\w*');
    if (e.test(this.inputs[2].value)) {
        return true
    } else {
        this.message.innerHTML = "Username must be at least 3 characters!";
        this.message.style.opacity = 1;
    }
    return false;
}

register.prototype.q4 = function () {
    var e = new RegExp('\\w{6}\\w*');
    if (e.test(this.inputs[3].value)) {
        return true
    } else {
        this.message.innerHTML = "Password must be at least 6 characters!";
        this.message.style.opacity = 1;
    }
    return false;
}

register.prototype.q5 = function () {
    var e = new RegExp('\\d{12}');
    if (e.test(this.inputs[4].value)) {
        return true
    } else {
        this.message.innerHTML = "Invalid phone!";
        this.message.style.opacity = 1;
    }
    return false;
}

register.prototype.q6 = function () {
    var e = new RegExp('^\\w+@\\w+\\.(com|ir)$');
    if (e.test(this.inputs[5].value)) {
        var connection = new WebSocket('ws://127.0.0.1:4444');

        var self = this;
        connection.onopen = function () {
            connection.send('user register ' + self.inputs[0].value + ' ' + self.inputs[1].value + ' ' + self.inputs[2].value + ' ' + self.inputs[3].value + ' ' + self.inputs[4].value + ' ' + self.inputs[5].value);

        };
        connection.onmessage = function (e) {
            if (e.data.localeCompare('done') == 0) {
                let pre = document.querySelector('section.register');
                let nxt = document.querySelector('section.primary');

                if (pre != undefined) {
                    pre.classList.remove('page');
                }
                nxt.classList.add('page');
                console.log(e.data + '1');

                document.querySelector('aside nav.login').classList.remove('show');
                document.querySelector('aside nav.play').classList.add('show');
                document.querySelector('aside nav.account').classList.add('show');
                document.querySelector('aside nav.inbox').classList.add('show');
            } else {
                self.inputs[2].value = self.inputs[3].value = self.inputs[4].value = self.inputs[5].value = '';
                self.message.innerHTML = "Invalid username!";
                self.message.style.opacity = 1;
                console.log(e.data + '2');
            }
        };
    } else {
        this.message.innerHTML = "Invalid email!";
        this.message.style.opacity = 1;
    }
}