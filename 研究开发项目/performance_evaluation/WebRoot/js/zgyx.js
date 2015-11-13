 $(function () {	//回车按键移动焦点用

        $('input:text:first').focus();

        var $inp = $('input:text');

        $inp.bind('keydown', function (e) {

            var key = e.which;

            if (key == 13) {

                e.preventDefault();

                var nxtIdx = $inp.index(this) + 1;

                $(":input:text:eq(" + nxtIdx + ")").focus();

            }

        });

    });