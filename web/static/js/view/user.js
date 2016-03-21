var User = User || {};
(function (user) {
    User.openCreate = function (btn) {
        window.location = $(btn).data("url");
    }


})(User);